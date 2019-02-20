package AST;

import java.util.LinkedList;
import Entorno.*;
import AST.Instrucciones.*;
/**
 *
 * @author rm
 */

public class AST {
    LinkedList<nodoAST> nodos;
    
    public AST(LinkedList<nodoAST> nodos){
        this.nodos = nodos;
    }
    
    public void ejecutar(){
        Entorno global = new Entorno(null);
        for(nodoAST nodo: nodos){
            if(nodo instanceof Instruccion){
                Instruccion instruccion = (Instruccion)nodo;
                instruccion.ejecutar(global);
            }
        }
    }
}
