/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.nodoAST;
import java.util.LinkedList;


public class Caso {
    Expresion expresion;
    LinkedList<nodoAST> instrucciones;
    public int linea;
    
    public Caso(Expresion exp,LinkedList<nodoAST> ins,int linea){
        this.expresion = exp;
        this.instrucciones = ins;
        this.linea = linea;
    }
    
    public Caso(LinkedList<nodoAST> ins,int linea){ //el ultimo de la linked list
        this.instrucciones = ins;
        this.linea = linea;
    }
    
}
