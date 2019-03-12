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
    private String identificador;
    private Object valor;
    int linea;
    Tipo tipo;
    
    public Identificador(String id,int linea){
        this.identificador = id;
        this.linea = linea;
    }
    @Override
    public Object getValor(Entorno ent) {
       if(ent.get(getIdentificador()) != null){
           Simbolo s = ent.get(getIdentificador());
           this.tipo = s.getTipo();
           valor = s.getValor();
           return valor;
       } else {
           System.out.println("> Erro el identificador " + getIdentificador() + "no existe.");
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

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
