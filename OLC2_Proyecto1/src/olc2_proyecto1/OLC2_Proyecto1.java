
package olc2_proyecto1;
import java.io.FileInputStream;
import olc2_proyecto1.Editor.Editor;
/**
 *
 * @author rm
 */
public class OLC2_Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Editor e = new Editor();
        e.setVisible(true);
      //  analizar("entrada.txt");
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
    
}
