/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

import java.util.Hashtable;

/**
 *
 * @author rm
 */
public class Entorno {
    public Hashtable tabla;
    protected Entorno anterior;
    
    public Entorno(Entorno p){
        tabla = new Hashtable();
        anterior = p;
    }
    
    public void put(String llv,Simbolo sim){
        tabla.put(llv, sim);
    }
    
    public Simbolo get(String llv){
        for(Entorno e = this; e!=null; e = e.anterior){
            Simbolo encontrado = (Simbolo)(e.tabla.get(e));
            if(encontrado != null)
                return encontrado;
        }
        return null;
    }
    
}
