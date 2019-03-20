/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Buscar;
import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Filter;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.AST.Expresiones.InterfazUsuario.CrearBoton;
import FuncionScript.AST.Expresiones.InterfazUsuario.CrearContenedor;
import FuncionScript.AST.Expresiones.InterfazUsuario.CrearVentana;
import FuncionScript.AST.Expresiones.InterfazUsuario.LeerGxml;
import FuncionScript.AST.Expresiones.InterfazUsuario.ObtenerPorEtiqueta;
import FuncionScript.AST.Expresiones.Map;
import FuncionScript.AST.Expresiones.Reduce;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author rm
 */
public class Declaracion implements Instruccion {

    LinkedList<Identificador> lstId;
    Tipo tipo;
    Expresion exp;
    boolean vinoExp;
    int linea;

    public Declaracion(LinkedList<Identificador> lstId, Expresion exp, int linea) {
        this.lstId = lstId;
        this.tipo = new Tipo(Tipo.Primitivo.NULL);
        this.exp = exp;
        this.linea = linea;
        this.vinoExp = true;
    }

    public Declaracion(LinkedList<Identificador> lstId, int linea) {
        this.lstId = lstId;
        this.linea = linea;
        this.vinoExp = false;
        this.tipo = new Tipo(Tipo.Primitivo.NULL);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        if (lstId != null) {
            for (int i = 0; i < lstId.size(); i++) { //recorro la lista de ID's que se ingresaron en la declaracion
                //SI EL IDENTIFICADOR SI EXISTE
                if (ent.getActual(lstId.get(i).getIdentificador()) != null) {
                    System.out.println("Error el identificador " + lstId.get(i).getIdentificador() + " ya existe"); //ERROR
                    System.out.println("linea: " + linea);
                    Editor.insertarTextoConsola("Error el identificador " + lstId.get(i).getIdentificador() + " ya existe  en linea " + linea);
                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error el identificador " + lstId.get(i).getIdentificador() + " ya existe");
                    break;
                } else {
                    //DEBO PREGUNTAR SI ES EL ULTIMO
                    if (i == lstId.size() - 1) {
                        if (vinoExp) { //ASIGNAMOS EL VALOR

                            if (exp instanceof CrearVentana) {
                                Object jframe = exp.getValor(ent);
                                if (jframe != null) {
                                    Simbolo simVentana = new Simbolo();
                                    simVentana.setRol(Simbolo.ROL.VENTANA);
                                    simVentana.setValor(jframe);
                                    ent.put(lstId.get(i).getIdentificador(), simVentana);
                                    break;
                                }
                            } else if (exp instanceof CrearContenedor) {
                                Object idVentana = exp.getValor(ent);
                                if (idVentana != null) {
                                    Simbolo simContenedor = new Simbolo();
//                                    simContenedor.setRol(Simbolo.ROL.FUNCION);
                                    simContenedor.setValor(idVentana);
                                    ent.put(lstId.get(i).getIdentificador(), simContenedor);
                                    break;
                                }
                            } else if (exp instanceof CrearBoton) {
                                Object idVentana = exp.getValor(ent);
                                if (idVentana != null) {
                                    Simbolo simBoton = new Simbolo();
//                                    simContenedor.setRol(Simbolo.ROL.FUNCION);
                                    simBoton.setValor(idVentana);
                                    simBoton.setTipo(exp.getTipo(ent));
                                    ent.put(lstId.get(i).getIdentificador(), simBoton);
                                    break;
                                }
                            } else if (exp instanceof LeerGxml) {
                                Object elementos = exp.getValor(ent);
                                if (elementos != null) {
                                    //AGREGAMOS AL NUEVO ARREGLO A LA TABLA DE SIMBOLOS Y NOS ASEGURAMOS DE PONERLE ROL HETEROGENEO
                                    LinkedList<Simbolo> valArray = (LinkedList<Simbolo>) elementos;
                                    Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), new Tipo(Tipo.Primitivo.NULL));
                                    nuevoSimbolo.setRol(Simbolo.ROL.ARREGLO_HETEROGENEO);
                                    nuevoSimbolo.setElementos(valArray);
                                    //agregando al entorno
                                    ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                    break;
                                } else {
                                    System.out.println("leerGxml no retorno elementos enlinea: " + linea);
                                    Editor.insertarTextoConsola("leerGxml no retorno elementos enlinea: " + linea);
                                    Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), tipo); //aca tipo ya es null
                                    break;
                                }

                            } else if (exp instanceof ObtenerPorEtiqueta) {
                                Object listado = exp.getValor(ent);
                                if (listado != null) {
                                    LinkedList<Simbolo> valArray = (LinkedList<Simbolo>) listado;
                                    Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), new Tipo(Tipo.Primitivo.NULL));
                                    nuevoSimbolo.setRol(Simbolo.ROL.ARREGLO_HETEROGENEO);
                                    nuevoSimbolo.setElementos(valArray);
                                    //agregando al entorno
                                    ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                    break;

                                } else {
                                    System.out.println("Error no se encontraron valores por etiqueta en linea " + linea);
                                    Editor.insertarTextoConsola("Error no se encontraron valores por etiqueta en linea " + linea);
                                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error no se encontraron valores por etiqueta en linea ");
                                    break;
                                }

                            } else if (exp instanceof Filter) {
                                Object arreglo = exp.getValor(ent);
                                Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), new Tipo(Tipo.Primitivo.NULL));

                                if (arreglo != null) {
                                    LinkedList<Simbolo> valArray = (LinkedList<Simbolo>) arreglo;
                                    nuevoSimbolo.setRol(Simbolo.ROL.ARREGLO_HETEROGENEO);
                                    nuevoSimbolo.setElementos(valArray);
                                    ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                    break;
                                }
                            } else if (exp instanceof Buscar) {
                                Object arreglo = exp.getValor(ent);
                                Simbolo valor = (Simbolo) arreglo;
                                if (valor != null) {
                                    ent.put(lstId.get(i).getIdentificador(), valor);
                                    break;
                                }
                            } else if (exp instanceof Reduce) {
                                Object arreglo = exp.getValor(ent);
                                Simbolo valor = (Simbolo) arreglo;
                                if (valor != null) {
                                    ent.put(lstId.get(i).getIdentificador(), valor);
                                    break;
                                }
                            }else if(exp instanceof Map){
                                Object arreglo = exp.getValor(ent);
                                Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), new Tipo(Tipo.Primitivo.NULL));

                                if (arreglo != null) {
                                    LinkedList<Simbolo> valArray = (LinkedList<Simbolo>) arreglo;
                                    nuevoSimbolo.setRol(Simbolo.ROL.ARREGLO_HOMOGENEO);
                                    nuevoSimbolo.setElementos(valArray);
                                    ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                    break;
                                }
                            } else {

                                Object resultado = exp.getValor(ent);
                                if (resultado != null) {

                                    if (resultado instanceof Simbolo) { //si retorna es porque hay un objecto
                                        tipo = new Tipo(Tipo.Primitivo.OBJECT);
                                        Simbolo result = (Simbolo) resultado;
                                        result.setTipo(tipo);
                                        ent.put(lstId.get(i).getIdentificador(), result);

                                    } else {
                                        tipo = exp.getTipo(ent);
                                        Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), tipo); //aca tipo ya es null
                                        ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                        nuevoSimbolo.setValor(resultado);
                                    }
                                } else {
                                    System.out.println("Error obteniendo el valor de expresion en clase Declaracion");
                                    Editor.insertarTextoConsola("Error en obtener el valor de una declaracion vino nulo en " + linea);
                                    ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error en obtener el valor de  una declaracion obtuvo nulo ");
                                }
                                break;
                            }

                        }
                    }
                    //VARIABLES QUE FUERON DECLARADAS PERO SIN NINGUNA ASIGNACION EN SU VALOR
                    Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), tipo); //aca tipo ya es null
                    ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                    nuevoSimbolo.setValor(null);
                }
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

}
