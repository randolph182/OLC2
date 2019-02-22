/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Declaracion implements Instruccion{
    String id;
    Tipo tipo;
    Expresion exp;
    int linea;
    
    public Declaracion(String id,Tipo tipo,Expresion exp,int linea){
        this.id = id;
        this.tipo = tipo;
        this.exp = exp;
        this.linea = linea;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
       if(ent.get(id) != null){ //preguntamos antes si existe la variable que se va a crear acorde al ambito actual
           System.out.println(">ERROR: la variable que se intenta declarar ya existe.");
       } else{ //sino existe se crea
           Simbolo nuevoSimbolo = new Simbolo(id, tipo);
           Object val = exp.getValor(ent);
       }
       return null;
    }

    @Override
    public int getLine() {
       return linea;
    }
    
}
