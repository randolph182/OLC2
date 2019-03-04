/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;

import herramientas.CadenaString;
/**
 *
 * @author rm
 */
public class Aumento implements  Instruccion{
    String id;
    int linea;
    
    public Aumento(String id, int linea) {
        this.id = id;
        this.linea = linea;
    }


    @Override
    public Object ejecutar(Entorno ent) {
        if(ent.get(id) != null){
           Simbolo s = ent.get(id);
           Tipo tipoAumen = s.getTipo();
           
           switch(tipoAumen.getTipoPrimitivo()){
               case NUMBER:
                   Double valorAumen = new Double(s.getValor().toString());
                   valorAumen++;
                   s.setValor(valorAumen);
               break;
               
               case STRING:
                   CadenaString cs = new CadenaString();
                   if(cs.isCharacter((String)s.getValor())){
                       if(cs.characterIsLetter((String)s.getValor())){
                           s.setValor(cs.aumentarCharacter((String)s.getValor()));
                       }
                   }
               break;
           }
       } else {
           System.out.println("> Erro el identificador " + id + "no fue declarado");
       }
       return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
