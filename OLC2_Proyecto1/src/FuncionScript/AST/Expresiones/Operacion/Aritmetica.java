/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.Operacion;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
//herramientas multiusos
import herramientas.CadenaString;
/**
 *
 * @author rm
 */
public class Aritmetica extends Operacion implements Expresion{

    public Aritmetica(Expresion exp1, Expresion exp2, Operador tipoOperador,int linea) {
        super(exp1, exp2, tipoOperador,linea);
    }
    
    public Aritmetica(Expresion exp1,Operador tipoOperador,int linea) {
        super(exp1, tipoOperador,linea);
    }

    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
       if(t1.isNumeric() && t2.isNumeric()){            //NUMBER && NUMBER
           return new Tipo(Tipo.Primitivo.NUMBER);
       } else if(t1.isString() && t2.isString()){       //STRING && STRING
           if(tipoOperador == Operador.SUMA)            
               return new Tipo(Tipo.Primitivo.STRING);
           else 
               return new Tipo(Tipo.Primitivo.NULL);
       } else if(t1.isString() || t2.isString()){       //STRING || STRING
           if(t1.isNumeric() || t2.isNumeric()){        //NUMBER || NUMBER
               return new Tipo(Tipo.Primitivo.STRING);
           } else //CUALQUIER OTRA COMBINACION ES ERRONEA
               return new Tipo(Tipo.Primitivo.NULL);
       }
       return new Tipo(Tipo.Primitivo.NULL);               //NULL
    }

    @Override
    public Object getValor(Entorno ent) {
        Object a = (exp1 == null)? null:exp1.getValor(ent);
        Tipo tipoA = exp1.getTipo(ent);
        Object b = (exp2 == null)? null:exp2.getValor(ent);
        Tipo tipoB = exp2.getTipo(ent);
        
        if(tipoA.getTipoPrimitivo() == Tipo.Primitivo.STRING){
            CadenaString cs = new CadenaString();
            if(cs.isCharacter((String)a)){ //COMPROVAMOS SI LA CADENA CONTIENE UN SOLO CARACTER
                if(cs.characterIsLetter((String)a)){ //VERIFICO SI EL CARACTER ES LETRA
                    a = cs.getAsciiCharacter((String)a);
                    tipoA.setTipoPrimitivo(Tipo.Primitivo.NUMBER); 
                }
                    
            }
        }
        if(tipoB.getTipoPrimitivo() == Tipo.Primitivo.STRING){
            CadenaString cs = new CadenaString();
            if(cs.isCharacter((String)b)){ //COMPROVAMOS SI LA CADENA CONTIENE UN SOLO CARACTER
                if(cs.characterIsLetter((String)b)){ //VERIFICO SI EL CARACTER ES LETRA
                    b = cs.getAsciiCharacter((String)b);
                    tipoB.setTipoPrimitivo(Tipo.Primitivo.NUMBER); 
                }
                    
            }
        }
        
        tipoResult = tipoResultado(tipoA, tipoB);
        
        switch(tipoResult.getTipoPrimitivo()){ //si se crea uno nuevo se debe modificar el Tipo tambien
            //::::::::::::::::::::::::::::::::::::::::::;;;;;;;;;;;;;;;;;;;;;;;;;:::::::::::::::::::::::::::::   
            case NUMBER:
                if(tipoOperador == Operador.SUMA){
                     return new Double(a.toString()) + new Double( b.toString());
                } else if(tipoOperador == Operador.RESTA){
                    return new Double(a.toString()) - new Double( b.toString());
                } else if(tipoOperador == Operador.MULTIPLICACION){
                    return new Double(a.toString()) * new Double( b.toString());
                } else if(tipoOperador == Operador.DIVISION){
                    if(new Double( b.toString()) != 0)
                        return new Double(a.toString()) / new Double( b.toString());
                    else
                        System.out.println(">Error el operador 2 no puede dividir debido a que es 0");
                } else if(tipoOperador == Operador.ELEVACION){
                    return Math.pow(new Double(a.toString()), new Double(b.toString()));
                }
                else
                    System.out.println(">El tipo de operacion no esta especificada en el switch de Double");
            break;
            //::::::::::::::::::::::::::::::::::::::::::;;;;;;;;;;;;;;;;;;;;;;;;;:::::::::::::::::::::::::::::
            case STRING:
                if(tipoOperador == Operador.SUMA)
                     return a.toString() +  b.toString();
                else
                    System.out.println(">Error soloo se permiten sumas de Cadenas de caracteres");
            break;
                
            case NULL:
                System.out.println("Error algo salio mal porque vino null en el tipoResult de la aritmetica");
            break;
        }
        return null;
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
