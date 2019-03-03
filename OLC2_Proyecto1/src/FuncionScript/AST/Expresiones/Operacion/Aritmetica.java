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
public class Aritmetica extends Operacion implements Expresion{

    public Aritmetica(Expresion exp1, Expresion exp2, Operador tipoOperador,int linea) {
        super(exp1, exp2, tipoOperador,linea);
    }
    
    public Aritmetica(Expresion exp1,Operador tipoOperador,int linea) {
        super(exp1, tipoOperador,linea);
    }

    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
       if(t1.isDouble() && t2.isDouble()){              //DOUBLE VS DOUBLE
           return new Tipo(Tipo.TipoFS.DOUBLE);
       } else if(t1.isString() || t2.isString()){       //STRING VS STRING
           if(tipoOperador == Operador.SUMA)            
               return new Tipo(Tipo.TipoFS.STRING);
           else 
               return new Tipo(Tipo.TipoFS.NULL);
       } else if(t1.isInt() && t2.isInt()){             //INT VS INT
           return new Tipo(Tipo.TipoFS.INT);
       } else if(t1.isDouble() || t2.isDouble()){       //DOUBLE OR DOUBLE
           if(t1.isInt() || t2.isInt()){                //INT OR INT
               return new Tipo(Tipo.TipoFS.DOUBLE);
           } else
               return new Tipo(Tipo.TipoFS.NULL);
       }
       return new Tipo(Tipo.TipoFS.NULL);               //NULL
    }

    @Override
    public Object getValor(Entorno ent) {
        Object a = (exp1 == null)? null:exp1.getValor(ent);
        Tipo tipoA = exp1.getTipo(ent);
        Object b = (exp2 == null)? null:exp2.getValor(ent);
        Tipo tipoB = exp2.getTipo(ent);
        
        tipoResult = tipoResultado(tipoA, tipoB);
        
        // por el momeno solo suma resta multiplicacion y division mas adelate se debe agregar lo demas
        switch(tipoResult.getTipo()){ //si se crea uno nuevo se debe modificar el Tipo tambien
            //::::::::::::::::::::::::::::::::::::::::::;;;;;;;;;;;;;;;;;;;;;;;;;:::::::::::::::::::::::::::::
            case INT:
                if(tipoOperador == Operador.SUMA)
                    return new Integer(a.toString()) + new Integer( b.toString());
                else if(tipoOperador == Operador.RESTA)
                    return new Integer(a.toString()) - new Integer( b.toString());
                else if(tipoOperador == Operador.MULTIPLICACION)
                    return new Integer(a.toString()) * new Integer( b.toString());
                else if(tipoOperador == Operador.DIVISION){
                    if(new Integer( b.toString()) != 0)
                        return new Integer(a.toString())/ new Integer( b.toString());
                    else
                        System.out.println(">Error el operador 2 no puede dividir debido a que es 0");
                }
                else
                    System.out.println(">El tipo de operacion no esta especificada en el switch de INT");
            break;
            //::::::::::::::::::::::::::::::::::::::::::;;;;;;;;;;;;;;;;;;;;;;;;;:::::::::::::::::::::::::::::   
            case DOUBLE:
                if(tipoOperador == Operador.SUMA)
                     return new Double(a.toString()) + new Double( b.toString());
                else if(tipoOperador == Operador.RESTA)
                    return new Double(a.toString()) - new Double( b.toString());
                else if(tipoOperador == Operador.MULTIPLICACION)
                    return new Double(a.toString()) * new Double( b.toString());
                else if(tipoOperador == Operador.DIVISION){
                    if(new Double( b.toString()) != 0)
                        return new Double(a.toString()) / new Double( b.toString());
                    else
                        System.out.println(">Error el operador 2 no puede dividir debido a que es 0");
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
