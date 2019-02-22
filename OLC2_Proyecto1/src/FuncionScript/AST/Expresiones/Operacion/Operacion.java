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
        NEGACION
    }
    
    Expresion exp1;
    Expresion exp2;
    Operador tipoOperador;
    Tipo tipoResult;
    boolean esUnario;
    
    public Operacion(Expresion exp1,Expresion exp2,Operador tipoOperador){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.tipoOperador = tipoOperador;
        this.esUnario = false;
    }
    
    public Operacion(Expresion exp1,Operador tipoOperador){
        this.exp1 = exp1;
        this.tipoOperador = tipoOperador;
        this.esUnario = true;
    }
    
    public abstract Tipo tipoResultado(Tipo t1, Tipo t2);
    
}
