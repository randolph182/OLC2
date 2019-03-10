/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Return implements Expresion{


    Expresion valRet;
    int linea;
    boolean retornaValor = false;
    Tipo tipo;
    
    public Return(Expresion valRet, int linea) {
        this.valRet = valRet;
        this.linea = linea;
        this.retornaValor = true;
    }
    
    public Return(int linea){
        this.valRet = null;
        this.linea = linea;
        this.retornaValor = false;
    }
    
    @Override
    public Object getValor(Entorno ent) {
        if(retornaValor){ //si vino una expresin para retornar
            Object a = valRet.getValor(ent);
            tipo = valRet.getTipo(ent);
            return a;
        }
         return null;  
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        return linea;
    }
    

}
