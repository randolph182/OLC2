package FuncionScript.AST;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Funcion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
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
        Entorno ts = new Entorno(null);
        for(nodoAST nodo: nodos){
            if(nodo instanceof Instruccion){
                Instruccion instruccion = (Instruccion)nodo;
                
                if(instruccion instanceof Funcion){
                      Funcion funcion = (Funcion)instruccion;
                      funcion.setRol(Simbolo.ROL.FUNCION);
                      ts.putGlobal(funcion.getId(), funcion);
                      
                } else
                    instruccion.ejecutar(ts);
            } else if(nodo instanceof Expresion){
                Expresion exp = (Expresion)nodo;
                exp.getValor(ts);
            }
        }
    }
}
