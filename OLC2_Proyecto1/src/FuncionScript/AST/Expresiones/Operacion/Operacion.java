/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.Operacion;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public abstract class Operacion {
    public enum Operador{
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        ELEVACION,
        NEGACION,
        AUMENTO,
        DECREMENTO,
        IGUAL_QUE,
        DIFERENTE_QUE,
        MAYOR_QUE,
        MAYOR_IGUAL_QUE,
        MENOR_QUE,
        MENOR_IGUAL_QUE
    }
    
    Expresion exp1;
    Expresion exp2;
    Operador tipoOperador;
    Tipo tipoResult;
    boolean esUnario;
    int linea;
    
    public Operacion(Expresion exp1,Expresion exp2,Operador tipoOperador,int linea){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.tipoOperador = tipoOperador;
        this.esUnario = false;
        this.linea = linea;
    }
    
    public Operacion(Expresion exp1,Operador tipoOperador, int linea){
        this.exp1 = exp1;
        this.tipoOperador = tipoOperador;
        this.esUnario = true;
        this.linea = linea;
    }
    
    public abstract Tipo tipoResultado(Tipo t1, Tipo t2);
    
}
