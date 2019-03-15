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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AsignacionArreglo implements Instruccion{
    
    String id;
    Expresion posArreglo;
    Expresion expVal;
    int linea;

    public AsignacionArreglo(String id, Expresion posArreglo,Expresion valExp, int linea) {
        this.id = id;
        this.posArreglo = posArreglo;
        this.linea = linea;
        this.expVal = valExp;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object posArr = posArreglo.getValor(ent);
        Tipo tipoPos = posArreglo.getTipo(ent);
        
        Object valExp = expVal.getValor(ent);
        Tipo tipoVal = expVal.getTipo(ent);
        
        if(valExp != null && posArr != null){
            //BUSCAMOS QUE SI EXISTA EL IDENTIFICADOR
            if(ent.get(id) != null){ 
                Simbolo s = ent.get(id);
                
                //SI ES A UN ARREGLO A QUIEN QUEREMOS ASIGNARLE VALOR
                if(s.getRol() == Simbolo.ROL.ARREGLO_HOMOGENEO){
                //SI EL ARREGLO CONTIENE ELEMNTOS
                    if(s.getElementos().size() ==0){ //arreglo sin elemento
                        System.out.println("el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        Editor.insertarTextoConsola("el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        return null;   
                    }else if(tipoVal == s.getElementos().get(0).getTipo()){ //si el nuevo valor es del mismo tipo a asignar
                        //OBTENEMOS LA POSICION DEL ELEMENTO y sel asignamos el nuevo valor
                        if(tipoPos.isNumeric()){
                            Double pos = new Double(posArr.toString());
                            s.getElementos().get(pos.intValue()).setValor(valExp);
                        } else {
                            System.out.println("la posicion del arreglo no es numerico en la " + linea);
                            Editor.insertarTextoConsola("la posicion del arreglo no es numerico en la " + linea);
                            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"la posicion del arreglo no es numerico en la " + linea);
                            return null;   
                        }

                    }else{ // entonces el valor es de otro tipo por lo tanto se vuelve heterogeneo
                       if(tipoPos.isNumeric()){
                            Double pos = new Double(posArr.toString());
                            s.getElementos().get(pos.intValue()).setValor(valExp);
                            s.getElementos().get(pos.intValue()).setTipo(tipoVal);
                            s.setRol(Simbolo.ROL.ARREGLO_HETEROGENEO);
                        } else {
                            System.out.println("la posicion del arreglo no es numerico en la " + linea);
                            Editor.insertarTextoConsola("la posicion del arreglo no es numerico en la " + linea);
                            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"la posicion del arreglo no es numerico en la " + linea);
                            return null;   
                        }
                    }
                } else if(s.getRol() == Simbolo.ROL.ARREGLO_HETEROGENEO){
                    
                    if(s.getElementos().size() ==0){ //arreglo sin elemento
                        System.out.println("el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        Editor.insertarTextoConsola("el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "el arreglo al que se desea agregar valor no ha sido declarado en la " + linea);
                        return null;   
                    }else{ // entonces el valor es de otro tipo por lo tanto se vuelve heterogeneo
                       if(tipoPos.isNumeric()){
                            Double pos = new Double(posArr.toString());
                            s.getElementos().get(pos.intValue()).setValor(valExp);
                            s.getElementos().get(pos.intValue()).setTipo(tipoVal);
                            s.setRol(Simbolo.ROL.ARREGLO_HETEROGENEO);
                        } else {
                            System.out.println("la posicion del arreglo no es numerico en la " + linea);
                            Editor.insertarTextoConsola("la posicion del arreglo no es numerico en la " + linea);
                            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"la posicion del arreglo no es numerico en la " + linea);
                            return null;   
                        }
                    }
                }
            } else{
               System.out.println("El identificador no existe  con id: "+ id + " en linea " + linea);
                   Editor.insertarTextoConsola("El identificador no existe con id: "+ id + " en linea " + linea);
                   ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "El identificador no existe  con id: "+ id);
                   return null; 
            }
        } else {
            System.out.println("hay error en la obtencion del valor de la expresion ya que vino null en el  arreglo con id: "+ id + " en linea " + linea);
            Editor.insertarTextoConsola("hay error en la obtencion del valor de la expresion ya que vino null en el  arreglo con id: "+ id + " en linea " + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "hay error en la obtencion del valor de la expresion ya que vino null en el arreglo con id: "+ id);
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
