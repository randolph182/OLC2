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

    /**
     * @return the tipoGxml
     */
    public TipoGXML getTipoGxml() {
        return tipoGxml;
    }

    /**
     * @param tipoGxml the tipoGxml to set
     */
    public void setTipoGxml(TipoGXML tipoGxml) {
        this.tipoGxml = tipoGxml;
    }
    public enum Primitivo{
        STRING,
        OBJECT,
        NUMBER,
        BOOLEAN,
        NULL
    }
    
    public enum TipoGXML{
        VENTANA,
        CONTENEDOR,
        TEXTO,
        CONTROL,
        BOTON,
        MULTIMEDIA,
        LISTA_DATOS,
        ENVIAR,
        CONTROL_TEXTO
        
    }
    
    private Primitivo tipo = null;
    private TipoGXML tipoGxml = null;
    
    
    
    
    public Tipo(Primitivo tp){
        this.tipo = tp;
    }
    
    public Tipo(TipoGXML tp){
        this.tipoGxml = tp;
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
        if(tipo == Primitivo.NULL){
            return true;
        }
        return false;
    }
    
    public boolean isBoolean(){
        if(tipo == Primitivo.BOOLEAN){
            return true;
        }
        return false;
    }
    
    public boolean isNumeric(){
        if(tipo == Primitivo.NUMBER){
            return true;
        }
        return false;
    }
    
    public boolean esTipoPrimitivo(){
        if(tipo != null)
            return true;
        else return false;
    }
    
    public boolean esTipoGXML(){
        if(tipoGxml != null)
            return true;
        else return false;
    }
}
