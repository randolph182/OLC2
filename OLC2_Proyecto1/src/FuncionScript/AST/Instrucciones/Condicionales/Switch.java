/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones.Condicionales;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Instrucciones.Break;
import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class Switch implements Instruccion{



    Expresion expresion;
    LinkedList<Caso> casos;
    int linea;
    public Switch(Expresion expresion, LinkedList<Caso> casos,int linea) {
        this.expresion = expresion;
        this.casos = casos;
        this.linea = linea;
    }
        
    @Override
    public Object ejecutar(Entorno ent) {
        Object control = expresion.getValor(ent);
        //VALIDAR QUE TODOS LOS CASOS SEAN DEL MISMO TIPO
        for (int i = 0; i < casos.size()-1; i++) { //no tomamos en cuenta el default
            if(expresion.getTipo(ent).getTipoPrimitivo() != casos.get(i).expresion.getTipo(ent).getTipoPrimitivo()){
                System.out.println("Error hay un caso que no es del tipo control");
                casos.remove(i); //quito ese caso  aca me recupero semanticamente
            }
        }
        
        boolean flag = false;
        //despues de obtener el valor necesito ir comparando para ver en que caso concuerda
        //debo tomar en cuenta que si no viene un brake sigo ejecutando todos los cases
        int i =0;
        for (i =0; i < casos.size() - 1; i++) {
            //De la lista de casos si alguno concuerda con el control del switch
            if(casos.get(i).expresion.getValor(ent).equals(control)  || flag){
                //ejecuto su instrucciones  y todas las demas sino se encuentra detener
                flag = true;
                for(nodoAST nodo: casos.get(i).instrucciones){
                    if(nodo instanceof Instruccion){ //debo ejecutar esa instruccion
                        Instruccion ins = (Instruccion)nodo;
                        //verfico sino vino un breake;
                        if(ins instanceof Break){
                            return null;
                        }
                        ins.ejecutar(ent); //ejecuto la instruccion
                    } else if(nodo instanceof Expresion){ //para los casos de Expresion del tipo return;
                        /*
                            if(nodo instanceof Resturn){
                                return toReturn.getValue(env); -> tipo Expresion
                            }
                        */
                    }
                }
            }
        }
        //SI LLEGA HASTA AQUI ES PORQUE NO VINO BREAK O ALGUNA QUE INTERRUMPA EL SWITCH
        for(nodoAST nodo: casos.get(i).instrucciones){
            if(nodo instanceof Instruccion){ //debo ejecutar esa instruccion
                Instruccion ins = (Instruccion)nodo;
                //verfico sino vino un breake;
                if(ins instanceof Break){
                    return null;
                }
                ins.ejecutar(ent); //ejecuto la instruccion
            } else if(nodo instanceof Expresion){ //para los casos de Expresion del tipo return;
                /*
                    if(nodo instanceof Resturn){
                        return toReturn.getValue(env); -> tipo Expresion
                    }
                */
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
