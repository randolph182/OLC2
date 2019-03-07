/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Operacion.Logica;
import FuncionScript.AST.Expresiones.Operacion.Relacional;
import FuncionScript.AST.Expresiones.Operacion.Unario;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Ternario  implements  Expresion{

    Expresion condicion;
    Expresion isTrue;
    Expresion isFalse;
    int linea;
    
    public Ternario(Expresion condicion, Expresion isTrue, Expresion isFalse, int linea) {
        this.condicion = condicion;
        this.isTrue = isTrue;
        this.isFalse = isFalse;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        boolean tmp = false;
        Object a = null;
        Tipo tipoA = null;
        
        if(condicion instanceof Unario){
             a = null;
             tipoA = null;
            a = (condicion == null) ? null : condicion.getValor(ent);
            tipoA= condicion.getTipo(ent);
            if(tipoA.isBoolean()){
                if(a.toString().equals("verdadero"))
                    tmp =  true;
                else
                    tmp = false;
            }
        } else if(condicion instanceof Identificador){
            a = ((Identificador)condicion).getIdentificador();
             if(ent.get((String)a) != null){
                    Simbolo s = ent.get((String)a);
                    tipoA = s.getTipo();
                    if(tipoA.isBoolean()){
                        if(s.getValor().toString().equals("verdadero"))
                             tmp =  true;
                        else
                            tmp = false;
                    }
             } else{
                 System.out.println("Error no se encontro identificador en Ternario");
                 tmp = false;
             }       
        }else{
            tmp = (boolean)condicion.getValor(ent);
        }
        
      return  tmp?isTrue.getValor(ent):isFalse.getValor(ent);
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        boolean tmp = false;
        Object a = null;
        Tipo tipoA = null;
        
        if(condicion instanceof Unario){
             a = null;
             tipoA = null;
            a = (condicion == null) ? null : condicion.getValor(ent);
            tipoA= condicion.getTipo(ent);
            if(tipoA.isBoolean()){
                if(a.toString().equals("verdadero"))
                    tmp =  true;
                else
                    tmp = false;
            }
        } else if(condicion instanceof Identificador){
            a = ((Identificador)condicion).getIdentificador();
             if(ent.get((String)a) != null){
                    Simbolo s = ent.get((String)a);
                    tipoA = s.getTipo();
                    if(tipoA.isBoolean()){
                        if(s.getValor().toString().equals("verdadero"))
                             tmp =  true;
                        else
                            tmp = false;
                    }
             } else{
                 System.out.println("Error no se encontro identificador en Ternario");
                 tmp = false;
             }       
        }else{
            tmp = (boolean)condicion.getValor(ent);
        }
       return  tmp?isTrue.getTipo(ent):isFalse.getTipo(ent);
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
