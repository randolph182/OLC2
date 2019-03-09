/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2_proyecto1.Editor;

import FuncionScript.ErroresFS.ErrorFS;
import FuncionScript.ErroresFS.ManejadorErroresFS;
//import com.sun.xml.internal.txw2.TXW;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JTextArea;

/**
 *
 * @author rm
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    
    public static JTextArea consola;
    public Editor() {
        initComponents();
        consola = jtxtConsola;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtAreaTrabajo = new javax.swing.JTextArea();
        btnCompilar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtConsola = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxtAreaTrabajo.setColumns(20);
        jtxtAreaTrabajo.setRows(5);
        jScrollPane1.setViewportView(jtxtAreaTrabajo);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        jtxtConsola.setColumns(20);
        jtxtConsola.setRows(5);
        jScrollPane2.setViewportView(jtxtConsola);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addComponent(btnCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero =  new FileWriter("entrada.txt");
            pw = new PrintWriter(fichero);
            //la alternativa mas rapida es  pw.println(txtAreaTrabajo.getText());
            char[] informacion = jtxtAreaTrabajo.getText().toCharArray();
            for (int i = 0; i < jtxtAreaTrabajo.getText().length(); i++) {
                if(informacion[i] == '\n')
                    pw.println("");
                else
                    pw.print(informacion[i]);
            }
        } catch (Exception e) {
            System.out.println("Error en la escritura del archivo en Editor" + e);
        } finally{
            try {
                //si utiliza el finally par asegurarse de haber cerrado el archivo
                if(null != fichero)
                    fichero.close();
                    analizar("entrada.txt"); //ANALIZAMOS EL ARCHIVO DE ENTRADA
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo en la Clase Editor" + e);
            }
        }
        
    }//GEN-LAST:event_btnCompilarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompilar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtxtAreaTrabajo;
    private javax.swing.JTextArea jtxtConsola;
    // End of variables declaration//GEN-END:variables

    public static void analizar(String path){
        analizadores.FS.sintacticoFS parserFS;
        try {
        
            parserFS = new analizadores.FS.sintacticoFS(new analizadores.FS.lexicoFS(new FileInputStream(path)));
            parserFS.parse();
//            parserFS.ast.ejecutar();
//            ManejadorErroresFS e = ManejadorErroresFS.getInstance();
//            for(ErrorFS fs:e.getTablaErrores()){   
//                System.out.println("tipo: " +fs.getTipo()+ " descripcion "+ fs.getDescripcion());
//            }
        } catch (Exception e) {
            ManejadorErroresFS.getInstance();
            System.out.println("Error Fatal al trata de analizar el archivo");
            System.out.println("Causa " + e.getCause());
        }
        
        
    }
    
    
    public static void insertarTextoConsola(String informacion){
       
       if(consola.getText().equals("")){
           consola.append("> "+informacion);
       } else{
           String texto =  consola.getText();
           consola.setText(""); //limpiamos
           texto += "\n";
           texto += "> " + informacion;
           consola.append(texto);
       }
       
       
       
    }
}
