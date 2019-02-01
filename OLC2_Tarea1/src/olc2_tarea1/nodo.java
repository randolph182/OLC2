/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2_tarea1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rm
 */
public class nodo {
    public nodo(){}
    public String etiqueta;
    public String valor;
    public int idNodo;
  
    public List<nodo> hijos = new ArrayList<nodo>();
    public void addHijo(nodo hijo)
    {
        hijos.add(hijo);
    }
}


