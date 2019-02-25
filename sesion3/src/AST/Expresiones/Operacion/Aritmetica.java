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
        
        tipoResult = tipoResultado(tipoA, tipoB);
        
        switch(tipoResult.getTipoPrimitivo()){
            case INT:
                if(tipoOperador == Operador.SUMA)
                    return new Integer(a.toString()) + new Integer( b.toString());
                else if(tipoOperador == Operador.RESTA)
                    return new Integer(a.toString()) - new Integer( b.toString());
                else if(tipoOperador == Operador.MULTIPLICACION)
                    return new Integer(a.toString()) * new Integer( b.toString());
                else if(tipoOperador == Operador.DIVISION){
                    if(new Integer( b.toString()) != 0)
                        return new Integer(a.toString())/ new Integer( b.toString());
                    else
                        System.out.println(">Error el operador 2 no puede dividir debido a que es 0");
                }
                else
                    System.out.println(">El tipo de operacion no esta especificada en el switch de INT");
               break;
            
            case DOUBLE:
                if(tipoOperador == Operador.SUMA)
                     return new Double(a.toString()) + new Double( b.toString());
                else if(tipoOperador == Operador.RESTA)
                    return new Double(a.toString()) - new Double( b.toString());
                else if(tipoOperador == Operador.MULTIPLICACION)
                    return new Double(a.toString()) * new Double( b.toString());
                else if(tipoOperador == Operador.DIVISION){
                    if(new Double( b.toString()) != 0)
                        return new Double(a.toString()) / new Double( b.toString());
                    else
                        System.out.println(">Error el operador 2 no puede dividir debido a que es 0");
                }
                else
                    System.out.println(">El tipo de operacion no esta especificada en el switch de Double");
               break;
            
            case STRING:
                if(tipoOperador == Operador.SUMA)
                     return a.toString() +  b.toString();
                else
                    System.out.println(">Error soloo se permiten sumas de Cadenas de caracteres");
               break;
           
            case NULL:
                System.out.println(">Algo salio mal porque vino un NULL en aritmetica");
               break;
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
