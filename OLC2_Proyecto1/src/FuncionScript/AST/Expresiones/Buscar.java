/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Expresiones;

import FuncionScript.AST.Instrucciones.Funcion;
import FuncionScript.AST.Instrucciones.Instruccion;
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
public class Buscar implements Expresion {

    String idArreglo;
    Object idF;
    int linea;
    Tipo tipo;

    public Buscar(String idArreglo, Object idFuncion, int linea) {
        this.idArreglo = idArreglo;
        this.idF = idFuncion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno ent) {
        String idFuncion = "";
        if (idF instanceof Funcion) { //ESTA FUNCION SERA TEMPORAL
            Funcion f = (Funcion) idF;
            f.setId(idArreglo + "tmp");
            f.setRol(Simbolo.ROL.FUNCION);
            ent.putGlobal(f.getId(), f);
            idFuncion = idArreglo + "tmp";
            //HASTA AQUI YA TENGO UNA FUNCION EN LA TABLA GLOBAL
            //  FuncionLLamada fl = new FuncionLLamada(null, idArreglo, linea)
        } else {
            idFuncion = idF.toString();  //LA COMVIERTO A STRING
        }
//COMPROBAMOS QUE LOS IDS EXISTAN EN LA TABLA DE SIMBOLOS
        if (ent.get(idArreglo) != null) {
            if (ent.getGlobal(idFuncion) != null) {
                //Obtenemos los simbolos de cada una 
                Simbolo arreglo = ent.get(idArreglo);
                Simbolo funcion = ent.getGlobal(idFuncion);
                if (funcion.getElementos().size() == 1) {

                    if (arreglo.getElementos().size() != 0) {
                        //listamos todos los elementos del arreglo
                        for (int i = 0; i < arreglo.getElementos().size(); i++) {
                            //obtengo las posiciones del arreglo
                            Entorno entNuevo = new Entorno(ent);
                            Object e = arreglo.getElementos().get(i).getValor();
                            Tipo t = arreglo.getElementos().get(i).getTipo();
                            funcion.getElementos().get(0).setValor(e);
                            funcion.getElementos().get(0).setTipo(t);
                            //obtenemos el valor
                            Object a = ((Instruccion) funcion).ejecutar(entNuevo);
                            if (a != null) {
                                RetornoSecundario rs = (RetornoSecundario) a;
                                //ENTRAN SOLO LAS QUE FUERON VERDADERAS SOLO RETORNA UNO
                                if (rs.getValor(ent).toString().equalsIgnoreCase("true") || rs.getValor(ent).toString().equalsIgnoreCase("verdadero")) {
                                    Simbolo s = new Simbolo();
                                    s.setValor(e);
                                    s.setTipo(t);
                                    if (ent.getGlobal(idArreglo + "tmp") != null) {
                                        ent.getInstanceGlobal().remove(idArreglo + "tmp");
                                    }
                                    return s;
                                }
                            }
                        }
                        if (ent.getGlobal(idArreglo + "tmp") != null) {
                            ent.getInstanceGlobal().remove(idArreglo + "tmp");
                        }

                    } else {
                        System.out.println("El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                        Editor.insertarTextoConsola("El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El arreglo con id " + idArreglo + "no tiene elementos linea" + linea);
                    }
                } else {
                    System.out.println("La funcion con  con id " + idFuncion + "recibe mas parametros linea" + linea);
                    Editor.insertarTextoConsola("La funcion con  con id " + idFuncion + "recibe mas parametros linea" + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "La funcion con  con id " + idFuncion + "recibe mas parametros linea" + linea);
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
        return tipo;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
