/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones;
import AST.nodoAST;
import Entorno.*;
/**
 *
 * @author rm
 */
public interface Expresion extends nodoAST{
    Object getValor(Entorno ent);
    Tipo getTipo(Entorno ent);
}
