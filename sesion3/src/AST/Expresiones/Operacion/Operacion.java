package AST.Expresiones.Operacion;
import AST.Expresiones.Expresion;
import Entorno.Entorno;
import Entorno.Tipo;
/**
 *
 * @author rm
 */
public abstract class Operacion {

    Expresion exp1;
    Expresion exp2;
    Operador tipoOperador;
    Tipo tipoResult;
    boolean unario;
    
    public Operacion(Expresion exp1,Expresion exp2,Operador tipoOperador){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.tipoOperador = tipoOperador;
        this.unario = false;
    }
    
    public Operacion(Expresion exp1,Operador tipoOperador){
        this.exp1 = exp1;
        this.tipoOperador = tipoOperador;
        this.unario = true;
    }
   
   
    
    public enum Operador{
        SUMA,
        RESTA,
        MULTIPLICACION,
        BOOLEANO,
        DIVISION,
        NUMERO,
        IDENTIFICADOR,
        CADENA
    }
   
    public abstract Tipo tipoResultado(Tipo t1,Tipo t2);
}
