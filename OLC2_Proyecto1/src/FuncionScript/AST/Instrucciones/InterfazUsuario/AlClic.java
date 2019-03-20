/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class AlClic implements Instruccion {

    String idElemento;
    nodoAST elementoEjecutar;
    int linea;

    public AlClic(String idElemento, nodoAST elementoEjecutar, int linea) {
        this.idElemento = idElemento;
        this.elementoEjecutar = elementoEjecutar;
        this.linea = linea;
    }

    @Override
    //El OBJETIVO ES OBTENER EL FRAME Y SUS COMPONENTES Y ENCONTRAR EL ADECUADO Y LUEGO AGREGARLE EL EVENTO
    public Object ejecutar(Entorno ent) {
        if (ent.get(idElemento) != null) {
            Simbolo comp = ent.get(idElemento);
            if (comp.getTipo().getTipoGxml() == Tipo.TipoGXML.BOTON) { //de tipo boton 
                JFrame ventana = (JFrame) comp.getValor();
                Component[] components = ventana.getContentPane().getComponents();

                JPanel panel = null;
                for (Component c : components) {
                    if (c instanceof JPanel) {
                        panel = (JPanel) c;
                        break;
                    }
                }

                if (panel != null) {
                    //BUSCAMOS EL BOTON
                    Component[] componentsPanel = panel.getComponents();
                    JButton btn = null;

                    for (Component c : componentsPanel) {
                        if (c instanceof JButton) {
//                            System.out.println(((JButton)c).getName());
                            if (((JButton) c).getName().equalsIgnoreCase(idElemento)) {
                                btn = (JButton) c;
                                break;
                            }
                        }
                    }

                    if (btn != null) {
                        btn.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(elementoEjecutar instanceof Instruccion)
                                    ((Instruccion)elementoEjecutar).ejecutar(ent);
                                else if(elementoEjecutar instanceof Expresion)
                                    ((Expresion)elementoEjecutar).getValor(ent);
                            }
                        });
                    } else {
                        System.out.println("problemas con encontral el boton del metodo alclick con elementos " + idElemento + " en linea " + linea);
                        Editor.insertarTextoConsola("problemas encontral el boton del metodo alclick con elementos " + idElemento + " en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "problemas con encontral el boton del metodo alclick con elementos " + idElemento + " en linea " + linea);
                    }
                } else {
                    System.out.println("problemas con trabajar en jframe del metodo alclick con elementos " + idElemento + " en linea " + linea);
                    Editor.insertarTextoConsola("problemas con trabajar en jframe del metodo alclick con elementos " + idElemento + " en linea " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "problemas con trabajar en jframe del metodo alclick con elementos " + idElemento + " en linea " + linea);
                }
            }

        } else {
            System.out.println("Error no se encontro el id " + idElemento + "en la tabla de simbolos en linea =" + linea);
            Editor.insertarTextoConsola("Error no se encontro el id " + idElemento + "en la tabla de simbolos en linea =" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error no se encontro el id " + idElemento + "en la tabla de simbolos en linea =" + linea);
        }

        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
