/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;

/**
 *
 * @author rm
 */
public class Imprimir implements Instruccion{

    Expresion contenido;
    int linea;
    
    public Imprimir(Expresion contenido, int linea) {
    this.contenido = contenido;
    this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object valor = contenido.getValor(ent);
        if(valor != null){
            System.out.println("> "+ valor.toString());
        } else {
            System.out.println("Hubo error en imprimir");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
