/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;



/**
 * 
 * @author randolph muy
 */
public class CrearTexto implements Expresion{
    LinkedList<Expresion> parametros;
    String idContenedor;
    int linea;

    public CrearTexto(LinkedList<Expresion> parametros, String idContenedor, int linea) {
        this.parametros = parametros;
        this.idContenedor = idContenedor;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno ent) {
        return null;
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
