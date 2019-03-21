/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GenericXML;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class ListaDatos implements Instruccion {
    LinkedList<String> datos;
    int linea;

    public ListaDatos(LinkedList<String> datos, int linea) {
        this.datos = datos;
        this.linea = linea;
    }
    
    
    @Override
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {
        
        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
