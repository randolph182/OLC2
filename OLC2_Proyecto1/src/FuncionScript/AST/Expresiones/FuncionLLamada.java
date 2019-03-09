/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FuncionLLamada implements Expresion{
    LinkedList<Expresion> parametros;
    String id;
    int linea;

    public FuncionLLamada(LinkedList<Expresion> parametros, String id, int linea) {
        this.parametros = parametros;
        this.id = id;
        this.linea = linea;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
