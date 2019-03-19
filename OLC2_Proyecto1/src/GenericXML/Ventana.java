/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;
//import GenericXML.Entorno.Entorno;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import GenericXML.nodoAST;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
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
        /**
     * @param archivo  :  parametro que contrendra el apuntador a un archivo y por medio del cual se traduciran a codigo Funcion Script
     * @param listadoSimbolos : Parametro por el cual se pasa como apuntador un linkedList de simbolor y el cual 
     * se ira agregando cada simbolo del recorrido del arbol
     * @param ent : entorno o tabla del simbolos del analisis 
     * @param tipoEjecucion : variable que determina si es 
     * 0: es de tipo Traduccion , si es 
     * 1: es de tipo getArreglo
     */
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {
        //PRIMERO PREGUNTAMOS AL ENTORNO SI YA EXISTE LA VENTANA
        if (elementos.size() != 0) {
            Entorno entLocal = new Entorno(ent);
            //buscamos el id de la ventana
            String idVentana = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.ID) {
                    if (entLocal.getGlobal(elemento.getValor().toString()) == null) {
                        idVentana = elemento.getId();
                        break;
                    } else {
                        System.out.println("Error el id: " + elemento.getId() + "de la ventana que intenta crear ya existe en linea: " + linea);
                        Editor.insertarTextoConsola("Error el id" + elemento.getId() + " de la ventana que intenta crear ya existe en linea: " + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " de la ventana que intenta crear ya existe");
                        return null;
                    }
                }
            }
           
            if (!idVentana.equals("")) { //SI ACCEDE ACA ES PORQUE NO NAY VENTANA QUE USE LLAME IGUAL
                //EMPEZAOS TRADUCCION
                try {
                    LinkedList<Simbolo> elementosVentana = null;
                    BufferedWriter bf = null;
                    if(tipoEjecucion == 0){
                        //instanciamos parametros para escribir en archivo
                      bf = new BufferedWriter(archivo);
                      bf.write("var "+idVentana + " = crearVentana(");  
                    }else{
                        //instanciamos los elemntos de la ventana 
                        elementosVentana = new LinkedList<>();
                    }
                    
                    String tipo = "";
                    String color = "";
                    String accionInicial = "";
                    String accionFinal = "";
                    String alto = "1000";
                    String ancho = "1000";
                    for (Simbolo elemento : elementos) {
                        switch (elemento.getRolGxml()) {
                            case TIPO:
                                tipo = elemento.getValor().toString();
                                if(tipoEjecucion == 1){
                                    Simbolo s = new Simbolo();
                                    s.setId("tipo");
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    s.setValor(tipo);
                                    s.setRolGxml(Simbolo.ROLGXML.TIPO);
                                    elementosVentana.add(s);
                                }
                                break;
                            case COLOR:
                                color = elemento.getValor().toString();
                                if(tipoEjecucion == 1){
                                    Simbolo s = new Simbolo();
                                    s.setId("color");
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    s.setValor(color);
                                    s.setRolGxml(Simbolo.ROLGXML.COLOR);
                                    elementosVentana.add(s);
                                }
                                break;
                            case ACCION_INICIAL:
                                accionInicial = elemento.getValor().toString();
                                if(tipoEjecucion == 1){
                                    Simbolo s = new Simbolo();
                                    s.setId("accionInicial");
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    s.setValor(accionInicial);
                                    s.setRolGxml(Simbolo.ROLGXML.ACCION_INICIAL);
                                    elementosVentana.add(s);
                                }                                
                                break;

                            case ACCION_FINAL:
                                accionFinal = elemento.getValor().toString();
                                if(tipoEjecucion == 1){
                                    Simbolo s = new Simbolo();
                                    s.setId("accionFinal");
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    s.setValor(accionInicial);
                                    s.setRolGxml(Simbolo.ROLGXML.ACCION_INICIAL);
                                    elementosVentana.add(s);
                                }    
                                break;
                        }
                    }
                    //INSTANCIA DE LA NUEVA VENTANA
                    Simbolo ventana = new Simbolo(idVentana, new Tipo(Tipo.TipoGXML.VENTANA));
                    if(tipo.equalsIgnoreCase("principal")){ //VERIFICAMOS SI NO HAY OTRA VENTANA PRINCIPAL
                        Enumeration<Simbolo> elements = entLocal.getInstanceGlobal().elements();
                        while(elements.hasMoreElements()){
                            Simbolo tmp = elements.nextElement();
                            if(tmp.getValor().toString().equalsIgnoreCase("principal")){ //SI HAY OTRA VENTANA QUE SEA PRINCIPAL
                                System.out.println("ERROR ya existe otra ventana que es principal, ventana actual = "+idVentana+" linea:" + linea);
                                Editor.insertarTextoConsola("ERROR ya existe otra ventana que es principal, ventana actual = "+idVentana+" linea:" + linea);
                                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "ERROR ya existe otra ventana que es principal, ventana actual = "+idVentana);
                                return null;
                            }
                        }
                        //AGREGAMOS EL NUEVO SIMBOLO
                        
                        ventana.setValor("principal");
                        entLocal.putGlobal(idVentana, ventana);
                        entLocal.put(idVentana, ventana);
                       
                    } else if(tipo.equalsIgnoreCase("secundaria")){
                       
                        ventana.setValor("secundaria");
                        entLocal.putGlobal(idVentana, ventana);
                        entLocal.put(idVentana, ventana);
                    }else{
                        System.out.println("algo salio mal porque vino tipo en blanco linea:" + linea);
                        Editor.insertarTextoConsola("algo salio mal porque vino tipo en blanco linea:" + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal porque vino tipo en blanco");
                        return null;
                    }
                   // System.out.println(accionInicial);
                   // System.out.println(accionFinal);
                    if(tipoEjecucion == 0){
                        bf.write("\""+color+"\","+alto+","+ancho+","+"\""+idVentana+"\");\n");
                        bf.flush();
                    }else{
                        Simbolo ventanaArray = new Simbolo(idVentana, new Tipo(Tipo.TipoGXML.VENTANA));
                        ventanaArray.setValor(ventana.getValor());
                        ventanaArray.setElementos(elementosVentana);
                        listadoSimbolos.add(ventanaArray);
                    }
                    
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
            
            //SI LLEGA HASTA ACA ES POR QUE LOS ELEMENTOS Y TRADUCCION DE LA VENTANA SALIERON BIEN
            for(nodoAST nodo: hijos){
                if(nodo instanceof Contenedor){
                    Contenedor contenedor = (Contenedor)nodo;
                    if(tipoEjecucion == 0)
                        contenedor.ejecutar(archivo,null,entLocal,tipoEjecucion);
                    else
                        contenedor.ejecutar(null,listadoSimbolos,entLocal,tipoEjecucion);
                }
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
