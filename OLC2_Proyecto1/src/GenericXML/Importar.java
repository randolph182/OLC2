/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GenericXML;

import FuncionScript.AST.Expresiones.InterfazUsuario.LeerGxml;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import GenericXML.ErroresGXML.ManejadorErroresGXML;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class Importar implements Instruccion{
    String direccion;
    int linea;

    public Importar(String direccion, int linea) {
        this.direccion = direccion;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(FileWriter archivo, LinkedList<Simbolo> listadoSimbolos, Entorno ent, int tipoEjecucion) {
        //VERIFICAMOS SI EL ARCHIVO EXISTE2
        File file = new File(direccion);
        if(file.exists()){
            //debo retornar un arreglo de copmonentes gxml
            analizadores.GXML.sintacticoGXML parserGXML;
            
            try {
                parserGXML = new analizadores.GXML.sintacticoGXML(new analizadores.GXML.lexicoGXML(new FileInputStream(direccion)));
                parserGXML.parse();
                if(tipoEjecucion == 0){
                    parserGXML.ast.traducir(archivo);
                }else
                    parserGXML.ast.getArray(listadoSimbolos);
            } catch (Exception ex) {
                Logger.getLogger(LeerGxml.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
                Editor.insertarTextoConsola("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
                ManejadorErroresGXML.getInstance().setErrorSemanticos(linea,"Error El archivo con direccion = "+direccion+"no existe");
            }
//            return elementos;
            
//            for(Simbolo elemento: elementos){
//                System.out.println("id: " + elemento.getId());
//                for(Simbolo e: elemento.getElementos()){
//                    System.out.println("Valor: "+e.getValor());
//                }
//            }
            
        }else{
            System.out.println("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            Editor.insertarTextoConsola("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            ManejadorErroresGXML.getInstance().setErrorSemanticos(linea,"Error El archivo con direccion = "+direccion+"no existe"); 
        }
        return null;  
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
