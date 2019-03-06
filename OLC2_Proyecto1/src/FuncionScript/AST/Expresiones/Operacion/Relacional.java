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
public class Relacional extends Operacion implements Expresion{

    public Relacional(Expresion exp1, Expresion exp2, Operador tipoOperador, int linea) {
        super(exp1, exp2, tipoOperador, linea);
    }
    
    public Relacional(Expresion exp1, Operador tipoOperador, int linea) {
        super(exp1, tipoOperador, linea);
    }

    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
        if(t1.isNumeric() && t2.isNumeric()){
            return new Tipo(Tipo.Primitivo.BOOLEAN);
        } else {
            System.out.println("Error en la comparacion de tipos de Relacionales");
        }
        
        return new Tipo(Tipo.Primitivo.NULL);
    }

    @Override
    public Object getValor(Entorno ent) {
        Object a = (exp1 == null) ? null : exp1.getValor(ent);
        Tipo tipoA = exp1.getTipo(ent);
        Object b = (exp2 == null) ? null : exp2.getValor(ent);
        Tipo tipoB = exp2.getTipo(ent);

        tipoResult = tipoResultado(tipoA, tipoB);
        
        if (tipoResult.isBoolean()){
            switch (tipoOperador) { 
                case IGUAL_QUE:
                    return new Double((String)a) == new Double((String)b);
                case DIFERENTE_QUE:
                    return new Double((String)a) != new Double((String)b);
                case MAYOR_QUE:
                    return new Double((String)a) > new Double((String)b);
                case MAYOR_IGUAL_QUE:
                    return new Double((String)a) >= new Double((String)b);
                case MENOR_QUE:
                    return new Double((String)a) < new Double((String)b);
                case MENOR_IGUAL_QUE:
                    return new Double((String)a) <= new Double((String)b);
            }  
        } else{
            System.out.println("Error no se puede resolver la operacion Relacional");
        }
        return false;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
       return tipoResult;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
