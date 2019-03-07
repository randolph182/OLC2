/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.Operacion;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import herramientas.CadenaString;

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
        if(t1.isNumeric() && t2.isNumeric()){   //PARA COMPARAR SOLO NUMERICOS
            return new Tipo(Tipo.Primitivo.BOOLEAN);
        } else if(t1.isBoolean() && t2.isBoolean()){
           return new Tipo(Tipo.Primitivo.BOOLEAN);
        }else {
            System.out.println("Error en la comparacion de tipos de Relacionales");
            //error semantico
        }
        
        return new Tipo(Tipo.Primitivo.NULL);
    }

    @Override
    public Object getValor(Entorno ent) {
        
        Object a = (exp1 == null) ? null : exp1.getValor(ent);
        Tipo tipoA = exp1.getTipo(ent);
        Object b = (exp2 == null) ? null : exp2.getValor(ent);
        Tipo tipoB = exp2.getTipo(ent);

        if (tipoA.isString()) { 
            CadenaString cs = new CadenaString();
            a = cs.getAsciiFromString(a.toString());
            tipoA = new Tipo(Tipo.Primitivo.NUMBER);
        }
        if (tipoB.isString()) {
            CadenaString cs = new CadenaString();
            b = cs.getAsciiFromString(b.toString());
            tipoB = new Tipo(Tipo.Primitivo.NUMBER);
        }
        tipoResult = tipoResultado(tipoA, tipoB);
        
        if (tipoResult.isBoolean()){ 
            //aca por cada Operador debo preguntar si es booleano porque de lo 
            //contrario siempre van a ser numericos los Operandos
            Object t1 ;
            Object t2 ;
            switch (tipoOperador) {
                case IGUAL_QUE:
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                        if(a.toString().equals("verdadero"))
                            t1 = true;
                        else
                            t1 = false;
                        if(b.toString().equals("verdadero"))
                            t2 = true;
                        else
                            t2 = false;
                        return (boolean)t1 == (boolean)t2;
                    }
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 == (double)t2;
                    
                case DIFERENTE_QUE:    
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                        if(a.toString().equals("verdadero"))
                            t1 = true;
                        else
                            t1 = false;
                        if(b.toString().equals("verdadero"))
                            t2 = true;
                        else
                            t2 = false;
                        return (boolean)t1 != (boolean)t2;
                    }
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 != (double)t2;  
                
                case MAYOR_QUE:
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                      if(a.toString().equals("verdadero"))
                            t1 = 1;
                        else
                            t1 = 0;
                        if(b.toString().equals("verdadero"))
                            t2 = 1;
                        else
                            t2 = 0;
                        return (int)t1 > (int)t2;
                    } 
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 > (double)t2;                      
                    
                case MAYOR_IGUAL_QUE:
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                      if(a.toString().equals("verdadero"))
                            t1 = 1;
                        else
                            t1 = 0;
                        if(b.toString().equals("verdadero"))
                            t2 = 1;
                        else
                            t2 = 0;
                        return (int)t1 >= (int)t2;
                    } 
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 >= (double)t2;  
                
                case MENOR_QUE:
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                      if(a.toString().equals("verdadero"))
                            t1 = 1;
                        else
                            t1 = 0;
                        if(b.toString().equals("verdadero"))
                            t2 = 1;
                        else
                            t2 = 0;
                        return (int)t1 < (int)t2;
                    } 
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 < (double)t2;
                    
                case MENOR_IGUAL_QUE:
                    if(tipoA.isBoolean()){ //con uno que sea booleano
                      if(a.toString().equals("verdadero"))
                            t1 = 1;
                        else
                            t1 = 0;
                        if(b.toString().equals("verdadero"))
                            t2 = 1;
                        else
                            t2 = 0;
                        return (int)t1 <= (int)t2;
                    } 
                    t1 = new Double(a.toString());
                    t2 = new Double(b.toString());
                return (double)t1 <= (double)t2;
                    
                default:
                    System.out.println("problemas con el tipo de operacion en Relacionales ya que no se relaciono con ninguna");
                    return false;
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
