/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2_tarea1;

/**
 *
 * @author rm
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;
import javax.swing.JOptionPane;

public class OLC2_Tarea1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Se movieron todos los archivos");
        vista v = new vista();
        v.show();
//        String archLexico = "lexico.jflex";
//        String archSintactico = "sintactico.cup";
//        String[] aLexico = {archLexico};
//        String[] aSintactico = {"-parser","Parser",archSintactico};
//        jflex.Main.main(aLexico);
//        System.out.println("Genero lexico");
//        
//        try {
//            java_cup.Main.main(aSintactico);
//            System.out.println("Genero Sintactico");
//        } catch (Exception e) {
//            System.out.println("******************** ERROR ******************\n NO SE GENERO EL ANALISIS SINTACTICO ");
//        }
//        
//        //parte donde se mueven los archivos generados
//        
//        boolean mvALex = moverArchivo("scanner.java");
//        boolean mvASint = moverArchivo("parser.java");
//        boolean mvSym = moverArchivo("sym.java");
//        if(mvALex && mvASint && mvSym){
//            System.out.println("Se movieron todos los archivos");
//            vista v = new vista();
//            v.show();
//        }
//            
       
    }
    
    public static boolean moverArchivo(String archNombre){
        boolean exito = false;
        File arch = new File(archNombre);
        if(arch.exists()){
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString() + File.separator
                    + "src" + File.separator
                    + "olc2_tarea1" + File.separator
                    + arch.getName();
            
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if(arch.renameTo(new File(nuevoDir))){
                System.out.println("Se Movio " + archNombre);
                exito = true;
            }else{
                System.out.println("No se movio: " + archNombre);
            }
            
        }else{
            System.out.println("El archivo no se puede mover porque no existe: " + archNombre);
        }
        return exito;
    }
    
}
