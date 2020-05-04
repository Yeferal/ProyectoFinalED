
package ventanas;

import analizador.AnalizadorLexico;
import analizador.AnalizadorSintactico;
import archivos.Archivo;
import estructuras.grafos.Grafo;
import estructuras.grafos.NodoG;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import objetos.Registro;

public class Inicio extends javax.swing.JFrame {

    private Archivo archivo = new Archivo();
    private ArrayList<Registro> listaR = null;
    Grafo grafo;
    
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void pintar(){
        for (int i = 0; i < listaR.size(); i++) {
            System.out.println(listaR.get(i).toString());
        }
    }
    
    public void generarGrafo(){
        grafo = new Grafo();
        
        for (int i = 0; i < listaR.size(); i++) {
            if(grafo.buscarNodo(listaR.get(i).getOrigen())==null){
                
                NodoG nuevo = new NodoG(listaR.get(i).getOrigen());
                grafo.insertaNodo(nuevo);
            }
            if(grafo.buscarNodo(listaR.get(i).getDestino())==null){
                
                NodoG nuevo = new NodoG(listaR.get(i).getDestino());
                grafo.insertaNodo(nuevo);    
            }
            
            grafo.agregarArista(listaR.get(i));
        }
    }
    public void iniciarGrafo(){
        try {
            archivo.crearArchivo(grafo.generarTexto(), "Graficas/grafo.dot");
            String comando = "dot -Tjpg Graficas/grafo.dot -o Graficas/grafo.jpg";
            Runtime rt = Runtime.getRuntime();
            rt.exec( comando );
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mostrarGrafo(){
        ImageIcon imagen = new ImageIcon("Graficas/grafo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(labelGrafo.getWidth(), labelGrafo.getHeight(), Image.SCALE_SMOOTH));
        labelGrafo.setIcon(icono);
        panel.repaint();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        labelGrafo = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
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
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(labelGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(labelGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 920, 770));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 770));

        menuAbrirArchivo.setText("Archivo");

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
                //pintar();
                generarGrafo();
                iniciarGrafo();
                mostrarGrafo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel labelGrafo;
    private javax.swing.JMenu menuAbrirArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel2;
    // End of variables declaration//GEN-END:variables
}
