/* tabla de simbolos tomada de la pagina 89 de 
   Principios tecnicas y herramientas 2da edicion*/
package FuncionScript.Entorno;

import java.util.Hashtable;

/**
 *
 * @author rm
 */
public class Entorno {
    protected Hashtable tablaSimbolos;
    protected Entorno anterior;
    
    public Entorno(Entorno p){
        tablaSimbolos = new Hashtable();
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
}
