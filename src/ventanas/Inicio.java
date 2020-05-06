
package ventanas;

import analizador.AnalizadorLexico;
import analizador.AnalizadorSintactico;
import archivos.Archivo;
import estructuras.arboles.Arbol;
import estructuras.arboles.Nodo;
import estructuras.grafos.Arista;
import estructuras.grafos.Grafo;
import estructuras.grafos.NodoG;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import objetos.Camino;
import objetos.Grafica;
import objetos.Registro;
import objetos.Ruta;
import objetos.VerificadorRutas;

public class Inicio extends javax.swing.JFrame {

    public Archivo archivo = new Archivo();
    private ArrayList<Registro> listaR = null;
    String origen,destino;
    boolean caminando;
    public Arbol arbol = new Arbol();
    public Grafo grafo;
    ArrayList<Camino> recorrido = new ArrayList<>();
    VerificadorRutas verificadorRutas;
    VentanaDatos ventanaDatos = new VentanaDatos();
    public JLabel label;
    public int contador = 0;
    
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        panel2.setVisible(true);
        panel3.setVisible(true);
        panel4.setVisible(true);
        label = new JLabel();
        scrollPane.setViewportView(label);
    }
    
    public void setCombox(){
        comboBoxOringe.removeAllItems();
        comboBoxDestino.removeAllItems();
        for (int i = 0; i < grafo.listaNodo.size(); i++) {
            comboBoxOringe.addItem(grafo.listaNodo.get(i).idNodo);
            comboBoxDestino.addItem(grafo.listaNodo.get(i).idNodo);
        }
        panel2.setVisible(true);
    }
    public void setComboxSiguiente(){
        comboSiguiente.removeAllItems();
        
        for (int i = 0; i < grafo.listaRutas.size(); i++) {
            comboSiguiente.addItem(grafo.listaRutas.get(i).listaCaminos.get(1).getNodo().idNodo);
        }
        panel4.setVisible(true);
    }
    
    public void generarGrafo(){
        grafo = new Grafo();
        verificadorRutas = new VerificadorRutas(grafo);
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
    public void mostrarGrafo(){
        
        
        System.out.println("Tamanio: "+scrollPane.getComponents().length);

        ImageIcon imagen = new ImageIcon("Graficas/grafo.jpg");
        //Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        Icon icono = imagen;
        label.setIcon(icono);
        
        panel4.setVisible(true);
        
        panel.repaint();
    }
    
    public void llenarArbol(){
        //verificadorRutas.actualizarDatos(grafo.listaRutas);
        for (int i = 0; i < grafo.listaRutas.size(); i++) {
            Nodo nodo = new Nodo(i, grafo.listaRutas.get(i));
            arbol.insertar(nodo);
        }
    }
    
    
    public void calcularRecorrido(){
        Ruta ru = new Ruta(recorrido);
        if(caminando){
            ru.calcularDatosCaminando();
            labelParametro.setText("Desgaste Fisico: "+ru.desgaste);
           labelDosParametros.setText("Desgante-Distancia: "+ru.distanciaDesgaste);
        }else{
            ru.calcularDatos();
            labelParametro.setText("Consumo Gasolina: "+ru.gasolina);
           labelDosParametros.setText("Gasolina-Distancia: "+ru.distanciaGasolina);
        }
        labelDistancia.setText("Distancia: "+ru.distancia);
//        labelCaminata.setText(destino);
        
    }
    public void calcular(){
        LinkedList<Camino> pila = new LinkedList<>();
        recorrido.add(new Camino(grafo.buscarNodo(comboBoxOringe.getSelectedItem().toString()), 0));
        if(caminando){
            grafo.calcularRutaCaminando(grafo.buscarNodo(comboBoxOringe.getSelectedItem().toString()), comboBoxDestino.getSelectedItem().toString(),0,pila);
            setComboxSiguiente();
            archivo.crearArchivo(verificadorRutas.generarTextoCaminando(origen,destino), "Graficas/grafo1.dot");
        }else{
            grafo.calcularRuta(grafo.buscarNodo(comboBoxOringe.getSelectedItem().toString()), comboBoxDestino.getSelectedItem().toString(),0,pila);
            setComboxSiguiente();
            archivo.crearArchivo(verificadorRutas.generarTexto(origen,destino), "Graficas/grafo1.dot");
        }
        pintar();
        grafo.buscarNodo(comboBoxOringe.getSelectedItem().toString()).paso=true;
    }
    public void calcularSiguiente(){
        LinkedList<Camino> pila = new LinkedList<>();
        if(caminando){
            grafo.calcularRutaCaminando(grafo.buscarNodo(comboSiguiente.getSelectedItem().toString()), comboBoxDestino.getSelectedItem().toString(),0,pila);
            grafo.buscarNodo(comboSiguiente.getSelectedItem().toString()).paso=true;
            setComboxSiguiente();
            archivo.crearArchivo(verificadorRutas.generarTextoCaminando(origen,destino), "Graficas/grafo1.dot");
        }else{
            grafo.calcularRuta(grafo.buscarNodo(comboSiguiente.getSelectedItem().toString()), comboBoxDestino.getSelectedItem().toString(),0,pila);
            grafo.buscarNodo(comboSiguiente.getSelectedItem().toString()).paso=true;
            setComboxSiguiente();
            archivo.crearArchivo(verificadorRutas.generarTexto(origen,destino), "Graficas/grafo1.dot");
        }
        pintar();
        
    }
    public void pintar(){
        try {
                
                String comando = "dot -Tjpg Graficas/grafo1.dot -o Graficas/grafo1.jpg";
                Runtime rt = Runtime.getRuntime();
                rt.exec( comando );
                if(caminando){
                    verificadorRutas.actualizarDatosCaminando(grafo.listaRutas);
                }else{
                    verificadorRutas.actualizarDatos(grafo.listaRutas);
                }
                llenarArbol();
                archivo.crearArchivo(arbol.busqueda(), "Graficas/arbol.dot");
                String comando1 = "dot -Tjpg Graficas/arbol.dot -o Graficas/arbol.jpg";
                
                rt.exec( comando1 );
                Desktop.getDesktop().open(new File("Graficas/grafo1.jpg"));
                Desktop.getDesktop().open(new File("Graficas/arbol.jpg"));
                verificadorRutas.verificarMejorRuta(grafo.listaRutas);
                //verificadorRutas.verificarPeorRuta(grafo.listaRutas);
                //grafo.limpiarRutas();
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        panel2 = new javax.swing.JPanel();
        comboBoxOringe = new javax.swing.JComboBox<>();
        labelOrigen = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();
        comboBoxDestino = new javax.swing.JComboBox<>();
        botonInciarViaje = new javax.swing.JButton();
        labelTipo = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        panel3 = new javax.swing.JPanel();
        botonNuevoViaje = new javax.swing.JButton();
        panel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboSiguiente = new javax.swing.JComboBox<>();
        botonSiguiente = new javax.swing.JButton();
        labelDistancia = new javax.swing.JLabel();
        botonDatos = new javax.swing.JButton();
        labelRecoriido = new javax.swing.JLabel();
        labelParametro = new javax.swing.JLabel();
        labelDosParametros = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        labelCaminata = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        menuAbrirArchivo = new javax.swing.JMenu();
        menuItemAbrir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 990, 770));

        labelOrigen.setText("Origen:");

        labelDestino.setText("Destino:");

        botonInciarViaje.setText("INICIAR VIAJE");
        botonInciarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInciarViajeActionPerformed(evt);
            }
        });

        labelTipo.setText("Tipo:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehiculo", "Caminando" }));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(labelOrigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxOringe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDestino)
                            .addComponent(labelTipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxTipo, 0, 257, Short.MAX_VALUE))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonInciarViaje)
                .addGap(123, 123, 123))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxOringe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrigen))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDestino)
                    .addComponent(comboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonInciarViaje)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 220));

        botonNuevoViaje.setText("Nuevo Viaje");
        botonNuevoViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoViajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(botonNuevoViaje)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(botonNuevoViaje)
                .addContainerGap())
        );

        getContentPane().add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 360, 70));

        panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Siguiente:");
        panel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        panel4.add(comboSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 260, -1));

        botonSiguiente.setText("Siguiente camino");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });
        panel4.add(botonSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        labelDistancia.setText("Distancia:");
        panel4.add(labelDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 240, 30));

        botonDatos.setText("Ver Datos");
        botonDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDatosActionPerformed(evt);
            }
        });
        panel4.add(botonDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, -1));

        labelRecoriido.setText("Recorrido");
        panel4.add(labelRecoriido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 70, 20));
        panel4.add(labelParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 240, 20));
        panel4.add(labelDosParametros, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 240, 30));

        labelCaminata.setEditable(false);
        labelCaminata.setColumns(20);
        labelCaminata.setRows(5);
        jScrollPane1.setViewportView(labelCaminata);

        panel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 290, 60));

        getContentPane().add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 360, 470));

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
//                iniciarGrafo();
//                mostrarGrafo();
                
                Grafica g = new Grafica(this);
                g.start();
                mostrarGrafo();
                setCombox();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed

    private void botonInciarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInciarViajeActionPerformed
       panel3.setVisible(true);
       panel4.setVisible(true);
       comboBoxDestino.setEnabled(false);
       comboBoxOringe.setEnabled(false);
       botonInciarViaje.setEnabled(false);
       botonSiguiente.setEnabled(true);
       
       if(comboBoxTipo.getSelectedIndex()==0){
           caminando = false;
           labelParametro.setText("Consumo Gasolina: 0");
           labelDosParametros.setText("Gasolina-Distancia: 0");
       }else{
           caminando = true;
           labelParametro.setText("Desgaste Fisico: 0");
           labelDosParametros.setText("Desgante-Distancia: 0");
       }
       origen = comboBoxOringe.getSelectedItem().toString();
       destino = comboBoxDestino.getSelectedItem().toString();
       
       if(origen.equals(destino)){
           JOptionPane.showMessageDialog(null, "NO puese viajar al mismos destino de tu origne");
       }else{
           labelCaminata.setText(origen);
           labelDistancia.setText("Distancia: 0");
           recorrido.clear();
           grafo.limpiarRutas();
           recorrido.add(new Camino(grafo.buscarNodo(origen), 0));
           calcular();
       }
       


    }//GEN-LAST:event_botonInciarViajeActionPerformed

    private void botonNuevoViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoViajeActionPerformed
       comboBoxDestino.setEnabled(true);
       comboBoxOringe.setEnabled(true);
       botonInciarViaje.setEnabled(true);
       recorrido.clear();
       grafo.resetearPasos();
       comboSiguiente.removeAllItems();
       //calcularRecorrido();
    }//GEN-LAST:event_botonNuevoViajeActionPerformed

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        
        arbol.borrarArbol();
            if(comboSiguiente.getSelectedItem().toString().equals(destino)){
                labelCaminata.setText(labelCaminata.getText()+" -> "+comboSiguiente.getSelectedItem().toString());
                calcularRecorrido();
                recorrido.add(grafo.listaRutas.get(comboSiguiente.getSelectedIndex()).listaCaminos.get(1));
                
                botonSiguiente.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Exelente has llegado a tu ruta");
                
                
            }else{
                calcularRecorrido();
                labelCaminata.setText(labelCaminata.getText()+" -> "+comboSiguiente.getSelectedItem().toString());
                recorrido.add(grafo.listaRutas.get(comboSiguiente.getSelectedIndex()).listaCaminos.get(1));
                
                grafo.limpiarRutas();
                calcularSiguiente();
            }
    }//GEN-LAST:event_botonSiguienteActionPerformed

    private void botonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDatosActionPerformed
        if(caminando){
            ventanaDatos.llenarDatosCaminando(verificadorRutas);
        }else{
            ventanaDatos.llenarDatosVehiculo(verificadorRutas);
        }
        ventanaDatos.setVisible(true);
        
    }//GEN-LAST:event_botonDatosActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDatos;
    private javax.swing.JButton botonInciarViaje;
    private javax.swing.JButton botonNuevoViaje;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JComboBox<String> comboBoxDestino;
    private javax.swing.JComboBox<String> comboBoxOringe;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JComboBox<String> comboSiguiente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea labelCaminata;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelDistancia;
    private javax.swing.JLabel labelDosParametros;
    private javax.swing.JLabel labelOrigen;
    private javax.swing.JLabel labelParametro;
    private javax.swing.JLabel labelRecoriido;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JMenu menuAbrirArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    public javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
