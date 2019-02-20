/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instrucciones;

import AST.Expresiones.Expresion;
import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Asignacion implements Instruccion{

    String identificador;
    Expresion expresion;
    int linea;
    
    public Asignacion(String id,Expresion exp,int linea){
        this.identificador = id;
        this.expresion = exp;
        this.linea = linea;
    }
    @Override
    public Object ejecutar(Entorno ent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object valExp = expresion.getValor(ent);
        Tipo tipoVal = expresion.getTipo(ent);
        
        if(valExp != null){
            if(ent.get(identificador) != null){
                Simbolo s = ent.get(identificador);
                Tipo tipo = s.getTipo();
                if( tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.INT){
                    if(tipoVal.getTipoString() == "INT"){
                        s.setValor(valExp);
                    } else{
                        System.out.println("> Error No se pude asignar otro valor que no sea entero");
                    }
                }
                else if( tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.STRING){
                    if(tipoVal.getTipoString() == "STRING"){
                        s.setValor(valExp);
                    } else{
                        System.out.println("> Error No se pude asignar otro valor que no sea string");
                    }
                }
                else if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.BOOLEAN){
                    if(tipoVal.getTipoString() == "BOOLEAN"){
                        s.setValor(valExp);
                    } else {
                        System.out.println("> Error no se puede asignar otro valor que no sea tipo booleano");
                    }
                }
                
            }else{
                System.out.println("> Error el identificador al que desea asignar valor no existe!!");
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
