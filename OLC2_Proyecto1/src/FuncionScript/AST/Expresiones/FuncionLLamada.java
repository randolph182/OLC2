/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FuncionScript.AST.Expresiones;

import FuncionScript.AST.Instrucciones.Instruccion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.util.LinkedList;
import olc2_proyecto1.Editor.Editor;

public class FuncionLLamada implements Expresion{
    LinkedList<Expresion> parametros;
    String id;
    int linea;
    Tipo tipo;

    public FuncionLLamada(LinkedList<Expresion> parametros, String id, int linea) {
        this.parametros = parametros;
        this.id = id;
        this.linea = linea;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
        //obteniendo funcion 
        
        if(ent.getGlobal(id) != null){
            Simbolo s = ent.getGlobal(id);
            //verficando que el numero de parametros sea igual 
            if(parametros.size() == s.getElementos().size()){
                Object e;
                Tipo tipoE;
                int i = 0;
                //INSERTO VALORES A LOS PARAMETROS EN EL ORDEN QUE FUERON DECLARADOS
                for(Expresion exp: parametros){
                   e = exp.getValor(ent);
                   tipoE = exp.getTipo(ent);
                   s.getElementos().get(i).setValor(e);
                   s.getElementos().get(i).setTipo(tipoE);
                   i++;
                }
            } else{
                System.out.println("Error el numero de parametros no son iguales a los que fueron declarados en linea" + linea);
                Editor.insertarTextoConsola("Error el numero de parametros no son iguales a los que fueron declarados en linea" + linea);
                ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "Error el numero de parametros no son iguales a los que fueron declarados");
                return null;
            }
            //EJECUTO LA INSTRUCCION DE LA FUNCION QUE MANDE A LLAMAR 
            Entorno nuevoEnt = new Entorno(ent);
            Object a = ((Instruccion)s).ejecutar(nuevoEnt);
            //si a es distinto a nulo es porque fijo trae valores 
            if(a != null){
                RetornoSecundario rs = (RetornoSecundario)a;
                this.tipo = rs.getTipo(nuevoEnt);
                return rs.getValor(nuevoEnt);              
            }
        } else{
            System.out.println("Error no existe la funcion que mando a llamar en" + linea);
            Editor.insertarTextoConsola("Error no existe la funcion que mando a llamar en" + linea);
            ManejadorErroresFS.getInstance().setErrorSemanticos(linea, "no existe la funcion que mando a llamar");
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        return linea;
    }

}
