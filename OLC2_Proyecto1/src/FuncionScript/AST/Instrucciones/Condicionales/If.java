/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class If  implements Instruccion{


    Expresion condicion;
    LinkedList<nodoAST> sentencias;
    int linea;
    
    public If(Expresion condicion, LinkedList<nodoAST> sentencias, int linea) {
        this.condicion = condicion;
        this.sentencias = sentencias;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno nuevoEnt = new Entorno(ent); //apuntamos al anterior
        
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
