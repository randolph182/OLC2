/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.ErroresFS;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import static olc2_proyecto1.Editor.Editor.analizar;

/**
 *
 * @author rm
 */
public class ManejadorErroresFS {
    private static ManejadorErroresFS erroresFS;
    private LinkedList<ErrorFS> TablaErrores;
    
    private ManejadorErroresFS(){
        TablaErrores = new LinkedList<>();
    }
    
    private synchronized static void createInstance(){
        if(erroresFS == null){
            erroresFS  = new ManejadorErroresFS();
        }
    }
    
    public static ManejadorErroresFS getInstance(){
        if(erroresFS == null){
            erroresFS  = new ManejadorErroresFS();
        }
        return erroresFS;
    }
    
    public void formateraInstancia(){
        erroresFS = new ManejadorErroresFS();
    }
    
    public void setErrorLexico(int fila,int columna,String descripcion){
        ErrorFS efs = new ErrorFS(fila,columna,descripcion,"Error lexico");
        TablaErrores.add(efs);
    }
    
    public void setErrorSintactico(int fila,int columna,String descripcion){
        ErrorFS efs = new ErrorFS(fila,columna,descripcion,"Error sintactico");
        TablaErrores.add(efs);
    }
    
    public void setErrorSemanticos(int fila,String descripcion){
        ErrorFS efs = new ErrorFS(fila,0,descripcion,"Error Semantico");
        TablaErrores.add(efs);
    }
    
    public LinkedList<ErrorFS> getTablaErrores(){
        return TablaErrores;
    }
    
    public boolean generarReporte(){
        if(TablaErrores.size() != 0){
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero =  new FileWriter("errFS.html");
            pw = new PrintWriter(fichero);
            //la alternativa mas rapida es  pw.println(txtAreaTrabajo.getText());
            char[] informacion = generarHTML().toCharArray();
            for (int i = 0; i < generarHTML().length(); i++) {
                if(informacion[i] == '\n')
                    pw.println("");
                else
                    pw.print(informacion[i]);
            }
        } catch (Exception e) {
            System.out.println("Error en la escritura del archivo de errores " + e);
        } finally{
            try {
                //si utiliza el finally par asegurarse de haber cerrado el archivo
                if(null != fichero)
                    fichero.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo en la Clase Editor" + e);
            }
        }
            
            return true;
        }
        return false;
    }
    
    private String generarHTML(){
        String acumlador = "<table border=\"1\">\n"+
                            "<caption>Errores FuncionScript</caption>\n";
        acumlador += "  <tbody>\n"
                        + "<tr>\n" +
"                       <th scope=\"row\">Tipo de Error</th>\n" +
"                       <th>linea</th>\n" +
"                       <th>Columna</th>\n" +
"                       <th>mensaje</th>\n" +      
"                       </tr>\n";
        
        for (ErrorFS error : TablaErrores) {
            acumlador +=    "<tr>\n"+
                            "<th>" + error.getTipo() + "</th>\n"+
                            "<th>" + error.getLinea() + "</th>\n"+
                            "<th>" + error.getColumna() + "</th>\n"+
                            "<th>" + error.getDescripcion() + "</th>\n" +       
                            "</tr>\n";
        }
        acumlador += "</tbody>\n"
                   + "</table>\n";
        return acumlador;
    }
}
