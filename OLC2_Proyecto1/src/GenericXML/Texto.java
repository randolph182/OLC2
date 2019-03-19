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
public class Texto implements Instruccion {

    LinkedList<Simbolo> elementos;
    String texto;
    int linea;

    public Texto(LinkedList<Simbolo> elementos, String texto, int linea) {
        this.elementos = elementos;
        this.texto = texto;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {
        if (elementos.size() != 0) {
            //buscamos el nombre del texto
            String nombreTexto = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.NOMBRE) {
                    if (ent.getActual(elemento.getValor().toString()) == null) {
                        nombreTexto = elemento.getId();
                        break;
                    } else {
                        System.out.println("Error el id: " + elemento.getId() + "del texto  que intenta crear ya existe en linea: " + linea);
                        Editor.insertarTextoConsola("Error el id" + elemento.getId() + " del texto que intenta crear ya existe en linea: " + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " del texto que intenta crear ya existe");
                        return null;
                    }
                }
            }

            if (!nombreTexto.equals("")) {
                try {
                    LinkedList<Simbolo> elementosTexto = null;
                    BufferedWriter bf = null;
                    if (tipoEjecucion == 0) {
                        //instanciamos parametros para escribir en archivo
                        bf = new BufferedWriter(archivo);
                    } else {
                        //instanciamos los elemntos de la ventana 
                        elementosTexto = new LinkedList<>();
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
                            bf.write(idContenedor + ".crearTexto(");
                        }
                        Simbolo text = new Simbolo(nombreTexto, new Tipo(Tipo.TipoGXML.TEXTO));
                        text.setValor(idContenedor);
                        ent.put(nombreTexto, text);

                        String nombre = "";
                        String fuente = "Agency FB";
                        String tamanio = "5";
                        String color = "#000000";
                        String x = "10";
                        String y = "10";
                        String negrilla = "falso";
                        String cursiva = "falso";

                        for (Simbolo elemento : elementos) {

                            switch (elemento.getRolGxml()) {
                                case NOMBRE:
                                    nombre = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("nombre");
                                        s.setValor(nombre);
                                        s.setRolGxml(Simbolo.ROLGXML.NOMBRE);
                                        s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                        elementosTexto.add(s);
                                    }
                                    break;
                                case X:
                                    x = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("x");
                                        s.setValor(x);
                                        s.setRolGxml(Simbolo.ROLGXML.X);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosTexto.add(s);
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
                                        elementosTexto.add(s);
                                    }
                                    break;
                                case FUENTE:
                                    fuente = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("fuente");
                                        s.setValor(fuente);
                                        s.setRolGxml(Simbolo.ROLGXML.FUENTE);
                                        s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                        elementosTexto.add(s);
                                    }
                                    break;

                                case TAMANIO:
                                    tamanio = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("tamanio");
                                        s.setValor(tamanio);
                                        s.setRolGxml(Simbolo.ROLGXML.TAMANIO);
                                        s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                        elementosTexto.add(s);
                                    }
                                    break;
                                case COLOR:
                                    color = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("color");
                                        s.setValor(color);
                                        s.setRolGxml(Simbolo.ROLGXML.COLOR);
                                        s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                        elementosTexto.add(s);
                                    }
                                    break;

                                case NEGRILLA:
                                    negrilla = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("negrilla");
                                        s.setValor(negrilla);
                                        s.setRolGxml(Simbolo.ROLGXML.NEGRILLA);
                                        s.setTipo(new Tipo(Tipo.Primitivo.BOOLEAN));
                                        elementosTexto.add(s);
                                    }
                                    break;
                                case CURSIVA:
                                    cursiva = elemento.getValor().toString();
                                    if (tipoEjecucion == 1) {
                                        Simbolo s = new Simbolo();
                                        s.setId("cursiva");
                                        s.setValor(cursiva);
                                        s.setRolGxml(Simbolo.ROLGXML.CURSIVA);
                                        s.setTipo(new Tipo(Tipo.Primitivo.BOOLEAN));
                                        elementosTexto.add(s);
                                    }
                                    break;
                            }
                        }
                        
                        //AGREGANDO EL VALOR DEL TEXTO
                        if(tipoEjecucion == 1){
                            Simbolo s = new Simbolo();
                            s.setId("valor");
                            s.setValor(texto);
                            s.setRolGxml(Simbolo.ROLGXML.VALOR);
                            s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                            elementosTexto.add(s);
                        }
                        
                        if (!x.equals("0") && !y.equals("0")) {
                            if (tipoEjecucion == 0) {
                                bf.write("\""+fuente + "\"," + tamanio + ",\"" + color + "\"," + x + "," + y + "," + negrilla + ","+cursiva+",\""+texto+"\");\n");
                                bf.flush();
                            } else {
                                Simbolo s = new Simbolo(nombreTexto, new Tipo(Tipo.TipoGXML.TEXTO));
                                s.setValor(idContenedor); //SERVIRA COMO REFERENCIA PARA MAS ADELANTE
                                s.setElementos(elementosTexto);
                                listadoSimbolos.add(s);
                            }

                        } else {
                            System.out.println("hacen falta X y Y del texto = " + nombreTexto + " en linea:" + linea);
                            Editor.insertarTextoConsola("hacen falta X y Y del texto= " + nombreTexto + " en linea:" + linea);
                            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "hacen falta X y Y del texto = " + nombreTexto);
                            return null;
                        }

                    } else {
                        System.out.println("No se encontro el contenedor Actual de la etiqueta texto = " + nombreTexto + " en linea:" + linea);
                        Editor.insertarTextoConsola("No se encontro el contenedor Actual de la etiqueta texto = " + nombreTexto + " en linea:" + linea);
                        ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "No se encontro el contenedor Actual de la etiqueta texto = " + nombreTexto);
                        return null;
                    }

                } catch (Exception e) {
                    System.out.println("algo salio mal en la escritura del archivo en una etiqueta texto en linea:" + linea);
                    Editor.insertarTextoConsola("algo salio mal en la escritura del archivo en una etiqueta texto en linea:" + linea);
                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "algo salio mal en la escritura del archivo en una etiqueta texto ");
                    return null;
                }
            } else {
                System.out.println("Error hubo problemas con nombre del tenxto en linea: " + linea);
                Editor.insertarTextoConsola("Error hubo problemas con nombre del texto en linea: " + linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error hubo problemas con nombre del texto");
                return null;
            }

        } else {
            System.out.println("Error la etiqueta texti debe tener por lomenos 3 elementos que son nombre x & y en linea: " + linea);
            Editor.insertarTextoConsola("Error la etiqueta texti debe tener por lomenos 3 elementos que son nombre x & y en linea: " + linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error la etiqueta texti debe tener por lomenos 3 elementos que son nombre x & y");
        }
        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
