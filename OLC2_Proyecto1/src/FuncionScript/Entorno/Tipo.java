/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.Entorno;

/**
 *
 * @author rm
 */
public class Tipo {
    public enum Primitivo{
        STRING,
        OBJECT,
        NUMBER,
        BOOLEAN,
        NULL
    }
    
    private Primitivo tipo;
    
    public Tipo(Primitivo tp){
        this.tipo = tp;
    }
    
    public Primitivo getTipoPrimitivo(){
        return tipo;
    }
    
    public void setTipoPrimitivo(Primitivo tipo){
        this.tipo = tipo;
    }
    
    public boolean isString(){
        if(tipo == Primitivo.STRING)
            return true;
        return false;
    }
    
    public boolean isObject(){
        return false;
    }
    
    public boolean isNull(){
        return false;
    }
    
    public boolean isBoolean(){
        return false;
    }
    
    public boolean isNumeric(){
        if(tipo == Primitivo.NUMBER){
            return true;
        }
        return false;
    }
}
