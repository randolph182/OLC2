/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

import FuncionScript.Entorno.Entorno;
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
        //creacion de archivo 
        
        for (nodoAST nodo : nodos) {
            if (nodo instanceof Instruccion) {
                Instruccion instruccion = (Instruccion) nodo;

                instruccion.Traducir(fichero, ts) ;
//                if(instruccion instanceof Ventana){
                        //                      Funcion funcion = (Funcion)instruccion;
                        //                      ts.putGlobal(funcion.getId(), funcion);
                        //                } else
                        //                    instruccion.ejecutar(ts);
                        //            } else if(nodo instanceof Expresion){
                        //                Expresion exp = (Expresion)nodo;
                        //                exp.getValor(ts);
                        //            }
            }
        }
    }

}
