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
    public enum TipoFS{
        INT,
        STRING,
        DOUBLE,
        OBJECT,
        NUMBER,
        BOOLEAN,
        NULL
    }
    
    private TipoFS tipo;
    
    public Tipo(TipoFS tp){
        this.tipo = tp;
    }
    
    public TipoFS getTipo(){
        return tipo;
    }
    
    public boolean isInt(){
        if(tipo == TipoFS.INT)
            return true;
        return false;
    }
    
    public boolean isDouble(){
        if(tipo == TipoFS.DOUBLE)
            return true;
        return false;
    }
    
    public boolean isString(){
        if(tipo == TipoFS.STRING)
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
}
