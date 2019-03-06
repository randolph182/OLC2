/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Operacion.Logica;
import FuncionScript.AST.Expresiones.Operacion.Relacional;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
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
        if(!(condicion instanceof Logica) || !(condicion instanceof Relacional)){
            System.out.println("Error");
        }
        this.condicion = condicion;
        this.isTrue = isTrue;
        this.isFalse = isFalse;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
      return  (boolean)condicion.getValor(ent)?isTrue.getValor(ent):isFalse.getValor(ent);
//        boolean b = (boolean)condicion.getValor(ent);
//        
//        return b;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
       return  (boolean)condicion.getValor(ent)?isTrue.getTipo(ent):isFalse.getTipo(ent);
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
