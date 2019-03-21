/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class CrearBoton implements Expresion {

    String idContenedor;
    LinkedList<Expresion> parametros;
    int linea;

    public CrearBoton(String idContenedor, LinkedList<Expresion> parametros, int linea) {
        this.idContenedor = idContenedor;
        this.parametros = parametros;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        //VERIFICAMOS QUE LA LISTA DE EXPRESIONES NO SEA 0 
        if (parametros.size() != 0 && parametros.size() == 9) {
            if (ent.get(idContenedor) != null) {
                String fuente = "Agency FB";
                Double tamanio = new Double(10);
                Color color = Color.decode("#000000");
                Double x = new Double(10);;
                Double y = new Double(10);;
                String referencia = ""; //por si no viene referencia
                String valorBoton = "";
                Double alto = new Double(10);;
                Double ancho = new Double(10);;
                String accion = "";

                for (int i = 0; i < parametros.size(); i++) {
                    Object valor = parametros.get(i).getValor(ent);
                    Tipo tipoValor = parametros.get(i).getTipo(ent);

                    if (valor != null) {
                        switch (i) {
                            case 0:
                                fuente = valor.toString();
                                break;

                            case 1:
                                tamanio = new Double(valor.toString());
                                break;

                            case 2:
                                color = Color.decode(valor.toString());
                                break;

                            case 3:
                                x = new Double(valor.toString());
                                break;

                            case 4:
                                y = new Double(valor.toString());
                                break;

                            case 5:
                                referencia = valor.toString();
                                break;
                            case 6:
                                valorBoton = valor.toString();
                                break;
                            case 7:
                                alto = new Double(valor.toString());
                                break;
                            case 8:
                                ancho = new Double(valor.toString());
                                break;

                        }

                    } else {
                        System.out.println("Error uno de los parametros de boton vino nulo algo salio mal en linea " + linea);
                        Editor.insertarTextoConsola("Error uno de los parametros de boton vino nulo algo salio mal en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error uno de los parametros de boton vino nulo algo salio mal  ");
                        //return null;
                    }
                }

                Simbolo cont = ent.get(idContenedor);
                try {
                    JFrame ventana = (JFrame) (ent.get(cont.getValor().toString())).getValor();
                    Component[] components = ventana.getContentPane().getComponents();

                    JPanel panel = null;
                    for (Component c : components) {
                        if (c instanceof JPanel) {
                            panel = (JPanel) c;
                            break;
                        }
                    }
                    if (panel != null) {
                        JButton btn = new JButton(valorBoton);
                        panel.add(btn);
                        btn.setBounds(new Rectangle(x.intValue(), y.intValue(), ancho.intValue(), alto.intValue()));
                        btn.setFont(new Font(fuente, 0, tamanio.intValue()));

                        btn.setName(referencia);
                        btn.setBackground(color);
//                        btn.addActionListener(new ActionListener() {
//
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                System.out.println("Imprimir del bootn hola mundo cuchas");
//                            }
//                        });
                        //ventana.setVisible(true);
                        //no retorna nada por eso es que es una instruccion
                        return ventana;

                    } else {
                        System.out.println("algo salio mal en obtener frame en  etiqueta boton  en linea " + linea);
                        Editor.insertarTextoConsola("algo salio mal en obtener frame en  etiqueta boton   en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "algo salio mal en obtener frame en  etiqueta boton  en linea " + linea);
                    }
                } catch (Exception e) {
                    System.out.println("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                    Editor.insertarTextoConsola("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                }

            } else {
                System.out.println("Error la contenedor con id: " + idContenedor + " no existe para crear boton en linea " + linea);
                Editor.insertarTextoConsola("Error la contenedor con id: " + idContenedor + " no existe para crear boton en linea " + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error la contenedor con id: " + idContenedor + " no existe para crear boton");
            }
        } else {
            System.out.println("Error para crear un nuevo boton debe contener 9 o  este debe contener mas de un parametros en linea " + linea);
            Editor.insertarTextoConsola("Error para crear un nuevo contenedor este debe boton 9 o  mas de un parametros. en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error para crear un nuevo boton este debe contener 9 o  mas de un parametros en linea " + linea);
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return new Tipo(Tipo.TipoGXML.BOTON);
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
