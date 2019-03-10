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
    
    public void putGlobal(String llv,Simbolo sim){
        tablaSimbolosGlobal.put(llv,sim);
    }
    

//    /**
//     @param llv : identificador de la variable
//     @param sim : clase simbolo que contendra los valorea asociados a esa variable
//     * Nota: como en una tabla de simbolos puede existir funciones y variables con el 
//     * mismo nombre entonces, simpre que se haga uso del put se llevara una nueva
//     * lista de simbolos por ser un put el que se realiza
//     */
//    public void putGlobal(String llv,Simbolo sim){
//        LinkedList<Simbolo> simbolos = new LinkedList<>();
//        simbolos.add(sim);
//        tablaSimbolos.put(llv,simbolos);
//    }
//    /**
//     * Este metodo cuando se le ingresa su llave obtiene una lista de simbolos
//     * los cuales puede tener asociado al mismo nombre pero distinto rol
//     * @param llv : identificador del Simbolo
//     * @param rol : es el rol que juega 
//     */
//    public Simbolo get(String llv,Simbolo.ROL rol){
//        for(Entorno e = this; e != null; e = e.anterior){
//            LinkedList<Simbolo> lstSimbolos = (LinkedList<Simbolo>)(e.tablaSimbolos.get(llv));
//            for (Simbolo simbolo : lstSimbolos) {
//                
//            }
//            if(encontrado != null)
//                return encontrado;
//        }
//        return null;
//    }
//   
    
//    public void putGlobal(String llv,Simbolo sim){
//        tablaSimbolosGlobal.put(llv, sim);
//    }
    
    public Simbolo getGlobal(String llv){
        Simbolo encontrado = (Simbolo)(tablaSimbolosGlobal.get(llv));
        if(encontrado != null)
            return encontrado;
        return null;
    }
    
}
