/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instrucciones;

import Entorno.*;
import AST.Expresiones.*;
/**
 *
 * @author rm
 */
public class Declaracion implements Instruccion{
    Tipo tipo;
    String identificador;
    Expresion initValue;
    int linea;
    
    public Declaracion(Tipo tipo, String id,Expresion initValue,int linea){
        this.tipo = tipo;
        this.identificador = id;
        this.initValue = initValue;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        if(ent.get(identificador)!= null){
            System.out.println("ERROR: la variable que se intenta declarar ya existe!!");   
        } else {
            Simbolo nSimbolo = new Simbolo(identificador, tipo);
            Object val = initValue.getValor(ent);
            if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.STRING){
                nSimbolo.setValor(val);
                ent.put(identificador, nSimbolo);
            }
            else if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.INT){
                nSimbolo.setValor(val);
                ent.put(identificador, nSimbolo);
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
