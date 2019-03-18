/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GenericXML;

/**
 * 
 * @author randolph muy
 */
public class ComprobacionErrorGlobal {
    private static ComprobacionErrorGlobal comprobacion;
    private boolean bandera = false;
    
    public static ComprobacionErrorGlobal getInstance(){
        if(comprobacion == null){
            comprobacion = new ComprobacionErrorGlobal();
        }
        return comprobacion;
    }
    
    public boolean hayError(){
        return bandera;
    }
    
    public void setEstadoError(boolean bandera){
        this.bandera = bandera;
    }
    
}
