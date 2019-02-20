/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Instrucciones;
import AST.nodoAST;
import Entorno.*;
/**
 *
 * @author rm
 */
public interface Instruccion extends nodoAST{
    Object ejecutar(Entorno ent);
}
