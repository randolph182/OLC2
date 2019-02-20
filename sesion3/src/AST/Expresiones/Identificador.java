package AST.Expresiones;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Tipo;

/**
 *
 * @author rm
 */
public class Identificador implements Expresion{
    String identificador;
    int linea;
    Tipo tipo;
    Object valor;
    
    public Identificador(String id,int linea){
        this.identificador = id;
        this.linea = linea;
    }
    
    
    @Override
    public Object getValor(Entorno ent) {
        if(ent.get(identificador) != null){
            Simbolo s = ent.get(identificador);
            this.tipo = s.getTipo();
            return s.getValor();
        }else{
            System.out.println("> Error el identificador  "+ identificador +"  no existe!!");
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipo;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
