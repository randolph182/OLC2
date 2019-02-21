/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

/**
 *
 * @author rm
 */
public class Tipo {
    
    private TipoPrimitivo tipoPrimitivo;
    
    public Tipo(TipoPrimitivo tipoPrimitivo){
        this.tipoPrimitivo = tipoPrimitivo;
    }
    
    public String getTipoString(){
        switch(this.tipoPrimitivo){
            case INT:
                return "INT";
            case STRING:
                return "STRING";
            case BOOLEAN:
                return "BOOLEAN";
            case DOUBLE:
                return "DOUBLE";
            default:
                break;
        }
        return "";
    }
    
    public TipoPrimitivo getTipoPrimitivo(){
        return tipoPrimitivo;
    }
    
    
    public enum TipoPrimitivo{
        INT,
        BOOLEAN,
        STRING,
        DOUBLE,
        NULL
    }
    
    public boolean isBool(){
        if(tipoPrimitivo == tipoPrimitivo.BOOLEAN)
            return true;
        return false;
    }
    
    public boolean isString(){
        if(tipoPrimitivo == tipoPrimitivo.STRING)
            return true;
        return false;
    }
    
    public boolean isNumeric(){
        if(tipoPrimitivo == tipoPrimitivo.INT || tipoPrimitivo == tipoPrimitivo.DOUBLE)
            return true;
        return false;
    }
    
    public boolean isDouble(){
        if(tipoPrimitivo == tipoPrimitivo.DOUBLE)
            return true;
        return false;
    }
    
    public boolean isInt(){
        if(tipoPrimitivo == tipoPrimitivo.INT)
            return true;
        return false;
    }
}
