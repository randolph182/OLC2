/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones.InterfazUsuario;

import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import javax.swing.JFrame;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class AlCargar implements Instruccion{

    String idVentana;
    int linea;

    public AlCargar(String idVentana, int linea) {
        this.idVentana = idVentana;
        this.linea = linea;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        //verificamos antes que exista la entana
        if(ent.get(idVentana)!=null){
            Simbolo sim = ent.get(idVentana);
            //obtenemos su frame
            JFrame ventana =(JFrame)sim.getValor();
            if(ventana != null){
                ventana.setVisible(true);
            }
            
        }else{
             System.out.println("Error no se encontro el id " + idVentana + "en la tabla de simbolos en linea =" + linea);
            Editor.insertarTextoConsola("Error no se encontro el id " + idVentana + "en la tabla de simbolos en linea =" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error no se encontro el id " + idVentana + "en la tabla de simbolos en linea =" + linea);
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
