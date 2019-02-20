/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.Expresiones.Operacion;

import AST.Expresiones.Expresion;
import Entorno.Entorno;
import Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Aritmetica extends Operacion implements Expresion {

    public Aritmetica(Expresion exp1, Expresion exp2, Operador tipoOperador) {
        super(exp1, exp2, tipoOperador);
    }
    public Aritmetica(Expresion exp1, Operador tipoOperador) {
        super(exp1, tipoOperador);
    }
    
    @Override
    public Object getValor(Entorno ent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Object a = (exp1 == null)? null:exp1.getValor(ent);
        Object b = (exp2 == null)? null:exp2.getValor(ent);
        
        if(tipoOperador == Operador.SUMA){
            if(a instanceof Integer && b instanceof Integer){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                return (int)a + (int)b;
            }
            else if(a instanceof String && b instanceof String){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.STRING);
                return (String)a + (String)b;
            }
            else{
                System.out.println("Error en la suma de tipos ");
                return null;
            }
        } else if(tipoOperador == Operador.MULTIPLICACION){
            if(a instanceof Integer && b instanceof Integer){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                return (int)a * (int)b;
            } else {
                System.out.println("Error ne la multiplicacion de tipos");
            }
        } else if( a instanceof Integer){
            System.out.println("hola mundo");
        } else {
            return null;
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return tipoResult;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
