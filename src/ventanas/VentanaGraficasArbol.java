/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javax.swing.ImageIcon;

/**
 *
 * @author yefer
 */
public class VentanaGraficasArbol extends javax.swing.JFrame {

    Inicio inicio;
    
    public VentanaGraficasArbol(Inicio inicio) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.inicio = inicio;
    }
    
    public void setGrafica(String ruta){
        labelGrafica.setIcon(new ImageIcon(ruta));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelGrafica = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(labelGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelGrafica;
    // End of variables declaration//GEN-END:variables
}
