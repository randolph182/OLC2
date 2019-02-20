/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion3;

import java.io.FileInputStream;

/**
 *
 * @author rm
 */
public class Sesion3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        analizar("entrada.txt");
    }

    public static void analizar(String path) {
        analizadores.sintactico parser;
//        LinkedList<Instruccion> ast = null;
        try {
            parser = new analizadores.sintactico(new analizadores.lexico(new FileInputStream(path)));
            parser.parse();
//            ast = parser.raiz;
            parser.ast.ejecutar();
        } catch (Exception e) {
            System.out.println("Error Fatal al tratar de analizar el archivo");
            System.out.println("Causa" + e.getCause());
        }
//        ejecutar(ast);
    }

//    public static void ejecutar(LinkedList<Instruccion> raiz) {
//        if (raiz != null) {
//            entorno tabla = new entorno(null);
//            for (Instruccion ins : raiz) {
//                if (ins != null) {
//                    ins.ejecutar(tabla);
//                }
//            }
//        } else {
//            System.out.println("No es posible realizar la operaicon porque hubo problemas en el arbol");
//        }
//    }
}
