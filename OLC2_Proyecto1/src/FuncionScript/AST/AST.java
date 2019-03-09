package FuncionScript.AST;

import FuncionScript.AST.Instrucciones.Funcion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import java.util.LinkedList;

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
                
                if(instruccion instanceof Funcion){
                      
                }
                instruccion.ejecutar(global);
            }
        }
    }
}
