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
        NULL,
    }
    
    private TipoFS tipo;
    
    public Tipo(TipoFS tp){
        this.tipo = tp;
    }
    
    public TipoFS getTipo(){
        return tipo;
    }
}
