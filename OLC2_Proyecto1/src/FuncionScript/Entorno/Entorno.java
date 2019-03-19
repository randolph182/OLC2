/* tabla de simbolos tomada de la pagina 89 de 
   Principios tecnicas y herramientas 2da edicion*/
package FuncionScript.Entorno;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class Entorno {
    protected Hashtable tablaSimbolos;


    public static  Hashtable tablaSimbolosGlobal;
    protected Entorno anterior;

    public Entorno getAnterior() {
        return anterior;
    }

    public void setAnterior(Entorno anterior) {
        this.anterior = anterior;
    }
    
    public Entorno(Entorno p){
        tablaSimbolos = new Hashtable();
        tablaSimbolosGlobal = getInstanceGlobal();
        anterior = p;
    }
    
    
    public void put(String llv,Simbolo sim){
        tablaSimbolos.put(llv,sim);
    }
    
    public Simbolo get(String llv){
        for(Entorno e = this; e != null; e = e.anterior){
            Simbolo encontrado = (Simbolo)(e.tablaSimbolos.get(llv));
            if(encontrado != null)
                return encontrado;
        }
        return null;
    }
    
    public Simbolo getActual(String llv){
        Simbolo encontrado = (Simbolo)(this.tablaSimbolos.get(llv));
        if(encontrado != null)
            return encontrado;
        return null;
    }
    
    public static Hashtable getInstanceGlobal(){
        if(tablaSimbolosGlobal == null){
           tablaSimbolosGlobal = new Hashtable();
        } 
        return tablaSimbolosGlobal;
    }
    
    public void resetInstanciaGlobal(){
        tablaSimbolosGlobal = new Hashtable();
    }
    
    public void putGlobal(String llv,Simbolo sim){
        tablaSimbolosGlobal.put(llv,sim);
    }
    

    public Hashtable getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(Hashtable tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }
    
    public Simbolo getGlobal(String llv){
        Simbolo encontrado = (Simbolo)(tablaSimbolosGlobal.get(llv));
        if(encontrado != null)
            return encontrado;
        return null;
    }
    
}
