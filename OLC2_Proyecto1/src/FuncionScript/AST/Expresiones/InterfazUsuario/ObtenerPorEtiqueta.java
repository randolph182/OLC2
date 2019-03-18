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
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class ObtenerPorEtiqueta implements Expresion {

    String idArreglo;
    String etiqueta;
    int linea;

    public ObtenerPorEtiqueta(String idArreglo, String etiqueta, int linea) {
        this.idArreglo = idArreglo;
        this.etiqueta = etiqueta;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        //NECESITO RETORNAR UNA ARREGLO DE SIMBOLOR CON LA ETIQUETA SOLICITADA

        //primero verificamos que exista idArreglo
        if (ent.get(idArreglo) != null) {
            Simbolo arreglo = ent.get(idArreglo);
            if (arreglo != null) {
                //ya que existe el arreglo debo valuar sus elementos dados por la etiqueta;
                LinkedList<Simbolo> listadoEtiqueta = new LinkedList<>();
                if (arreglo.getElementos().size() != 0) {
                    if (etiqueta.equalsIgnoreCase("ventana")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.VENTANA) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("contenedor")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.CONTENEDOR) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("texto")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.TEXTO) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("control")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.CONTROL) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("boton")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.BOTON) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("Multimedia")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.MULTIMEDIA) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("ListaDatos")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.LISTA_DATOS) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else if (etiqueta.equalsIgnoreCase("Enviar")) {
                        for (Simbolo etq : arreglo.getElementos()) {
                            if (etq.getTipo().getTipoGxml() == Tipo.TipoGXML.ENVIAR) {
                                listadoEtiqueta.add(etq);
                            }
                        }
                    } else {
                        System.out.println("Error la etiqueta = " + etiqueta + " no existe  linea:" + linea);
                        Editor.insertarTextoConsola("Error la etiqueta = " + etiqueta + " no existe  linea:" + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error la etiqueta = " + etiqueta + " no existe");
                        return null;
                    }
                    //SI LLEGA AQUI ES PORQUE APROVO UNA ETIQUETA Y POSTERIROMENTE VAMOS A RETORNAR EL LISTADO
                    return listadoEtiqueta;

                } else {
                    System.out.println("Error no hay elementos en el arreglo con el id " + idArreglo + " linea:" + linea);
                    Editor.insertarTextoConsola("Error no hay elementos en el arreglo con el id " + idArreglo + " linea:" + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error no hay elementos en el arreglo con el id " + idArreglo);
                    return null;
                }
            } else {
                System.out.println("problemas con obtener arreglos con el id " + idArreglo + " linea:" + linea);
                Editor.insertarTextoConsola("problemas con obtener arreglos con el id " + idArreglo + " linea:" + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "problemas con obtener arreglos con el id " + idArreglo);
                return null;
            }
        } else {
            System.out.println("problemas con obtener arreglos con el id " + idArreglo + " linea:" + linea);
            Editor.insertarTextoConsola("problemas con obtener arreglos con el id " + idArreglo + " linea:" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "problemas con obtener arreglos con el id " + idArreglo);
            return null;
        }
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
