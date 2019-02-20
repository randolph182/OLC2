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
        STRING
    }
}
