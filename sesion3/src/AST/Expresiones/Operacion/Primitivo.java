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
public class Primitivo implements Expresion{

    Object valor;
    Tipo tipo;
    public Primitivo(Object valor,Tipo tipo){
        this.valor = valor;
        this.tipo = tipo;
    }
    @Override
    public Object getValor(Entorno ent) {
        return valor;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
