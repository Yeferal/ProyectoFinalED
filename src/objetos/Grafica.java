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
        
        Icon icono = null;
        icono = imagen;
        
        for (Component c : inicio.label.getComponents()) {
            inicio.label.remove(c);
        }

        //System.out.println(inicio.scrollPane.getViewport().toString());
        //inicio.scrollPane.getViewport().removeAll();
        JLabel lab = new JLabel(icono);
        inicio.scrollPane.setViewportView(lab);
        //System.out.println(inicio.scrollPane.getViewport().toString());
        
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
