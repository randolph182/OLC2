
package olc2_proyecto1;
import FuncionScript.ErroresFS.ManejadorErroresFS;
import java.io.FileInputStream;
import olc2_proyecto1.Editor.Editor;
import static olc2_proyecto1.Editor.Editor.consola;
/**
 *
 * @author rm
 */
public class OLC2_Proyecto1 {

    public static ManejadorErroresFS tablaErrores;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Editor e = new Editor();
        e.setVisible(true);
//        analizar2("entrada2.txt");
    }
    
    public static void analizar(String path){
        analizadores.FS.sintacticoFS parserFS;
        try {
            parserFS = new analizadores.FS.sintacticoFS(new analizadores.FS.lexicoFS(new FileInputStream(path)));
            parserFS.parse();
            parserFS.ast.ejecutar();
        } catch (Exception e) {
            System.out.println("Error Fatal al trata de analizar el archivo");
            System.out.println("Causa " + e.getCause());
        }
    }
    
        public static void analizar2(String path){
        analizadores.GXML.sintacticoGXML parserGXML;
        try {
            parserGXML = new analizadores.GXML.sintacticoGXML(new analizadores.GXML.lexicoGXML(new FileInputStream(path)));
            parserGXML.parse();
        } catch (Exception e) {
            ManejadorErroresFS.getInstance();
            System.out.println("Error Fatal al trata de analizar el archivo");
            System.out.println("Causa " + e.getCause());
        }
     }
    
}
