/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Return;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import java.util.LinkedList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Funcion  implements Instruccion{
    
    LinkedList<Simbolo> parametros;
    LinkedList<nodoAST> instrucciones;
    String id;
    int linea;

    public Funcion(String id,LinkedList<Simbolo> parametros, LinkedList<nodoAST> instrucciones, int linea) {
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.linea = linea;
    }

    
    @Override
    public Object ejecutar(Entorno ent) {
        // ingreso los parametros de la funcion a la tabla de simbolos actual
        for (Simbolo parametro : parametros) {
            if(ent.get(parametro.getId()) != null){
                System.out.println("Error ya existe el parametro en la tabla de simbolos algo salio mal");
            } else{
                ent.put(parametro.getId(), parametro);
            }
        }
        
        for (nodoAST nodo: instrucciones) {
            if(nodo instanceof Instruccion){
                Instruccion instruccion = (Instruccion) nodo;
                if(instruccion instanceof Funcion){
                    //NO HACE NADA 
                } else if(instruccion instanceof Break){
                    return null;
                } else {
                    instruccion.ejecutar(ent);
                }
            } else if(nodo instanceof Return){
                System.out.println("Aun no definido pero ya mero ");
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
