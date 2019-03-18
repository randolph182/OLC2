/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones.InterfazUsuario;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import olc2_proyecto1.Editor.Editor;

/**
 * 
 * @author randolph muy
 */
public class LeerGxml  implements Expresion {

    String direccion;
    int linea;

    public LeerGxml(String direccion, int linea) {
        this.direccion = direccion;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno ent) {
        //VERIFICAMOS SI EL ARCHIVO EXISTE2
        File archivo = new File(direccion);
        if(archivo.exists()){
            //debo retornar un arreglo de copmonentes gxml
            analizadores.GXML.sintacticoGXML parserGXML;
            LinkedList<Simbolo> elementos = new LinkedList<>();
            
            try {
                parserGXML = new analizadores.GXML.sintacticoGXML(new analizadores.GXML.lexicoGXML(new FileInputStream(direccion)));
                parserGXML.parse();
                parserGXML.ast.getArray(elementos);
            } catch (Exception ex) {
                Logger.getLogger(LeerGxml.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
                Editor.insertarTextoConsola("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"Error El archivo con direccion = "+direccion+"no existe");
            }
            return elementos;
            
//            for(Simbolo elemento: elementos){
//                System.out.println("id: " + elemento.getId());
//                for(Simbolo e: elemento.getElementos()){
//                    System.out.println("Valor: "+e.getValor());
//                }
//            }
            
        }else{
            System.out.println("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            Editor.insertarTextoConsola("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"Error El archivo con direccion = "+direccion+"no existe"); 
        }
        return null;  
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
