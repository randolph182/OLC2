/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

public class ContadorBreak {
     private static ContadorBreak cb;
     private static int contador;
    public static ContadorBreak getInstance(){
        if(cb == null){
            cb  = new ContadorBreak();
            contador = 0;
        }
        return cb;
    }
    
    public void formateraInstancia(){
        cb = new ContadorBreak();
        contador = 0;
    }
    
    public void incrementarContador(){
        if(cb != null){
            contador++;
        }
    }
    
    public void decrementarContador(){
        if(cb != null){
            contador--;
        }
    }
    
    public int getContador(){
        if(cb != null){
            return contador;
        }
        return -1;
    }
    
    
}
