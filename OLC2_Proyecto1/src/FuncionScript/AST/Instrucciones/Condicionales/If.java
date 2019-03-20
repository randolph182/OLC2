/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.AST.Expresiones.Operacion.Unario;
import FuncionScript.AST.Expresiones.RetornoSecundario;
import FuncionScript.AST.Expresiones.Return;
import FuncionScript.AST.Instrucciones.Break;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import herramientas.ContadorBreak;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author rm
 */
public class If implements Instruccion {

    Expresion condicion;
    LinkedList<nodoAST> sentencias1;
    LinkedList<nodoAST> sentencias2;
    If elseIf = null;
    boolean hay_else = false;
    int linea;

    public If(Expresion condicion, LinkedList<nodoAST> sentencias, int linea) {
        this.condicion = condicion;
        this.sentencias1 = sentencias;
        this.linea = linea;
        this.hay_else = false;
    }

    public If(Expresion cond, LinkedList<nodoAST> s1, LinkedList<nodoAST> s2, int linea) {
        this.condicion = cond;
        this.sentencias1 = s1;
        this.sentencias2 = s2;
        this.linea = linea;
        this.hay_else = true;
    }

    public If(Expresion cond, LinkedList<nodoAST> ins, If tIf, int linea) {
        this.condicion = cond;
        this.sentencias1 = ins;
        this.elseIf = tIf;
        this.linea = linea;
        this.hay_else = false;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        //variables que funcionan para complementar el retorno o como varios usos
        Object a = null;
        Tipo tipoA = new Tipo(Tipo.Primitivo.NULL);
        Entorno nuevoEnt = new Entorno(ent);
        //bandera flag que sirve para saber si la condicion del if es verdera o no 
        boolean flag = false;
        if (condicion instanceof Unario) {
            //obtenemos el valor de la expresion la almacenamos en a;
            a = (condicion == null) ? null : condicion.getValor(nuevoEnt);
            tipoA = condicion.getTipo(nuevoEnt);
            if (tipoA.isBoolean()) {
                if (a instanceof String) {
                    if (a.toString().equals("verdadero")) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                } else{
                    Editor.insertarTextoConsola("Problemas con la la condicional en If. Linea: " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Problemas con la la condicional en If");
                }
            } else {
                Editor.insertarTextoConsola("La condicional no es booleano en If. Linea: " + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "La condicional no es booleano en If");
            }
        } else if (condicion instanceof Identificador) {
            a = ((Identificador) condicion).getIdentificador();
            if (nuevoEnt.get((String) a) != null) {
                Simbolo s = nuevoEnt.get((String) a);
                tipoA = s.getTipo();
                if (tipoA.isBoolean()) {
                    if (s.getValor().toString().equals("verdadero")) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                } else {
                    Editor.insertarTextoConsola("La condicional no es booleano en If. Linea: " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "La condicional no es booleano en If");
                    flag = false;
                }
            } else{
                    Editor.insertarTextoConsola("El identificador como condicional en  If no es encontro. Linea: " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El identificador como condicional en  If no es encontro");
                    flag = false;
            } 
        } else {
            //sino es ninguna de las anterires entonces obtenemos el valor de las 
            //operaciones logicas, relacionales 
            
            flag = (boolean) condicion.getValor(nuevoEnt);
        }
        //SI flag == true ENTONCES EJECUTAMOS  LAS SENTENCIAS DEL IF
        //creamos un nuevo entorno
        if (flag) {
            return interpretarSetnencias(sentencias1,nuevoEnt);
        } else if (elseIf != null) {
            //APARTADO PARA ELSE IF 
            return elseIf.ejecutar(nuevoEnt);
        } else if (hay_else) {
            return interpretarSetnencias(sentencias2,nuevoEnt);
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private Object interpretarSetnencias(LinkedList<nodoAST> sentencias,Entorno ent){
        Object a = null;
        
        //si existe valor  de retorno esta variable hara el trabajo de retornarla como objeto
        Return valRetorno  = null;
        int valTmpBrake = ContadorBreak.getInstance().getContador();
        for (nodoAST nodo : sentencias) {
            if (nodo instanceof Instruccion) {
                Instruccion instruccion = (Instruccion) nodo;
                //::::::::::::::::::::::::: INSTRUCCION TIPO BREAK ;;;;;;;;;;;;;;;;;;;;;
                if(instruccion instanceof Break){
                    if(ContadorBreak.getInstance().getContador() == 0){
                        Editor.insertarTextoConsola("Error en If vino un Break en un mal ambito. Linea: " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error en If vino un Break en un mal ambito");
                    } else
                        ContadorBreak.getInstance().decrementarContador();
                    return null;
                }else {
                    //::::::::::::::::::::::::: INSTRUCCION NORMAL :::::::::::::::::::
                    a = instruccion.ejecutar(ent);
                    if(a != null){
                        RetornoSecundario rs = (RetornoSecundario)a;
                        return rs;
                    } 
                }
            //::::::::::::::::::::::::: EXPRESION RETURN :::::::::::::::::::
            } else if (nodo instanceof Return) {
                
                Expresion e = (Expresion)nodo;
                a = e.getValor(ent);
                if (a != null) {
                    Tipo  tipo = e.getTipo(ent);
                    RetornoSecundario rs = new RetornoSecundario(a, tipo, linea);
                    return rs; //:::::::::::::::::::::::::::::::::::::::::
                }
            } else if (nodo instanceof Expresion) {
                Expresion exp = (Expresion) nodo;
                Object b = exp.getValor(ent);
                if(b != null){
                    RetornoSecundario rs = (RetornoSecundario)b;
                    return rs;
                }
            }
            //::::::::::::::::: COMPROBACION DEL BREAKE ANIDADO
            if(ContadorBreak.getInstance().getContador() < valTmpBrake ){
                        return null; // es porque en una instruccion se activo un breake se necesita salir
            }
        }
        return null;
    }

}
