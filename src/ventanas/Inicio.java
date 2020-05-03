
package ventanas;

import analizador.AnalizadorLexico;
import analizador.AnalizadorSintactico;
import archivos.Archivo;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import objetos.Registro;

public class Inicio extends javax.swing.JFrame {

    private Archivo archivo = new Archivo();
    private ArrayList<Registro> listaR = null;
    
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void pintar(){
        for (int i = 0; i < listaR.size(); i++) {
            System.out.println(listaR.get(i).toString());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuAbrirArchivo = new javax.swing.JMenu();
        menuItemAbrir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        menuAbrirArchivo.setText("File");

        menuItemAbrir.setText("Abrir Archivo Entrada");
        menuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAbrirActionPerformed(evt);
            }
        });
        menuAbrirArchivo.add(menuItemAbrir);

        menuBar.add(menuAbrirArchivo);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAbrirActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        String ruta = "";
        AnalizadorSintactico sintacitico = null;
        AnalizadorLexico lexico = null;
        if(file!=null){
            ruta = file.getPath();
            lexico = new AnalizadorLexico(new StringReader(archivo.leerArchivo(ruta)));
            sintacitico = new AnalizadorSintactico(lexico);
            try {
                sintacitico.parse();
                listaR = sintacitico.getLista();
                pintar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu menuAbrirArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
