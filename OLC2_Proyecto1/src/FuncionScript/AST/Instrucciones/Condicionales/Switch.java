/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;

/**
 *
 * @author rm
 */
public class Switch implements Instruccion{

    Expresion expresion;
    
    @Override
    public Object ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
