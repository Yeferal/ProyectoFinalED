/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import objetos.Ruta;
import objetos.VerificadorRutas;

/**
 *
 * @author yefer
 */
public class VentanaDatos extends javax.swing.JFrame {

    DefaultTableModel modelo1;
    
    public VentanaDatos() {
        initComponents();
    }
    
    public void setModelos(){
        modelo1 =  new  DefaultTableModel ();
        modelo1 . addColumn ( " Tipo " );
        modelo1 . addColumn ( " Cantidad " );
        modelo1 . addColumn ( " Camino " );
        tabla.setModel (modelo1);
    }

    public void llenarDatosVehiculo(VerificadorRutas verificadorRutas){
        labelTitulo2.setText("En Vehiculo");
        setModelos();
        String dato[] = new String[3];
        dato[0]="Mejores";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        dato[0]="Gasolian";
        dato[1]= ""+verificadorRutas.gasolinaMejor;
        dato[2]= verificadorRutas.gasolinaMejorTexto;
        modelo1.addRow(dato);
        dato[0]="Distancia";
        dato[1]=""+verificadorRutas.distanciaMejor;
        dato[2]= verificadorRutas.distanciaMejorTexto;
        modelo1.addRow(dato);
        dato[0]="Gasolina-Distancia";
        dato[1]=""+verificadorRutas.gasolinaDistanciaMejor;
        dato[2]= verificadorRutas.gasolinaDistanciaMejorTexto;
        modelo1.addRow(dato);
        
        dato[0]=" ";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        
        dato[0]="Peores";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        dato[0]="Gasolian";
        dato[1]= ""+verificadorRutas.gasolinaPeor;
        dato[2]= verificadorRutas.gasolinaPeorTexto;
        modelo1.addRow(dato);
        dato[0]="Distancia";
        dato[1]=""+verificadorRutas.distanciaPeor;
        dato[2]= verificadorRutas.distanciaPeorTexto;
        modelo1.addRow(dato);
        dato[0]="Gasolina-Distancia";
        dato[1]=""+verificadorRutas.gasolinaDistanciaPeor;
        dato[2]= verificadorRutas.gasolinaDistanciaPeorTexto;
        modelo1.addRow(dato);
        
        
    }
    
    public void llenarDatosCaminando(VerificadorRutas verificadorRutas){
        labelTitulo2.setText("Caminando");
        setModelos();
        String dato[] = new String[3];
        dato[0]="Mejores";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        dato[0]="Desgaste Fisico";
        dato[1]= ""+verificadorRutas.desgasteFisicoMejor;
        dato[2]= verificadorRutas.desgasteFisicoMejorTexto;
        modelo1.addRow(dato);
        dato[0]="Distancia";
        dato[1]=""+verificadorRutas.distanciaMejor;
        dato[2]= verificadorRutas.distanciaMejorTexto;
        modelo1.addRow(dato);
        dato[0]="Desgaste-Distancia";
        dato[1]=""+verificadorRutas.desgasteDistanciaMejor;
        dato[2]= verificadorRutas.desgasteDistanciaMejorTexto;
        modelo1.addRow(dato);
        
        dato[0]=" ";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        
        dato[0]="Peores";
        dato[1]=" ";
        dato[2]=" ";
        modelo1.addRow(dato);
        dato[0]="Desgaste Fisico";
        dato[1]= ""+verificadorRutas.desgasteFisicoPeor;
        dato[2]= verificadorRutas.desgasteFisicoPeorTexto;
        modelo1.addRow(dato);
        dato[0]="Distancia";
        dato[1]=""+verificadorRutas.distanciaPeor;
        dato[2]= verificadorRutas.distanciaPeorTexto;
        modelo1.addRow(dato);
        dato[0]="Desgaste-Distancia";
        dato[1]=""+verificadorRutas.desgasteDistanciaPeor;
        dato[2]= verificadorRutas.desgasteDistanciaPeorTexto;
        modelo1.addRow(dato);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        labelTitulo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitulo.setText("Informacion Rutas");

        tabla.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tabla);

        labelTitulo2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 472, Short.MAX_VALUE)
                .addComponent(labelTitulo)
                .addGap(416, 416, 416))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(430, 430, 430))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTitulo)
                .addGap(18, 18, 18)
                .addComponent(labelTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTitulo2;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
