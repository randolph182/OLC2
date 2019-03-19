/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class CrearCajaTexto implements Instruccion{

    LinkedList<Expresion> parametros;
    String idContenedor;
    int linea;

    public CrearCajaTexto(LinkedList<Expresion> parametros, String idContenedor, int linea) {
        this.parametros = parametros;
        this.idContenedor = idContenedor;
        this.linea = linea;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        if (parametros.size() != 0 && parametros.size() == 11) {
             if (ent.get(idContenedor) != null) {
                Double alto = new Double(10);
                Double ancho = new Double(10);
                String fuente = "Agency FB";
                Double tamanio = new Double(5);
                Color color = Color.decode("#000000");
                Double posX = new Double(10);
                Double posY = new Double(10);
                boolean negrilla = false;
                boolean cursiva = false;
                String defecto = "";
                String nombre = "";
                
                for (int i = 0; i < parametros.size(); i++) {
                    Object valor = parametros.get(i).getValor(ent);
                    Tipo tipoValor = parametros.get(i).getTipo(ent);
                    if (valor != null) {

                        switch (i) {
                            case 0:
                                alto = new Double(valor.toString());
                                break;
                            case 1:
                                ancho = new Double(valor.toString());
                                break;
                            case 2:
                                fuente = valor.toString();
                                break;

                            case 3:
                                tamanio = new Double(valor.toString());
                                break;

                            case 4:
                                color = Color.decode(valor.toString());
                                break;

                            case 5:
                                posX = new Double(valor.toString());
                                break;

                            case 6:
                                posY = new Double(valor.toString());
                                break;
                            case 7:
                                if (valor.toString().equals("verdadero")) {
                                    negrilla = true;
                                } else {
                                    negrilla = false;
                                }
                                break;
                            case 8:
                                if (valor.toString().equals("verdadero")) {
                                    cursiva = true;
                                } else {
                                    cursiva = false;
                                }
                                break;

                            case 9:
                                defecto = valor.toString();
                                break;
                            case 10:
                                nombre = valor.toString();
                                break;
                        }

                    } else {
                        System.out.println("Error uno de los parametros de la etiqueta caja de texto vino nulo algo salio mal en linea " + linea);
                        Editor.insertarTextoConsola("Error uno de los parametros de la etiqueta caja de texto  vino nulo algo salio mal en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error uno de los parametros de la etiqueta caja de texto vino nulo algo salio mal  ");
                        //return null; //recuperacion
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
                        JTextField tf = new JTextField(defecto);
                        panel.add(tf);
                        tf.setBounds(new Rectangle(posX.intValue(), posY.intValue(), ancho.intValue(), alto.intValue()));
                        if(negrilla ){
                            tf.setFont(new Font(fuente, Font.BOLD, tamanio.intValue()));
                        } 
                        if(cursiva){
                            tf.setFont(new Font(fuente,  Font.ITALIC, tamanio.intValue()));
                        }
                        if(!negrilla && !cursiva)
                        {
                            tf.setFont(new Font(fuente, 0, tamanio.intValue()));
                        }
                        tf.setCaretColor(color);
                        
                        ventana.setVisible(true);
                        //no retorna nada por eso es que es una instruccion
                        return null;

                    } else {
                        System.out.println("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                        Editor.insertarTextoConsola("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                    }
                } catch (Exception e) {
                    System.out.println("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                    Editor.insertarTextoConsola("algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "algo salio mal en obtener frame en  etiqueta texto  en linea " + linea);
                }
                

             }else {
                System.out.println("Error el contenedor con id: " + idContenedor + " no existe para crear etiqueta caja de texto en linea " + linea);
                Editor.insertarTextoConsola("Error el contenedor con id: " + idContenedor + " no existe para crear etiqueta caja de texto en linea " + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error el contenedor con id: " + idContenedor + " no existe para crear etiqueta caja de texto");
            }
        }else{
            System.out.println("Error para crear una nueva etiqueta caja de texto debe contener 11  o  este debe contener mas de un parametros en linea " + linea);
            Editor.insertarTextoConsola("Error para crear una nueva etiqueta texto este debe contener 8 o  mas de un parametros. en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error para crear una nueva etiqueta texto este debe contener 8 o  mas de un parametros en linea " + linea);
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
