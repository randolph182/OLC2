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
public class Controlador implements Instruccion {

    LinkedList<Simbolo> elementos;
    LinkedList<Simbolo> hijos;
    int linea;

    public Controlador(LinkedList<Simbolo> elementos, LinkedList<Simbolo> hijos, int linea) {
        this.elementos = elementos;
        this.hijos = hijos;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {

        if (elementos.size() != 0) {
            //buscamos el tipo para trabajar con ello 
            String tipoControl = "";
            for (Simbolo elemento : elementos) {
                if (elemento.getRolGxml() == Simbolo.ROLGXML.TIPO) {
                    tipoControl = elemento.getValor().toString();
                    break;
                }
            }

            if (!tipoControl.equals("")) {

                String nombreControl = "";
                for (Simbolo elemento : elementos) {
                    if (elemento.getRolGxml() == Simbolo.ROLGXML.NOMBRE) {
                        if (ent.getActual(elemento.getValor().toString()) == null) {
                            nombreControl = elemento.getId();
                            break;
                        } else {
                            System.out.println("Error el id: " + elemento.getId() + "del texto  que intenta crear ya existe en linea: " + linea);
                            Editor.insertarTextoConsola("Error el id" + elemento.getId() + " del texto que intenta crear ya existe en linea: " + linea);
                            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error el id: " + elemento.getId() + " del texto que intenta crear ya existe");
                            return null;
                        }
                    }
                }

                if (!nombreControl.equals("")) { //SI EXISTE EL NOMBRE

                    BufferedWriter bf = null;
                    LinkedList<Simbolo> elementosControl = null;

                    if (tipoEjecucion == 0) {
                        bf = new BufferedWriter(archivo);
                    } else {
                        elementosControl = new LinkedList<>();
                    }
                    String alto = "10";
                    String ancho = "10";
                    String fuente = "Agency FB";
                    String tamanio = "5";
                    String color = "#000000";
                    String x = "10";
                    String y = "10";
                    String negrilla = "falso";
                    String cursiva = "falso";
                    String defecto = "";
                    String nombre = "";
                    
                    //SACAMOS EL DEFECTO ANTES
                    for (Simbolo s : hijos) {
                        if (s.getRolGxml() == Simbolo.ROLGXML.DEFECTO) {

                            defecto = s.getValor().toString();
                            if (tipoEjecucion == 1) {
                                Simbolo s2 = new Simbolo();
                                s2.setId("defecto");
                                s2.setValor(defecto);
                                s2.setRolGxml(Simbolo.ROLGXML.DEFECTO);
                                s2.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                elementosControl.add(s2);
                            }

                            break;
                        }
                    }

                    for (Simbolo elemento : elementos) {

                        switch (elemento.getRolGxml()) {
                            case ALTO:
                                alto = elemento.getValor().toString();
                                if (tipoEjecucion == 1) {
                                    Simbolo s = new Simbolo();
                                    s.setId("alto");
                                    s.setValor(alto);
                                    s.setRolGxml(Simbolo.ROLGXML.ALTO);
                                    s.setTipo(new Tipo(Tipo.Primitivo.NUMBER));
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
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
                                    elementosControl.add(s);
                                }
                                break;

                            case NOMBRE:
                                nombre = elemento.getValor().toString();
                                if (tipoEjecucion == 1) {
                                    Simbolo s = new Simbolo();
                                    s.setId("nombre");
                                    s.setValor(nombre);
                                    s.setRolGxml(Simbolo.ROLGXML.NOMBRE);
                                    s.setTipo(new Tipo(Tipo.Primitivo.STRING));
                                    elementosControl.add(s);
                                }

                                break;
                        }
                    }
                    if (tipoControl.equalsIgnoreCase("texto")) {  //CAJA DE TEXTO 
                        try {

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
                                    bf.write(idContenedor + ".CrearCajaTexto(");
                                }
                                Simbolo cajaTexto = new Simbolo(nombreControl, new Tipo(Tipo.TipoGXML.CONTROL_TEXTO));
                                cajaTexto.setValor(idContenedor);
                                ent.put(nombreControl, cajaTexto);

                                if (!x.equals("0") && !y.equals("0")) {
                                    if (tipoEjecucion == 0) {
                                        bf.write(alto +","+ ancho + "\"" + fuente + "\"," + tamanio + ",\"" + color + "\"," + x + "," + y + "," + negrilla + "," + cursiva + ",\"" + defecto + "\",\"" + nombreControl + "\");\n");
                                        bf.flush();
                                    } else {
                                        Simbolo s = new Simbolo(nombreControl, new Tipo(Tipo.TipoGXML.CONTROL_TEXTO));
                                        s.setValor(idContenedor); //SERVIRA COMO REFERENCIA PARA MAS ADELANTE
                                        s.setElementos(elementosControl);
                                        listadoSimbolos.add(s);
                                    }

                                } else {
                                    System.out.println("hacen falta X y Y del control = " + nombreControl + " en linea:" + linea);
                                    Editor.insertarTextoConsola("hacen falta X y Y del control= " + nombreControl + " en linea:" + linea);
                                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "hacen falta X y Y del control = " + nombreControl);
                                    return null;
                                }

                            } else {
                                System.out.println("No se encontro el contenedor Actual de la etiqueta control = " + nombreControl + " en linea:" + linea);
                                Editor.insertarTextoConsola("No se encontro el contenedor Actual de la etiqueta control = " + nombreControl + " en linea:" + linea);
                                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "No se encontro el contenedor Actual de la etiqueta control = " + nombreControl + " en linea:" + linea);
                                return null;
                            }
                        } catch (Exception e) {
                            System.out.println("Error Ocurrio algo creando caja de texto en linea: " + linea);
                            Editor.insertarTextoConsola("Error Ocurrio algo creando caja de texto en linea: " + linea);
                            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error Ocurrio algo creando caja de texto en linea: " + linea);
                            return null;
                        }
                    }

                } else {
                    System.out.println("Error hubo problemas con nombre del control en linea: " + linea);
                    Editor.insertarTextoConsola("Error hubo problemas con nombre del control en linea: " + linea);
                    ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error hubo problemas con nombre del control");
                    return null;
                }

            } else {
                System.out.println("Error no se pudo encotrar el tipo en el controlador en linea: " + linea);
                Editor.insertarTextoConsola("Error no se pudo encotrar el tipo en el controlador en linea: " + linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error no se pudo encotrar el tipo en el controlador");
                return null;
            }

        } else {
            System.out.println("Error la etiqueta Control debe tener por lomenos 4 elementos que son Tipo,nombre, x & y en linea: " + linea);
            Editor.insertarTextoConsola("Error la etiqueta Control debe tener por lomenos 4 elementos que son Tipo,nombre, x & y en linea: " + linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea, "Error la etiqueta Control debe tener por lomenos 4 elementos que son Tipo,nombre, x & y");
        }
        return null;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
