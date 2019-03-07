/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.Operacion;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Logica extends Operacion implements Expresion{
    

    public Logica(Expresion exp1, Expresion exp2, Operador tipoOperador, int linea) {
        super(exp1, exp2, tipoOperador, linea);
    }

    public Logica(Expresion exp1, Operador tipoOperador, int linea) {
        super(exp1, tipoOperador, linea);
    }
    
    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
        if(t1.isBoolean() && t2.isBoolean())
            return new Tipo(Tipo.Primitivo.BOOLEAN);
        return new Tipo(Tipo.Primitivo.NULL);
    }

    @Override
    public Object getValor(Entorno ent) {
        if(esUnario){
            Object a = (exp1 == null) ? null : exp1.getValor(ent);
            Tipo tipoA = exp1.getTipo(ent);
            if(tipoA.isBoolean()){
                if (a instanceof String){
                    if(a.toString().equals("verdadero"))
                        a = true;
                    else 
                        a = false;
                }
               return (boolean)a?false:true; 
            } else{
                System.out.println("Error en el retorno de getValor Logica");
            }
        } else {
            Object a = (exp1 == null) ? null : exp1.getValor(ent);
            Tipo tipoA = exp1.getTipo(ent);
            Object b = (exp2 == null) ? null : exp2.getValor(ent);
            Tipo tipoB = exp2.getTipo(ent);
            
            tipoResult = tipoResultado(tipoA, tipoB);
            
            
            if(tipoResult.isBoolean()){
                if(a instanceof String){
                    if(a.toString().equals("verdadero"))
                        a = true;
                    else 
                        a = false;
                }
                if(b instanceof String){
                    if(b.toString().equals("verdadero"))
                        b = true;
                    else 
                        b = false;
                }
                switch(tipoOperador){
                    case AND:
                       if((boolean)a && (boolean)b)
                           return true;
                    return false;
                        
                    case OR:
                        if((boolean)a || (boolean)b)
                            return true;
                    return false;
                                          
                }
            } else{
                System.out.println("Error en el retorno de getValor Logica");
            }
            
        }
        return false;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
