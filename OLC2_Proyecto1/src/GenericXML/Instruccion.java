/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

//import GenericXML.Entorno.Entorno;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 *
 * @author randolph muy
 */
public interface Instruccion extends nodoAST{
    

    /**
     * @param archivo  :  parametro que contrendra el apuntador a un archivo y por medio del cual se traduciran a codigo Funcion Script
     * @param listadoSimbolos : Parametro por el cual se pasa como apuntador un linkedList de simbolor y el cual 
     * se ira agregando cada simbolo del recorrido del arbol
     * @param ent : entorno o tabla del simbolos del analisis 
     * @param tipoEjecucion : variable que determina si es 
     * 0: es de tipo Traduccion , si es 
     * 1: es de tipo getArreglo
     */
   public Object ejecutar(FileWriter archivo,LinkedList<Simbolo> listadoSimbolos,Entorno ent,int tipoEjecucion);
}
