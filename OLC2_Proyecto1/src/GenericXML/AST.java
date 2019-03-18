/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import java.util.LinkedList;
import GenericXML.nodoAST;
import java.io.FileWriter;

/**
 *
 * @author randolph muy
 */
public class AST {

    LinkedList<nodoAST> nodos;

    public AST(LinkedList<nodoAST> nodos) {
        this.nodos = nodos;
    }

    public void traducir(FileWriter fichero) {
        Entorno ts = new Entorno(null);
        ts.resetInstanciaGlobal();
        for (nodoAST nodo : nodos) {
            if (nodo instanceof Instruccion) {
                Instruccion instruccion = (Instruccion) nodo;
                instruccion.ejecutar(fichero,null, ts,0) ;
            }
        }
    }
    
    public void getArray(LinkedList<Simbolo> listadoSimbolos){
        Entorno ts = new Entorno(null);
        ts.resetInstanciaGlobal();
        for (nodoAST nodo : nodos) {
            if (nodo instanceof Instruccion) {
                Instruccion instruccion = (Instruccion) nodo;
                instruccion.ejecutar(null,listadoSimbolos,ts,1);
            }
        }
    }

}
