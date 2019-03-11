/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;

/**
 * 
 * @author randolph muy
 */
public class ArregloLlamada implements Expresion{

    String id;
    Expresion exp;
    int linea;
    Tipo tipo = new Tipo(Tipo.Primitivo.NULL);
    public ArregloLlamada(String id,Expresion exp, int linea){
        this.id = id;
        this.exp = exp;
        this.linea = linea;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
        //Calculando primero la Expresion
        Object valElemen = exp.getValor(ent);
        if(valElemen != null) //si posicion a accesar no es nula
        {
            if(exp.getTipo(ent).isNumeric()){ //si es numrica
                //AHORA LLAMAMOS AL ARREGLO QUE ESTA EN LA TABLA DE SIMBOLOS
                if(ent.get(id) != null){ //si existe el arreglo
                    //SI EXISTE ENTONCES SACAMOS SU LISTA DE SIMBOLOS PARA OBTENER LA POSICION SOLICITADA
                    LinkedList<Simbolo> valores = ent.get(id).getElementos();
                    Double d = (Double)valElemen;
                    int pos =  d.intValue();
                    if(pos >= valores.size() || pos < 0){
                        System.out.println("Error la posicion a la que desea acceder es Erronea");
                        return null;
                    }
                    tipo = valores.get(pos).getTipo();
                    return valores.get(pos).getValor();
                }
                
            } else{
                System.out.println("Error las posiciones de los arreglos sole se accesan con numericos");
                return null;
            }
        } else {
            System.out.println("El valor con  el que se pretende accesar al arreglo vino nulo ");
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
