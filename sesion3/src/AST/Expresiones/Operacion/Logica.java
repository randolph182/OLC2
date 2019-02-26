/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones.Operacion;

import AST.Expresiones.Expresion;
import Entorno.Entorno;
import Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Logica extends Operacion implements Expresion{

    public Logica(Expresion exp1, Expresion exp2, Operador tipoOperador) {
        super(exp1, exp2, tipoOperador);
    }

    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValor(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
