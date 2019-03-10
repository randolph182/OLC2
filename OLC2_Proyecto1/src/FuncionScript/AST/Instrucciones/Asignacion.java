/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Asignacion implements Instruccion{
    String id;
    Expresion expresion;
    int linea;
    
    public Asignacion(String id, Expresion valor, int linea) {
        this.id = id;
        this.expresion = valor;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object valExp = expresion.getValor(ent);
        Tipo tipo = expresion.getTipo(ent);
        
        if(valExp != null){
            //BUSCAMOS QUE SI EXISTA EL IDENTIFICADOR
            if(ent.get(id) != null){ 
                Simbolo s = ent.get(id);
                s.setValor(valExp);
                s.setTipo(tipo);
            //SINO BUSCAMOS EN EL GLOBAL;
            } else{
                System.out.println("El identificador no existe");
            }
        } else {
            System.out.println("hay error en la obtencion del valor de la expresion ya que vin null");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}
