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
public class ObtenerPorId implements Expresion {

    String idArreglo;
    String id;
    int linea;

    public ObtenerPorId(String idArreglo, String id, int linea) {
        this.idArreglo = idArreglo;
        this.id = id;
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
                LinkedList<Simbolo> listadoIDS = new LinkedList<>();
                if (arreglo.getElementos().size() != 0) {

                    for (Simbolo s : arreglo.getElementos()) { //obtenemos las etiquetas
                        
                        for(Simbolo se:s.getElementos()){
                            if (se.getRolGxml() == Simbolo.ROLGXML.ID && se.getValor().toString().equalsIgnoreCase(id)) {
                            listadoIDS.add(s);
                        }
                        }
                        
                    }

                    return listadoIDS;

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
