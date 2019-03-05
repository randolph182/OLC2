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
public class Asignacion_Operacion implements  Instruccion{

    String id;
    Expresion exp;
    TipoAO tipoAO;
    int linea;

    public Asignacion_Operacion(String id,  TipoAO tipoAO, Expresion exp,int linea) {
        this.id = id;
        this.exp = exp;
        this.tipoAO = tipoAO;
        this.linea = linea;
        TipoAO a = TipoAO.DIVISION;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object result = exp.getValor(ent);
        Tipo tipoResult = exp.getTipo(ent);
        if(result != null  && tipoResult.isNumeric()){ //VERIFICAMOS QUE VALOR NO SEA NULL Y QUE SEA NUMERICO
            if(ent.get(id) != null){
                Simbolo s = ent.get(id); //obtenemos el simbolo 
                Tipo tipoId = s.getTipo();
                if(tipoId.isNumeric()){ //si la variable es de tiop numerico
                    double valId = new Double((String)s.getValor());
                    switch(tipoAO){
                        case SUMA:
                          valId += new Double((String)result);
                          s.setValor(valId);
                        break;

                        case RESTA:
                          valId -= new Double((String)result);
                          s.setValor(valId);
                        break;

                        case MULTIPLICACION:
                          valId *= new Double((String)result);
                          s.setValor(valId);
                        break;

                        case DIVISION:
                          valId /= new Double((String)result);
                          s.setValor(valId);
                        break;
                    }
                } else
                    System.out.println("Error no se puede  asginar y operar un valor que no sea numerico");
            } else{
                System.out.println("El identificador no existe");
            }
            
        } else
            System.out.println("> Error porque hay nulo en ejecutar Asignacion_Operacion");
        return null;
    }

    @Override
    public int getLine() {
       return linea;
    }
    
    public enum TipoAO{
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION
    }
    
}