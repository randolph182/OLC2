/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2_tarea1;

/**
 *
 * @author rm
 */
import java.util.*;
import olc2_tarea1.Simbolo;
public class Entorno {
    public Hashtable tabla;
    protected Entorno anterior;
    
    public Entorno(Entorno p){
        tabla = new Hashtable();
        anterior = p;
    }
    
    public void put(String s,Simbolo sim){
        tabla.put(s, sim);
    }
    
    public Simbolo get(String s){
        for(Entorno e = this; e != null; e = e.anterior){
            Simbolo encontrado = (Simbolo)(e.tabla.get(e));
            if(encontrado != null)
                return encontrado;
        }
        return null;
    }
    

}
