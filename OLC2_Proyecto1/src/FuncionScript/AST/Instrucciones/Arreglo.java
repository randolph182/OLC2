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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Arreglo extends Simbolo implements Instruccion{

    LinkedList<Expresion> expresiones;
    public Arreglo(String id, Tipo tipo) {
        super(id, tipo);
    }
    
    public Arreglo(LinkedList<Identificador> ids,LinkedList<Expresion> Expresiones,int linea){
        super(ids,linea);
        this.expresiones = Expresiones;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        boolean flag_id = false;
        String id = "";
        //Sacando el ultimo id de la lista al cual se le asignaran los valores 
        if(getLstIds().size() > 1){ //ADVERTENCIA
            System.out.println("Hay mas idntificadores declarados en la declaracion de arreglos y no es lo correcto");
        }
        
        for (int i = 0; i < getLstIds().size(); i++) { //recorro la lista de ID's que se ingresaron en la declaracion
            //SI EL IDENTIFICADOR SI EXISTE
            if (ent.getActual(getLstIds().get(i).getIdentificador()) != null) { 
                System.out.println("Error el identificador "+getLstIds().get(i).getIdentificador()+" ya existe"); //ERROR
                System.out.println("linea: " + getLinea());
                break;
            } else {
                //DEBO PREGUNTAR SI ES EL ULTIMO
                if (i == getLstIds().size() - 1) {
                        id = getLstIds().get(i).getIdentificador();
                        flag_id = true;
                }else{
                    //VARIABLES QUE FUERON DECLARADAS PERO SIN NINGUNA ASIGNACION EN SU VALOR
                    Simbolo nuevoSimbolo = new Simbolo(getLstIds().get(i).getIdentificador(), new Tipo(Tipo.Primitivo.NULL)); //aca tipo ya es null
                    ent.put(getLstIds().get(i).getIdentificador(), nuevoSimbolo);
                    nuevoSimbolo.setValor(null);
                }
            }
        }
        
        
        if(flag_id){
            //=========================CALCULO DE DE LA LISTA DE EXPRESIONES
            LinkedList<Simbolo> elementos = new LinkedList<>();
            int i =0;
            Object a = null;
            boolean flag_homogeneo = true; //tipos del mismo tipo
            setRol(ROL.ARREGLO_HOMOGENEO);
            Tipo tipoAnterior = new Tipo(Tipo.Primitivo.NULL);

            for(Expresion exp: expresiones){
                Simbolo nSim  = new Simbolo(Integer.toString(i), new Tipo(Tipo.Primitivo.NULL));
                a = exp.getValor(ent);
                if(i == 0){ //Es el primer elmento
                    tipoAnterior = exp.getTipo(ent);
                }else if(exp.getTipo(ent).getTipoPrimitivo() != tipoAnterior.getTipoPrimitivo() && flag_homogeneo == true){ //ahora i ya no es el primero
                        flag_homogeneo = false;
                        setRol(ROL.ARREGLO_HETEROGENEO);
                }
                nSim.setValor(a);
                nSim.setTipo(exp.getTipo(ent));
                elementos.add(nSim);
                i++;
            }
            
            //===============================================================
            //agregamos a la tabla de simbolos el arreglo con esta clase ya contruida
            //Ojo cuando se hagan llamadas a los arreglos progrurar de nos darle ejecutar
            setElementos(elementos);
            ent.put(id, this);
        }


        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
