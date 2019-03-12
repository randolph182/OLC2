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
import java.util.LinkedList;

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
        //bandera flag que sirve para saber si la condicion del if es verdera o no 
        boolean flag = false;
        if (condicion instanceof Unario) {
            //obtenemos el valor de la expresion la almacenamos en a;
            a = (condicion == null) ? null : condicion.getValor(ent);
            tipoA = condicion.getTipo(ent);
            if (tipoA.isBoolean()) {
                if (a instanceof String) {
                    if (a.toString().equals("verdadero")) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            } else {
                System.out.println("Error de Tipo booleano en Unario : clase IF");
            }
        } else if (condicion instanceof Identificador) {
            a = ((Identificador) condicion).getIdentificador();
            if (ent.get((String) a) != null) {
                Simbolo s = ent.get((String) a);
                tipoA = s.getTipo();
                if (tipoA.isBoolean()) {
                    if (s.getValor().toString().equals("verdadero")) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            } else {
                System.out.println("Error no se encontro identificador en IF");
                flag = false;
            }
        } else {
            //sino es ninguna de las anterires entonces obtenemos el valor de las 
            //operaciones logicas, relacionales 
            flag = (boolean) condicion.getValor(ent);
        }
        //SI flag == true ENTONCES EJECUTAMOS  LAS SENTENCIAS DEL IF
        //creamos un nuevo entorno
        if (flag) {
            return interpretarSetnencias(sentencias1,ent);
        } else if (elseIf != null) {
            //APARTADO PARA ELSE IF 
            return elseIf.ejecutar(ent);
        } else if (hay_else) {
            return interpretarSetnencias(sentencias2,ent);
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private Object interpretarSetnencias(LinkedList<nodoAST> sentencias,Entorno ent){
        Object a = null;
        Tipo tipoA = new Tipo(Tipo.Primitivo.NULL);
        
        //si existe valor  de retorno esta variable hara el trabajo de retornarla como objeto
        Return valRetorno  = null;
        for (nodoAST nodo : sentencias) {
            if (nodo instanceof Instruccion) {
                Instruccion instruccion = (Instruccion) nodo;
                if(instruccion instanceof Break){
                    return null;
                }else {
                    Entorno nuevoEnt = new Entorno(ent); //apuntamos al anterior
                    a = instruccion.ejecutar(nuevoEnt);
                    //si a != nulo es porque una de sus instrucciones retorno un valor
                    if(a != null){
                        RetornoSecundario rs = (RetornoSecundario)a;
                        return rs;
                    }
                }
            } else if (nodo instanceof Return) {
                Expresion e = (Expresion)nodo;
                Entorno nuevoEnt = new Entorno(ent); //apuntamos al anterior
                a = e.getValor(nuevoEnt);
                //si a != nulo es porque fijo trae valores
                if (a != null) {
                    tipoA = e.getTipo(nuevoEnt);
                    RetornoSecundario rs = new RetornoSecundario(a, tipoA, linea);
                    return rs; //:::::::::::::::::::::::::::::::::::::::::
                }
            } else if (nodo instanceof Expresion) {
                Expresion exp = (Expresion) nodo;
                Entorno nuevoEnt = new Entorno(ent); //apuntamos al anterior
                Object b = exp.getValor(nuevoEnt);
                if(b != null){
                    RetornoSecundario rs = (RetornoSecundario)b;
                    return rs;
                }
            } 
        }
        return null;
    }

}
