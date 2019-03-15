/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import olc2_proyecto1.Editor.Editor;

/**
 *
 * @author rm
 */
public class Asignacion implements Instruccion{
    String id;
    Expresion expresion;
    int linea;
    
    public Asignacion(String id, Expresion valor, int linea) {
        this.id = id;
        this.expresion = valor;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        //haciendo coparacion con tipo de expresion en este caso con arreglo
//        if(expresion instanceof Arreglo){
//            Arreglo arrTmp = (Arreglo)expresion;
//            if(arrTmp.expresiones.size() == 0){   //SI EL ARRELGO NO CONTIENE ELEMENTOS ENTONCES ES UNA ASIGNACIOIN DE DIRECCION
//                if(ent.get(id) != null){  //buscamos el id en la tabla de simpblos
//                    Simbolo s = arrTmp; //asignamos posicion
//                }else{
//                   System.out.println("El identificador no existe  con id: "+ id + " en linea " + linea);
//                   Editor.insertarTextoConsola("El identificador no existe con id: "+ id + " en linea " + linea);
//                   ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El identificador no existe  con id: "+ id);
//                }
//                return null;
//            }
//        }
        Object valExp = expresion.getValor(ent);
        Tipo tipo = expresion.getTipo(ent);
        
        if(valExp != null){
            //BUSCAMOS QUE SI EXISTA EL IDENTIFICADOR
            if(ent.get(id) != null){ 
                Simbolo s = ent.get(id);
                //SI ES A UN ARREGLO A QUIEN QUEREMOS ASIGNARLE VALOR
                if(s.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO){
                    
                }
                
                s.setValor(valExp);
                s.setTipo(tipo);
            //SINO BUSCAMOS EN EL GLOBAL;
            } else{
               System.out.println("El identificador no existe  con id: "+ id + " en linea " + linea);
                   Editor.insertarTextoConsola("El identificador no existe con id: "+ id + " en linea " + linea);
                   ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El identificador no existe  con id: "+ id);
                   return null; 
            }
        } else {
            System.out.println("hay error en la obtencion del valor de la expresion ya que vin null");
            System.out.println("hay error en la obtencion del valor de la expresion ya que vino null en la  variable con id: "+ id + " en linea " + linea);
            Editor.insertarTextoConsola("hay error en la obtencion del valor de la expresion ya que vino null en la  variable con id: "+ id + " en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "hay error en la obtencion del valor de la expresion ya que vino null en la  variable con id: "+ id);
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
