
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
            Tipo tipoVal = initValue.getTipo(ent);
            
            if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.STRING){
                if(tipoVal.getTipoString() == "STRING"){
                    nSimbolo.setValor(val);
                    ent.put(identificador, nSimbolo);
                } else { 
                    System.out.println("> Error no se puede asignar otro valor que no sea string");
                }
            }
            else if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.INT){
                if(tipoVal.getTipoString() == "INT"){
                    nSimbolo.setValor((int)val);
                    ent.put(identificador, nSimbolo);
                } else {
                    System.out.println("> Error no se puede asignar otro valor que no sea entero");
                }
                
            }
            else if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.BOOLEAN){
                if(tipoVal.getTipoString() == "BOOLEAN"){
                    nSimbolo.setValor(val);
                    ent.put(identificador, nSimbolo);
                } else {
                    System.out.println("> Error no se pude asignar otro que no sea booleano");
                }
            } else if(tipo.getTipoPrimitivo() == Tipo.TipoPrimitivo.DOUBLE){
                if(tipoVal.getTipoString() == "DOUBLE" ){
                    nSimbolo.setValor((Double)val);
                    ent.put(identificador, nSimbolo);
                }  else {
                    System.out.println("> Error no se pude asignar otro que no sea double");
                }
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
