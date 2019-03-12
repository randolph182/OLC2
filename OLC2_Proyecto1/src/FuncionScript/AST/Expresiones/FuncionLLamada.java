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
import java.util.LinkedList;

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
//            LinkedList<Simbolo> pars = s.getElementos();
            
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
//                   pars.get(i).setValor(e);
//                   pars.get(i).setTipo(tipoE);
                   i++;
                }
            } else{
                System.out.println("El numero de parametros no son iguales");
                return null;
            }
            //EJECUTO LA INSTRUCCION DE LA FUNCION QUE MANDE A LLAMAR 
            Entorno nuevoEnt = new Entorno(ent);
//            Instruccion ins = (Instruccion)s;
            Object a = ((Instruccion)s).ejecutar(nuevoEnt);
            //si a es distinto a nulo es porque fijo trae valores 
            if(a != null){
                RetornoSecundario rs = (RetornoSecundario)a;
                this.tipo = rs.getTipo(nuevoEnt);
                return rs.getValor(nuevoEnt);              
            }
        } else
            System.out.println("No existe la funcion en la tabla global ");
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
