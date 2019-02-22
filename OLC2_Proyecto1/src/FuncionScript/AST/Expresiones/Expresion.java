/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public interface Expresion extends nodoAST{
    Object getValor(Entorno ent);
    Tipo getTipo(Entorno ent);
}
