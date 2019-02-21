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
        Object a = (exp1 == null)? null:exp1.getValor(ent);
        Tipo tipoA = exp1.getTipo(ent);
        Object b = (exp2 == null)? null:exp2.getValor(ent);
        Tipo tipoB = exp2.getTipo(ent);
        
        if(tipoOperador == Operador.SUMA){
            if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "INT")
            {
                tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                return (int)a + (int)b;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "DOUBLE"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                return (Double)a + (Double)b;
            } else if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "DOUBLE"){
                 tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result = new Double((int)a) + (Double)b;
                return result;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "INT"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result =  (Double)a + new Double((int)b)  ;
                return result;
            } else{
                System.out.println("Error en la suma de tipos");
                return null;
            }
        } else if(tipoOperador == Operador.RESTA){
            
            if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "INT")
            {
                tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                return (int)a - (int)b;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "DOUBLE"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                return (Double)a - (Double)b;
            } else if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "DOUBLE"){
                 tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result = new Double((int)a) - (Double)b;
                return result;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "INT"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result =  (Double)a - new Double((int)b)  ;
                return result;
            } else{
                System.out.println("Error en la resta de tipos");
                return null;
            }
        } else if(tipoOperador == Operador.MULTIPLICACION){
            if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "INT")
            {
                tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                return (int)a * (int)b;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "DOUBLE"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                return (Double)a * (Double)b;
            } else if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "DOUBLE"){
                 tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result = new Double((int)a) * (Double)b;
                return result;
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "INT"){
                tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                double result =  (Double)a * new Double((int)b)  ;
                return result;
            } else{
                System.out.println("Error en la multiplicacion de tipos");
                return null;
            }
        } else if(tipoOperador == Operador.DIVISION){
            if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "INT") {
                if((int)b != 0){
                    tipoResult = new Tipo(Tipo.TipoPrimitivo.INT);
                    return (int)a / (int)b;
                }else
                    System.out.println("> Error no se puede hacer la division por 0");
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "DOUBLE"){
                
                if((Double)b != 0){
                    tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                    return (Double)a / (Double)b;
                }else
                    System.out.println("> Error no se puede hacer la division por 0");
                
            } else if(tipoA.getTipoString() == "INT" && tipoB.getTipoString() == "DOUBLE"){
                if((Double)b != 0){
                    tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                    double result = new Double((int)a) / (Double)b;
                    return result;  
                }else
                    System.out.println("> Error no se puede hacer la division por 0");
                 
            } else if(tipoA.getTipoString() == "DOUBLE" && tipoB.getTipoString() == "INT"){

                if((int) b != 0){
                    tipoResult = new Tipo(Tipo.TipoPrimitivo.DOUBLE);
                    double result =  (Double)a / new Double((int)b)  ;
                    return result;
                }else
                    System.out.println("> Error no se puede hacer la division por 0");
                
            } else{
                System.out.println("Error en la division de tipos");
                return null;
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

    @Override
    public Tipo tipoResultado(Tipo t1, Tipo t2) {
       if(t1.isDouble() && t2.isDouble()){
           return new Tipo(Tipo.TipoPrimitivo.DOUBLE);
       } else if(t1.isString() || t2.isString()){
           if(tipoOperador == Operador.SUMA)
               return new Tipo(Tipo.TipoPrimitivo.STRING);
           else 
               return new Tipo(Tipo.TipoPrimitivo.NULL);
       } else if(t1.isInt() && t2.isInt()){
           return new Tipo(Tipo.TipoPrimitivo.INT);
       } else if(t1.isDouble() || t2.isDouble()){
           if(t1.isInt() || t2.isInt()){
               return new Tipo(Tipo.TipoPrimitivo.DOUBLE);
           } else
               return new Tipo(Tipo.TipoPrimitivo.NULL);
       }
        return null;
    }

}
