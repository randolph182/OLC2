
package AST.Instrucciones;
import AST.nodoAST;
import Entorno.*;
/**
 *
 * @author rm
 */
public interface Instruccion extends nodoAST{
    Object ejecutar(Entorno ent);
}
