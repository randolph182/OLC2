/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2_tarea1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Enumeration;
import javax.swing.JOptionPane;

import olc2_Tarea1.Parser;
import olc2_Tarea1.scanner;
import olc2_tarea1.nodo;
import olc2_tarea1.Entorno;
import olc2_tarea1.Simbolo;
/**
 *
 * @author rm
 */
public class vista extends javax.swing.JFrame {

    /**
     * Creates new form vista
     */
    public vista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaIngreso = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingreso de texto:");

        txtAreaResultado.setColumns(20);
        txtAreaResultado.setRows(5);
        jScrollPane1.setViewportView(txtAreaResultado);

        txtAreaIngreso.setColumns(20);
        txtAreaIngreso.setRows(5);
        txtAreaIngreso.setText("{\n int x; char y;\n  {\n    bool x; int w;\n     {\n       int z;\n     }\n  }\n}");
        jScrollPane2.setViewportView(txtAreaIngreso);

        jLabel2.setText("Resultado: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAnalizar)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        // TODO add your handling code here:
        ejec_archivo();
        ejecutar_analizador();
    }//GEN-LAST:event_btnAnalizarActionPerformed

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
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtAreaIngreso;
    private javax.swing.JTextArea txtAreaResultado;
    // End of variables declaration//GEN-END:variables

public void ejec_archivo(){
    String ruta = "Entrada.txt";
    File archivo = new File(ruta);
    BufferedWriter bw = null;
    if(archivo.exists()){
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(txtAreaIngreso.getText().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas creando el archivo de entrada");
        }
    }else{
        try {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            bw.write(txtAreaIngreso.getText().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    try {
        bw.close();
    } catch (Exception e) {
    }   
}

public void ejecutar_analizador(){
    System.out.println("************ INICIO EJECUCION ******************");
    try {
        Parser p = new Parser(new scanner(new FileReader("Entrada.txt")));
        Object result = p.parse().value;
        Entorno ent = new Entorno(null);
        //limpiamos el area de resultado simpre  que se ejecute una nueva instruccion
        txtAreaResultado.setText("");
        analizarArbolSintactico(p.raiz,ent);
        System.out.println("\n********* resultados finales ********** ");
    } catch(FileNotFoundException ex){
        JOptionPane.showMessageDialog(this,ex);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,e);
    }
}


public void analizarArbolSintactico(nodo actual,Entorno tabla)
{
    if(actual.etiqueta == "S"){
        analizarArbolSintactico(actual.hijos.get(0),tabla);
        String acum = txtAreaResultado.getText();
        acum += "NULL ";
        txtAreaResultado.setText(acum);
    }
    else if(actual.etiqueta == "B"){ //es un ambito 
        analizarArbolSintactico(actual.hijos.get(0),tabla);
        String acum = txtAreaResultado.getText();
        Enumeration<Simbolo> elements = tabla.tabla.elements();
        while(elements.hasMoreElements()) {
            Simbolo tmp = elements.nextElement();
            acum += " Tipo:" + tmp.tipo + " Valor:" + tmp.id;
            
        }
        acum += " ->\n";
        txtAreaResultado.setText(acum);
    }
    else if(actual.etiqueta == "L"){
        for (int i = 0; i < actual.hijos.size(); i++) {
            analizarArbolSintactico(actual.hijos.get(i),tabla);
        }
    }
    else if(actual.etiqueta == "INS"){ //INS -> T id ;
        if(actual.hijos.size() == 2) // T  id
        {
            String tipo = actual.hijos.get(0).valor;
            String val = actual.hijos.get(1).valor;
            Simbolo s = new Simbolo(val, tipo);
            tabla.put(val, s);
            System.out.println(tipo + " " +val);
        }
        else{ // INS -> B
            Entorno ent = new Entorno(tabla);
            analizarArbolSintactico(actual.hijos.get(0),ent);
        }
    }
}

}
