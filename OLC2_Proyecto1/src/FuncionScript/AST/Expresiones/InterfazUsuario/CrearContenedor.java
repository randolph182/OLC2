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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class CrearContenedor implements Expresion{
    LinkedList<Expresion> parametros;
    int linea;
    String idVentana;

    public CrearContenedor(LinkedList<Expresion> parametros, String idVentana, int linea) {
        this.parametros = parametros;
        this.linea = linea;
        this.idVentana = idVentana;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
       //VERIFICAMOS QUE LA LISTA DE EXPRESIONES NO SEA 0 
       if(parametros.size() != 0 && parametros.size() == 6){
           //OBTENIENDO EL ID DEL FRAME DE LA VENTANA
           if(ent.get(idVentana) != null){
                Double alto  = new Double(10);
                Double ancho = new Double(10);
                Color colorContenedor = Color.decode("#999999");
                boolean borde = false;
                Double posX =  new Double(10);
                Double posY = new Double(10);
                
                for (int i = 0; i < parametros.size(); i++) {
                   Object valor = parametros.get(i).getValor(ent);
                   Tipo tipoValor = parametros.get(i).getTipo(ent);
                   if(valor != null){
                       
                       switch(i){
                           case 0:
                               alto = new Double(valor.toString());
                           break;
                           
                           case 1:
                               ancho = new Double(valor.toString());
                           break;
                               
                           case 2:
                               colorContenedor = Color.decode(valor.toString());
                           break;
                               
                           case 3:
                               if(valor.toString().equals("verdadero"))
                                   borde = true;
                               else
                                   borde = false;
                           break;
                               
                           case 4:
                               posX = new Double(valor.toString());
                           break;
                               
                           case 5:
                               posY = new Double(valor.toString());
                           break;
                       }
                       
                   }else{
                        System.out.println("Error uno de los parametros de contenedor vino nulo algo salio mal en linea " + linea);
                        Editor.insertarTextoConsola("Error uno de los parametros de contenedor vino nulo algo salio mal en linea " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error uno de los parametros de contenedor vino nulo algo salio mal  " ); 
                        //return null;
                   }
               }
               //le agregamos la configuracion a la ventana ya creada antes
               JFrame ventana = (JFrame)(ent.get(idVentana)).getValor();
               JPanel panel = new JPanel();
               ventana.getContentPane().add(panel);
               panel.setLayout(new BorderLayout());
               panel.setBounds(new Rectangle(posX.intValue(), posY.intValue(), ancho.intValue(), alto.intValue()));
               if(borde)
                   panel.setBorder(null);
               panel.setBackground(colorContenedor);
               //guardamos la informacion para retornar en este caso solo retornamos el id de la ventana
               //ventana.setVisible(true);
               return idVentana;
           } else{
               System.out.println("Error la ventana con id: "+ idVentana + " no existe para crear contenedor en linea " + linea);
               Editor.insertarTextoConsola("Error la ventana con id: "+ idVentana + " no existe para crear contenedor en linea " + linea);
               ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error la ventana con id: "+ idVentana + " no existe para crear contenedor"); 
           }
       }else{
            System.out.println("Error para crear un nuevo contenedor debe contener 6 o  este debe contener mas de un parametros en linea " + linea);
            Editor.insertarTextoConsola("Error para crear un nuevo contenedor este debe contener 6 o  mas de un parametros. en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error para crear un nuevo contenedor este debe contener 6 o  mas de un parametros en linea " + linea);
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
