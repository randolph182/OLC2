/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Return;
import FuncionScript.AST.nodoAST;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Funcion  extends Simbolo implements Instruccion{
    
//    LinkedList<Simbolo> parametros;
//    LinkedList<nodoAST> instrucciones;
//    String id;
//    int linea;
//
    public Funcion(String id,LinkedList<Simbolo> parametros, LinkedList<nodoAST> instrucciones, int linea) {
        super(id,parametros,instrucciones,linea);
    }

    
    @Override
    public Object ejecutar(Entorno ent) {
        // ingreso los parametros de la funcion a la tabla de simbolos actual
        for (Simbolo parametro : getParametros()) {
            if(ent.get(parametro.getId()) != null){
                System.out.println("Error ya existe el parametro en la tabla de simbolos algo salio mal");
            } else{
                ent.put(parametro.getId(), parametro);
            }
        }
        
        for (nodoAST nodo: getInstrucciones()) {
            if(nodo instanceof Instruccion){
                Instruccion instruccion = (Instruccion) nodo;
                if(instruccion instanceof Funcion){
                    //NO HACE NADA 
                } else if(instruccion instanceof Break){
                    return null;
                }else {
                    Object a = instruccion.ejecutar(ent);
                    if(a != null)
                        return a;
                }
            } else if(nodo instanceof Return){
                Object a = ((Expresion)nodo).getValor(ent);
                if(a != null){
                    Tipo t = ((Expresion)nodo).getTipo(ent);
                    setValor(a);
                    setTipo(t);
                    return a;
                }
                System.out.println("Aun no definido pero ya mero ");
            }
        }
        return null;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
