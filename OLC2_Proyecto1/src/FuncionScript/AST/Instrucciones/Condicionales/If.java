/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.AST.Expresiones.Operacion.Unario;
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
public class If  implements Instruccion{


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
    
    public If(Expresion cond, LinkedList<nodoAST> ins,If tIf, int linea) {
        this.condicion = cond;
        this.sentencias1 = ins;
        this.elseIf = tIf;
        this.linea = linea;
        this.hay_else = false;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object a = null;
        Tipo tipoA = null;
         boolean flag = false;
        if(condicion instanceof Unario){
             a = (condicion == null) ? null : condicion.getValor(ent);
             tipoA = condicion.getTipo(ent);
            if(tipoA.isBoolean()){
                if (a instanceof String){
                    if(a.toString().equals("verdadero"))
                        flag = true;
                    else 
                        flag = false;
                }
            } else{
                System.out.println("Error en el retorno de getValor Logica");
            }
        } else if(condicion instanceof Identificador){
            a = ((Identificador)condicion).getIdentificador();
                if(ent.get((String)a) != null){
                       Simbolo s = ent.get((String)a);
                       tipoA = s.getTipo();
                       if(tipoA.isBoolean()){
                           if(s.getValor().toString().equals("verdadero"))
                               flag = true;
                           else
                               flag = false;
                       }
                } else{
                    System.out.println("Error no se encontro identificador en UF");
                    flag = false;
                }       
        } else{
            flag = (boolean)condicion.getValor(ent);
        }
        Entorno nuevoEnt = new Entorno(ent); //apuntamos al anterior
        if(flag){
            for(nodoAST nodo: sentencias1){
                if(nodo instanceof Instruccion){
                    Instruccion instruccion = (Instruccion)nodo;
                    instruccion.ejecutar(nuevoEnt);
                }
            }
        } else if(elseIf != null){ 
            elseIf.ejecutar(ent);
        }else if(hay_else){
            for(nodoAST nodo: sentencias2){
                if(nodo instanceof Instruccion){
                    Instruccion instruccion = (Instruccion)nodo;
                    instruccion.ejecutar(nuevoEnt);
                }
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
