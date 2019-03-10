/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import java.beans.Expression;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RetornoSecundario  {

    Object valor = null;
    Tipo tipoValor = new Tipo(Tipo.Primitivo.NULL);
    int linea;
    
    public RetornoSecundario(Object valor,Tipo tipo,int linea){
        this.valor = valor;
        this.tipoValor = tipo;
        this.linea = linea;
    }

    public Object getValor(Entorno ent) {
        return valor;
    }

    public Tipo getTipo(Entorno ent) {
        return tipoValor;
    }

    public int getLine() {
        return linea;
    }

    
   
    
}
