/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.InterfazUsuario.LeerGxml;
import FuncionScript.Entorno.Entorno;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import olc2_proyecto1.Editor.Editor;
import static olc2_proyecto1.Editor.Editor.consola;

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
    public Object ejecutar(Entorno ent) {
                //VERIFICAMOS SI EL ARCHIVO EXISTE2
        File file = new File(direccion);
        if(file.exists()){
                    analizadores.FS.sintacticoFS parserFS;
        try {
            consola.setText("");
            parserFS = new analizadores.FS.sintacticoFS(new analizadores.FS.lexicoFS(new FileInputStream(direccion)));
            parserFS.parse();
            parserFS.ast.ejecutar2(ent);
            
        } catch (Exception e) {
            ManejadorErroresFS.getInstance();
            System.out.println("Error Fatal al trata de analizar el archivo con direcion " + direccion +" linea "+linea);
            Editor.insertarTextoConsola("Error Fatal al trata de analizar el archivo con direcion " + direccion +"linea "+linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"Error Fatal al trata de analizar el archivo con direcion " + direccion);
            System.out.println("Causa " + e.getCause());
        }
            
           
            
        }else{
            System.out.println("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            Editor.insertarTextoConsola("Error El archivo con direccion = "+direccion+"no existe en linea ="+ linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea,"Error El archivo con direccion = "+direccion+"no existe"); 
        }
        return null;  
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
