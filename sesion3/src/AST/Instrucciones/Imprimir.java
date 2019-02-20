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
public class Imprimir implements Instruccion{

    Expresion contenido;
    int linea;
    
    public Imprimir(Expresion contenido,int linea){
        this.contenido = contenido;
        this.linea = linea;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        Object valor = contenido.getValor(ent);
        if(valor != null){
            System.out.println("> " + valor.toString());
        } else {
            System.out.println("Hubo error en Imprimir...");
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
