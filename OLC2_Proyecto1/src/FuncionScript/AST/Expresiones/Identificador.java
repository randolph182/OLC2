/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Identificador implements Expresion{
    String identificador;
    int linea;
    Tipo tipo;
    Object valor;
    
    public Identificador(String id,int linea){
        this.identificador = id;
        this.linea = linea;
    }
    @Override
    public Object getValor(Entorno ent) {
       if(ent.get(identificador) != null){
           Simbolo s = ent.get(identificador);
           this.tipo = s.getTipo();
           return s.getValor();
       } else {
           System.out.println("> Erro el identificador " + identificador + "no existe.");
       }
       return null;
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
