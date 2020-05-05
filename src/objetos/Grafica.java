/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import ventanas.Inicio;

/**
 *
 * @author yefer
 */
public class Grafica extends Thread{
    
    Inicio inicio;
    
    public Grafica(Inicio inicio){
        this.inicio = inicio;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("inicia");
            iniciarGrafo();
            System.out.println("para");
            Thread.sleep(100);
//            System.out.println("regresa");
            mostrar();
            System.out.println("termina");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void mostrar(){
        ImageIcon imagen = null;
            imagen = new ImageIcon("Graficas/grafo.jpg");
            try {
            Desktop.getDesktop().open(new File("Graficas/grafo.jpg"));
        } catch (IOException ex) {
            
        }
            inicio.contador=1;
        
            inicio.contador=0;
        
        //Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        Icon icono = null;
        icono = imagen;
        
        for (Component c : inicio.label.getComponents()) {
            inicio.label.remove(c);
        }
        inicio.label.revalidate();
//        inicio.label.remove(0);
        inicio.label.updateUI();//
        //inicio.scrollPane.removeAll();
        //inicio.label.repaint();
        inicio.label.updateUI();
        //inicio.label.removeAll();
        inicio.label.setIcon(null);
        inicio.label.repaint();
        inicio.label.updateUI();
        inicio.label.repaint();
        //inicio.label.removeAll();
        inicio.label.setIcon(icono);
        inicio.label.repaint();
        inicio.label.updateUI();
        inicio.label.repaint();
        //inicio.label.removeAll();
        //inicio.label.repaint();
        //inicio.scrollPane.add(inicio.label);
        //inicio.scrollPane.setViewportView(inicio.label);
        //inicio.scrollPane.updateUI();
        
    }
    
    public void iniciarGrafo(){
        
            try {
                inicio.archivo.crearArchivo(inicio.grafo.generarTexto(), "Graficas/grafo.dot");
                String comando = "dot -Tjpg Graficas/grafo.dot -o Graficas/grafo.jpg";
                Runtime rt = Runtime.getRuntime();
                rt.exec( comando );
                
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
}
