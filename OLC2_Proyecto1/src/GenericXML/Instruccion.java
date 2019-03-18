/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenericXML;

//import GenericXML.Entorno.Entorno;
import FuncionScript.Entorno.Entorno;
import java.io.FileWriter;

/**
 *
 * @author randolph muy
 */
public interface Instruccion extends nodoAST{
    
   public Object Traducir(FileWriter archivo,Entorno ent);
}
