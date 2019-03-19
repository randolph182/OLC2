/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class ObjetoLlamada implements Expresion {


    String idObjeto;
    String idValor;
    Tipo tipo;
    int linea;

    public ObjetoLlamada(String idObjeto, String idValor, int linea) {
        this.idObjeto = idObjeto;
        this.idValor = idValor;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno ent) {
        //PRIMERO VERIFICAMOS QUE EL ID OBJETO EXISTA EN LA TABLA DE SIMBOLOS
        if(ent.get(idObjeto) != null){
            Simbolo obj = ent.get(idObjeto);
            //hacemos listado de todos sus componenetes para saber si contiene lo que se le solicita
            for(Simbolo s:obj.getElementos()){
                if(s.getId().equalsIgnoreCase(idValor)){
                    tipo = s.getTipo();
                    return s.getValor();
                }
            }
            
        }else{
            System.out.println("Error el identificador = "+idObjeto+"no existe en la tabla de simbolos. Error en linea:"+ linea);
            Editor.insertarTextoConsola("Error el identificador = "+idObjeto+"no existe en la tabla de simbolos. Error en linea:"+ linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"Error el identificador = "+idObjeto+"no existe en la tabla de simbolos.");
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
