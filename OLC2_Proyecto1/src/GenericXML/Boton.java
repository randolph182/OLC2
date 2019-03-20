/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class Boton implements Instruccion {

    LinkedList<Simbolo> elementos;
    Object contenidoBoton;
    int linea;

    public Boton(LinkedList<Simbolo> elementos, Object contenidoBoton, int linea) {
        this.elementos = elementos;
        this.contenidoBoton = contenidoBoton;
        this.linea = linea;
    }

    @Override
    /**
     * @param archivo : parametro que contrendra el apuntador a un archivo y por
     * medio del cual se traduciran a codigo Funcion Script
     * @param listadoSimbolos : Parametro por el cual se pasa como apuntador un
     * linkedList de simbolor y el cual se ira agregando cada simbolo del
     * recorrido del arbol
     * @param ent : entorno o tabla del simbolos del analisis
     * @param tipoEjecucion : variable que determina si es 0: es de tipo
     * Traduccion , si es 1: es de tipo getArreglo
     */
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {
        if (elementos.size() != 0) {
            //buscamos el nombre del texto
            String nombreBoton = "";
            String referencia = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.NOMBRE) {
                    if (ent.getActual(elemento.getValor().toString()) == null) {
                        nombreBoton = elemento.getId();
                    }else {
                        System.out.println("Error el id: " + elemento.getId() + "del texto  que intenta crear ya existe en linea: " + linea);
                        Editor.insertarTextoConsola("Error el id" + elemento.getId() + " del texto que intenta crear ya existe en linea: " + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " del texto que intenta crear ya existe");
                        return null;
                    }
                }else if(elemento.getRolGxml() == Simbolo.ROLGXML.REFERENCIA){
                        referencia = elemento.getId();
                }
            }
            if (!nombreBoton.equals("")) {
                try {
                    LinkedList<Simbolo> elementosBoton = null;
                    BufferedWriter bf = null;
                    if (tipoEjecucion == 0) {
                        //instanciamos parametros para escribir en archivo
                        bf = new BufferedWriter(archivo);
                        if(!referencia.equals(""))
                            bf.write( "var "+referencia+" = ");
                        else
                            bf.write( "var "+nombreBoton+" = ");
                    } else {
                        //instanciamos los elemntos de la ventana 
                        elementosBoton = new LinkedList<>();
                    }

                    String idContenedor = "";
                    //OBTENEMO EL ID DEL CONTENEDOR ACTUAL
                    Enumeration<Simbolo> elements = ent.getTablaSimbolos().elements();
                    while (elements.hasMoreElements()) {
                        Simbolo tmp = elements.nextElement();
                        if (tmp.getTipo().getTipoGxml() == Tipo.TipoGXML.CONTENEDOR) { //SI HAY OTRA VENTANA QUE SEA PRINCIPAL
                            idContenedor = tmp.getId().toString();
                            break;
                        }
                    }

                    if (!idContenedor.equals("")) {
                        if (tipoEjecucion == 0) {
                            bf.write(idContenedor + ".crearBoton(");
                        }
                        Simbolo btn = new Simbolo(nombreBoton, new Tipo(Tipo.TipoGXML.BOTON));
                        btn.setValor(idContenedor);
                        ent.put(nombreBoton, btn);

                        String fuente = "Agency FB";
                        String tamanio = "10";
                        String color = "#C0C0C0";
                        String x = "10";
                        String y = "10";
                        String valor = "";
                        String alto = "10";
                        String ancho = "10";
                        String accion = "";

                        for (Simbolo elemento : elementos) { //ELEMENTOS QUE TRAE COMO PARAMETRO

                            switch (elemento.getRolGxml()) {

                                case X:
                                    x = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("x");
                                        s.setValor(x);
                                        s.setRolGxml(Simbolo.ROLGXML.X);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosBoton.add(s);
                                    }
                                    break;
                                case Y:
                                    y = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("y");
                                        s.setValor(y);
                                        s.setRolGxml(Simbolo.ROLGXML.Y);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosBoton.add(s);
                                    }
                                    break;

                                case ALTO:
                                    alto = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("alto");
                                        s.setValor(alto);
                                        s.setRolGxml(Simbolo.ROLGXML.ALTO);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosBoton.add(s);
                                    }
                                    break;
                                case ANCHO:
                                    ancho = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("ancho");
                                        s.setValor(ancho);
                                        s.setRolGxml(Simbolo.ROLGXML.ANCHO);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosBoton.add(s);
                                    }
                                break;

                                case REFERENCIA:
                                    referencia = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("referencia");
                                        s.setValor(referencia);
                                        s.setRolGxml(Simbolo.ROLGXML.REFERENCIA);
                                        s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                        elementosBoton.add(s);
                                    }
                                    break;
                                case ACCION:
                                    accion = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("accion");
                                        s.setValor(accion);
                                        s.setRolGxml(Simbolo.ROLGXML.ACCION);
                                        s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                        elementosBoton.add(s);
                                    }
                                    break;
                            }
                        }

                        //AHORO TOCA AGREGAR LOS ELEMENTOS QUE TRAE TEXTO SI LO TIENE EL EL CUERPO DEL BOTON 
                        if (contenidoBoton != null) {
                            if (contenidoBoton instanceof Texto) {
                                //quiero el listado de simbolos de texto
                                Texto txt = (Texto) contenidoBoton;
                                LinkedList<Simbolo> elementTexto = new LinkedList<>();
                                txt.ejecutar(null, elementTexto, ent, 1);
                                if (elementTexto.size() != 0) {
                                    
                                    for (Simbolo texto : elementTexto.get(0).getElementos()) { //SOLO UN TEXTO DEBERIA DE HABER
                                        if (texto.getRolGxml() == Simbolo.ROLGXML.FUENTE) {
                                            fuente = texto.getValor().toString();
                                            if (tipoEjecucion == 1) {
                                                Simbolo s = new Simbolo();
                                                s.setId("fuente");
                                                s.setValor(fuente);
                                                s.setRolGxml(Simbolo.ROLGXML.FUENTE);
                                                s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                                elementosBoton.add(s);
                                            }
                                        } else if (texto.getRolGxml() == Simbolo.ROLGXML.TAMANIO) {
                                            tamanio = texto.getValor().toString();
                                            if (tipoEjecucion == 1) {
                                                Simbolo s = new Simbolo();
                                                s.setId("tamanio");
                                                s.setValor(tamanio);
                                                s.setRolGxml(Simbolo.ROLGXML.TAMANIO);
                                                s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                                elementosBoton.add(s);
                                            }
                                        } else if (texto.getRolGxml() == Simbolo.ROLGXML.COLOR) {
                                            color = texto.getValor().toString();
                                            if (tipoEjecucion == 1) {
                                                Simbolo s = new Simbolo();
                                                s.setId("color");
                                                s.setValor(color);
                                                s.setRolGxml(Simbolo.ROLGXML.COLOR);
                                                s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                                elementosBoton.add(s);
                                            }
                                        }else if (texto.getRolGxml() == Simbolo.ROLGXML.VALOR) {
                                            valor = texto.getValor().toString();
                                            if (tipoEjecucion == 1) {
                                                Simbolo s = new Simbolo();
                                                s.setId("valor");
                                                s.setValor(valor);
                                                s.setRolGxml(Simbolo.ROLGXML.VALOR);
                                                s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                                elementosBoton.add(s);
                                            }
                                        }
                                    }

                                } else {
                                    System.out.println("no se pudo obtener el contenido de la etiqueta texto en el boton : " + nombreBoton + " en linea:" + linea);
                                    Editor.insertarTextoConsola("no se pudo obtener el contenido de la etiqueta texto en el boton : " + nombreBoton + " en linea:" + linea);
                                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "no se pudo obtener el contenido de la etiqueta texto en el boton : " + nombreBoton + " en linea:" + linea);
                                }
                            } else { //TRAE UN TEXTO NORMAL 
                                
                                valor = contenidoBoton.toString();
                                if (tipoEjecucion == 1) {
                                    Simbolo s = new Simbolo();
                                    s.setId("valor");
                                    s.setValor(valor);
                                    s.setRolGxml(Simbolo.ROLGXML.VALOR);
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    elementosBoton.add(s);
                                }
                            }
                        }

                        if (!x.equals("0") && !y.equals("0")) {
                            if (tipoEjecucion == 0) {
                                if(referencia.equals(""))
                                    referencia = nombreBoton;
                                bf.write("\""+fuente+"\","+tamanio+",\""+color+"\","+x+","+y+",\""+referencia+"\",\""+valor+"\","+alto+","+ancho+");\n");
                                if(!accion.equals(""))
                                    bf.write(nombreBoton +".AlClic("+accion+");\n");
                                bf.flush();
                            } else {
                                Simbolo s = new Simbolo(nombreBoton, new Tipo(Tipo.TipoGXML.BOTON));
                                s.setValor(idContenedor); //SERVIRA COMO REFERENCIA PARA MAS ADELANTE
                                s.setElementos(elementosBoton);
                                listadoSimbolos.add(s);
                            }

                        } else {
                            System.out.println("hacen falta X y Y del boton = " + nombreBoton + " en linea:" + linea);
                            Editor.insertarTextoConsola("hacen falta X y Y del boton = " + nombreBoton + " en linea:" + linea);
                            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "hacen falta X y Y del boton = " + nombreBoton);
                            return null;
                        }

                    } else {
                        System.out.println("No se encontro el contenedor Actual de la etiqueta boton = " + nombreBoton + " en linea:" + linea);
                        Editor.insertarTextoConsola("No se encontro el contenedor Actual de la etiqueta boton = " + nombreBoton + " en linea:" + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "No se encontro el contenedor Actual de la etiqueta texto = " + nombreBoton);
                        return null;
                    }

                } catch (Exception e) {
                    System.out.println("algo salio mal en la escritura del archivo en una etiqueta texto en linea:" + linea);
                    Editor.insertarTextoConsola("algo salio mal en la escritura del archivo en una etiqueta texto en linea:" + linea);
                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal en la escritura del archivo en una etiqueta texto ");
                    return null;
                }
            } else {
                System.out.println("Error hubo problemas con nombre del boton en linea: " + linea);
                Editor.insertarTextoConsola("Error hubo problemas con nombre del boton en linea: " + linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error hubo problemas con nombre del boton");
                return null;
            }

        } else {
            System.out.println("Error la etiqueta boton debe tener por lomenos 3 elementos que son nombre x & y en linea: " + linea);
            Editor.insertarTextoConsola("Error la etiqueta boton debe tener por lomenos 3 elementos que son nombre x & y en linea: " + linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error la etiqueta boton debe tener por lomenos 3 elementos que son nombre x & y");
        }
        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
