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
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    
    public Simbolo(String id,Tipo tipo){
        this.id = id;
        this.tipo = tipo;
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
}
