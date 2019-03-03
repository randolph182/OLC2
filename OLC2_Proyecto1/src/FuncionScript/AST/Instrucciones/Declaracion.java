/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionScript.AST.Instrucciones;

import FuncionScript.AST.Expresiones.Expresion;
import FuncionScript.AST.Expresiones.Identificador;
import FuncionScript.Entorno.Entorno;
import FuncionScript.Entorno.Simbolo;
import FuncionScript.Entorno.Tipo;
import java.util.LinkedList;

/**
 *
 * @author rm
 */
public class Declaracion implements Instruccion{
    String id;
    LinkedList<Identificador> lstId;
    Tipo tipo;
    Expresion exp;
    boolean vinoExp;
    int linea;
    
    public Declaracion(LinkedList<Identificador> lstId,Expresion exp,int linea){
        this.lstId = lstId;
        this.tipo = new Tipo(Tipo.TipoFS.NULL);
        this.exp = exp;
        this.linea = linea;
        this.vinoExp = true;
    }
    
    public Declaracion(String id,int linea){
        this.id = id;
        this.linea = linea;
        this.vinoExp = false;
        this.tipo = new Tipo(Tipo.TipoFS.NULL);
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        for(int i =0; i < lstId.size(); i++){
            if(ent.get(lstId.get(i).getIdentificador()) != null){
                System.out.println("Error el identificador ya existe"); //ERROR
                break;
            } else{
                //DEBO PREGUNTAR SI ES EL ULTIMO
                if(i == lstId.size()-1){
                    if(vinoExp){ //ASIGNAMOS EL VALOR
                        Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(),tipo); //aca tipo ya es null
                        ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                        if(exp.getValor(ent) != null){
                            nuevoSimbolo.setValor(exp.getValor(ent));
                            tipo = exp.getTipo(ent);
                        } else
                            System.out.println("Error obteniendo el valor de expresion en clase Declaracion");
                        break;
                    }
                } 
                Simbolo nuevoSimbolo = new Simbolo(lstId.get(i).getIdentificador(),tipo); //aca tipo ya es null
                ent.put(lstId.get(i).getIdentificador(), nuevoSimbolo);
                nuevoSimbolo.setValor(null);
            }
        }
       return null;
    }

    @Override
    public int getLine() {
       return linea;
    }
    
}
