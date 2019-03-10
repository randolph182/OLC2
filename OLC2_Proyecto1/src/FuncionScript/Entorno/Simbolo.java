/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.Entorno;

import FuncionScript.AST.nodoAST;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;


    
    private LinkedList<Simbolo> parametros;
    private LinkedList<nodoAST> instrucciones;
    private int  linea;
    
    public Simbolo(String id,Tipo tipo){
        this.id = id;
        this.tipo = tipo;
    }
    
    public Simbolo(String id,LinkedList<Simbolo> parametros, LinkedList<nodoAST> instrucciones, int linea) {
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.linea = linea;
    }
    
    
    public LinkedList<Simbolo> getParametros() {
    return parametros;
    }

    public void setParametros(LinkedList<Simbolo> parametros) {
        this.parametros = parametros;
    }

    public LinkedList<nodoAST> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<nodoAST> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    
    public String getId(){
        return id;
    }
    
    public Object getValor(){
        return valor;
    }
    
    public void setValor(Object valor){
        this.valor = valor;
    }
    
    public Tipo getTipo(){
        return tipo;
    }
    
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public enum ROL{
        FUNCION,
        VARIABLE,
        
    }
    
}
