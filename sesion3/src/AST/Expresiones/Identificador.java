/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Identificador implements Expresion{
    String identificador;
    int linea;
    Tipo tipo;
    Object valor;
    
    public Identificador(String id,int linea){
        this.identificador = id;
        this.linea = linea;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(ent.get(identificador) != null){
            Simbolo s = ent.get(identificador);
            return s.getValor();
        }else{
            System.out.println("> Error el identificador no existe!!");
        }
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
