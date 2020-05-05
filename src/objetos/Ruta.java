/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import estructuras.grafos.Arista;
import estructuras.grafos.NodoG;
import java.util.ArrayList;

/**
 *
 * @author yefer
 */
public class Ruta {
    
    //ArrayList<String> listaCamino = new ArrayList<>();
    public ArrayList<NodoG> listaGrafosCaminos;
    double distancia, desgaste, tiempo;
    public String texto;
    
    
    public Ruta(ArrayList<NodoG> listaGrafosCaminos){
        this.listaGrafosCaminos = listaGrafosCaminos;
        distancia =0;
    }
    
    
    public void calcularDatos(){
        NodoG buscar = null;
        texto = listaGrafosCaminos.get(0).idNodo;
        for (int i = 0; i < (listaGrafosCaminos.size()-1); i++) {
            
                buscar = listaGrafosCaminos.get(i+1);
                texto += " = "+listaGrafosCaminos.get(i+1).idNodo;
            Arista dato = buscarNodo(buscar,listaGrafosCaminos.get(i).listaAristas);
            if(dato!=null){
                distancia += dato.registro.getDistancia();
            }else{
                distancia +=0;
            }
        }
    }
    
    public Arista buscarNodo(NodoG nodo, ArrayList<Arista> lista){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).destino.equals(nodo)){
                return lista.get(i);
            }
        }
        return null;
    }
    
    
    
}
