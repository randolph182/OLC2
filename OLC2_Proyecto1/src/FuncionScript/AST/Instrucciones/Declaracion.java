/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;

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
                //SI EL IDENTIFICADOR NO EXISTE
                if (ent.get(lstId.get(i).getIdentificador()) != null) { 
                    System.out.println("Error el identificador "+lstId.get(i).getIdentificador()+" ya existe"); //ERROR
                    System.out.println("linea: " + linea);
                    break;
                } else {
                    //DEBO PREGUNTAR SI ES EL ULTIMO
                    if (i == lstId.size() - 1) {
                        if (vinoExp) { //ASIGNAMOS EL VALOR
                            Object resultado = exp.getValor(ent);
                            if ( resultado!= null) {
                                tipo = exp.getTipo(ent);
                                Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(), tipo); //aca tipo ya es null
                                ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                                nuevoSimbolo.setValor(resultado);
                            } else {
                                System.out.println("Error obteniendo el valor de expresion en clase Declaracion");
                            }
                            break;
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
