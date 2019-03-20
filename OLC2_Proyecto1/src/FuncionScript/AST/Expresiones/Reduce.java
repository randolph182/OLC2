/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author randolph muy
 */
public class Reduce implements Expresion {

    String idArreglo;
    String idFuncion;
    int linea;

    public Reduce(String idArreglo, String idFuncion, int linea) {
        this.idArreglo = idArreglo;
        this.idFuncion = idFuncion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        //COMPROBAMOS QUE LOS IDS EXISTAN EN LA TABLA DE SIMBOLOS
        if (ent.get(idArreglo) != null) {
            if (ent.getGlobal(idFuncion) != null) {
                //Obtenemos los simbolos de cada una 
                Simbolo arreglo = ent.get(idArreglo);
                Simbolo funcion = ent.getGlobal(idFuncion);
                if (funcion.getElementos().size() == 2) {

                    if (arreglo.getElementos().size() != 0) {
                        //listamos todos los elementos del arreglo
                        int contador = 0;
                        Object acumulador = null;
                        Tipo acumTipo = new Tipo(Tipo.Primitivo.NULL);

                        for (int i = 0; i < arreglo.getElementos().size(); i++) {
                            //obtengo las posiciones del arreglo

                            Entorno entNuevo = new Entorno(ent);
                            //verificando si es el ultimo 
                            if (i == 0) { //SI ES LA PRIMERA VES QUE ENTRA

                                Object val1 = arreglo.getElementos().get(i).getValor();
                                Tipo tVal1 = arreglo.getElementos().get(i).getTipo();
                                Object val2 = arreglo.getElementos().get(i + 1).getValor();
                                Tipo tVal2 = arreglo.getElementos().get(i + 1).getTipo();
                                funcion.getElementos().get(0).setValor(val1);
                                funcion.getElementos().get(0).setTipo(tVal1);
                                funcion.getElementos().get(1).setValor(val2);
                                funcion.getElementos().get(1).setTipo(tVal2);
                                i++; //de una vez le sumamos 1 
                            } else {
                                Object val1 = arreglo.getElementos().get(i).getValor();
                                Tipo tVal1 = arreglo.getElementos().get(i).getTipo();
                                funcion.getElementos().get(0).setValor(acumulador);
                                funcion.getElementos().get(0).setTipo(acumTipo);
                                funcion.getElementos().get(1).setValor(val1);
                                funcion.getElementos().get(1).setTipo(tVal1);
                            }

                            //obtenemos el valor
                            Object a = ((Instruccion) funcion).ejecutar(entNuevo);
                            if (a != null) {

                                RetornoSecundario rs = (RetornoSecundario) a;
                                acumulador = rs.getValor(ent);
                                acumTipo = rs.getTipo(ent);
                            }
                        }
                        Simbolo s = new Simbolo();
                        s.setValor(acumulador);
                        s.setTipo(acumTipo);
                        return s;

                    } else {
                        System.out.println("El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                        Editor.insertarTextoConsola("El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                    }
                } else {
                    System.out.println("La funcion con  con id " + idFuncion + "debe recibir 2  parametros linea" + linea);
                    Editor.insertarTextoConsola("La funcion con  con id " + idFuncion + "debe recibir 2 parametros linea" + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "La funcion con  con id " + idFuncion + "debe recibir 2 parametros linea" + linea);
                }

            } else {
                System.out.println("Error el id de la funcion: " + idFuncion + "no existe  en tabla linea" + linea);
                Editor.insertarTextoConsola("Error el id del funcion: " + idFuncion + "no existe  en tabla linea" + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error el id del funcion: " + idFuncion + "no existe  en tabla linea" + linea);
                return null;
            }
        } else {
            System.out.println("Error el id del arreglo: " + idArreglo + "no existe  en tabla linea" + linea);
            Editor.insertarTextoConsola("Error el id del arreglo: " + idArreglo + "no existe  en tabla linea" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error el id del arreglo: " + idArreglo + "no existe  en tabla linea" + linea);
            return null;
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
