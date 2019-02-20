/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instrucciones;

import AST.Expresiones.Expresion;
import Entorno.Entorno;

/**
 *
 * @author rm
 */
public class Asignacion implements Instruccion{

    String identificador;
    Expresion expresion;
    int linea;
    
    public Asignacion(String id,Expresion exp,int linea){
        this.identificador = id;
        this.expresion = exp;
        this.linea = linea;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
