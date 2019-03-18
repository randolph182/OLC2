/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;
//import GenericXML.Entorno.Entorno;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import GenericXML.nodoAST;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class Ventana implements Instruccion {

    LinkedList<Simbolo> elementos;
    LinkedList<nodoAST> hijos;
    int linea;

    public Ventana(LinkedList<Simbolo> elementos, LinkedList<nodoAST> hijos, int linea) {
        this.elementos = elementos;
        this.hijos = hijos;
        this.linea = linea;
    }

    @Override
    public Object Traducir(FileWriter archivo, Entorno ent) {
        //PRIMERO PREGUNTAMOS AL ENTORNO SI YA EXISTE LA VENTANA
        if (elementos.size() != 0) {
            //buscamos el id de la ventana
            String idVentana = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.ID) {
                    if (ent.getGlobal(elemento.getValor().toString()) == null) {
                        idVentana = elemento.getId();
                        //sino existe lo agregamos a la tabla global 
                        // ent.putGlobal(elemento.getId(), elemento);
                        break;
                    } else {
                        System.out.println("Error el id: " + elemento.getId() + "de la ventana que intenta crear ya existe en linea: " + linea);
                        Editor.insertarTextoConsola("Error el id" + elemento.getId() + " de la ventana que intenta crear ya existe en linea: " + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " de la ventana que intenta crear ya existe");
                        return null;
                    }
                }
            }

            if (!idVentana.equals("")) {
                //EMPEZAOS TRADUCCION
                try {
                   // PrintWriter pw = new PrintWriter(archivo);
                    BufferedWriter bf = new BufferedWriter(archivo);
                    bf.write("var "+idVentana + " = crearVentana(");
                    String tipo = "";
                    String color = "";
                    String accionInicial = "";
                    String accionFinal = "";
                    for (Simbolo elemento : elementos) {
                        switch (elemento.getRolGxml()) {
                            case TIPO:
                                tipo = elemento.getValor().toString();
                                break;
                            case COLOR:
                                color = elemento.getValor().toString();
                                break;
                            case ACCION_INICIAL:
                                accionInicial = elemento.getValor().toString();
                                break;

                            case ACCION_FINAL:
                                accionFinal = elemento.getValor().toString();
                                break;
                        }
                    }
                    
                    if(tipo.equalsIgnoreCase("principal")){
                        //verificamos que no haya otra principal
                    } else if(tipo.equalsIgnoreCase("secundaria")){
                        //solo lo agregamos 
                    }else{
                        System.out.println("algo salio mal porque vino tipo en blanco linea:" + linea);
                        Editor.insertarTextoConsola("algo salio mal porque vino tipo en blanco linea:" + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal porque vino tipo en blanco");
                        return null;
                    }
                    
                    bf.write("\""+color+"\",500,500,"+"\""+idVentana+"\");\n");
                    bf.flush();
                    
                    //if(bf != null)
                    //    bf.close();
                } catch (Exception e) {
                    System.out.println("algo salio mal en la escritura del archivo en una ventana linea:" + linea);
                    Editor.insertarTextoConsola("algo salio mal en la escritura del archivo en una ventana linea:" + linea);
                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal en la escritura del archivo en una ventana");
                    return null;
                }

            } else {
                System.out.println("Error hubo problemas con id de ventana en linea: " + linea);
                Editor.insertarTextoConsola("Error hubo problemas con id de ventana en linea: " + linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error hubo problemas con id de ventana");
                return null;
            }
        } else {
            System.out.println("Error la etiqueta ventana debe tener porlomenos 2 elementos que son id y tipo en linea: " + linea);
            Editor.insertarTextoConsola("Error la etiqueta ventana debe tener porlomenos 2 elementos que son id y tipo en linea: " + linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error la etiqueta ventana debe tener porlomenos 2 elementos que son id y tipo");
            return null;
        }

        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
