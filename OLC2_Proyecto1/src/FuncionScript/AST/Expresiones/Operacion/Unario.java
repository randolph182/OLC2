/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.Operacion;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Unario implements Expresion{
    Object valor;
    Tipo tipo;
    int linea;
    public Unario(Object valor,Tipo tipo,int linea){
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno ent) {
        return valor;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        return linea;
    }

    
}
