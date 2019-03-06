/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.ErroresFS;

import java.util.LinkedList;

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
    
    public void setErrorLexico(int fila,int columna,String descripcion){
        ErrorFS efs = new ErrorFS(fila,columna,descripcion,"Error lexico");
        TablaErrores.add(efs);
    }
    
    public void setErrorSintactico(int fila,int columna,String descripcion){
        ErrorFS efs = new ErrorFS(fila,columna,descripcion,"Error sintactico");
        TablaErrores.add(efs);
    }
    
    public LinkedList<ErrorFS> getTablaErrores(){
        return TablaErrores;
    }
}
