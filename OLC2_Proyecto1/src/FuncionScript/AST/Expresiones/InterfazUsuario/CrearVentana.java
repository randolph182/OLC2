/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JFrame;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */

/* Esta clase retorna un tipo simbolo */
public class CrearVentana implements Expresion {

    Tipo tipo;
    LinkedList<Expresion> parametros;
    int linea;

    public CrearVentana(LinkedList<Expresion> parametros, int linea) {
        this.parametros = parametros;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        if (parametros.size() != 0 && parametros.size() == 4) {
            Color colorVentana = new Color(0);
            Double alto = new Double(0);
            Double ancho = new Double(0);
            String idVentana = "";
            for (int i = 0; i < parametros.size(); i++) {
                Object valor = parametros.get(i).getValor(ent);
                Tipo tipo = parametros.get(i).getTipo(ent);
                
                switch (i) {
                    case 0:
                        colorVentana = Color.decode(valor.toString());
                        break;
                    case 1:
                        alto = new Double(valor.toString());
                        break;
                        
                    case 2:
                        ancho = new Double(valor.toString());
                        break;
                        
                    case 3:
                        idVentana = valor.toString();
                        break;
                }
            }
            
            JFrame ventana = new JFrame(idVentana);
            ventana.getContentPane().setBackground(colorVentana);
            ventana.setLocationRelativeTo(null);
            ventana.setSize(alto.intValue(),ancho.intValue());
            ventana.setLayout(null);
            return ventana; 

        } else {
            System.out.println("El numero de parametros debe contener 4 elementos  en linea " + linea);
            Editor.insertarTextoConsola("El numero de parametros debe contener 4 elementos  en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El numero de parametros debe contener 4 elementos  en linea " + linea);
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
