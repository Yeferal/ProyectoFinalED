/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles;

import java.util.ArrayList;

/**
 *
 * @author LENOVO-PC
 */
public class Pagina {
    
    ArrayList<Nodo> listaClaves = null;
    Pagina padre;
    int id;
    
    public Pagina(Pagina padre){
        this.padre = padre;
        listaClaves = new ArrayList<>();
    }
    
    
    public void insertarNodo(Nodo clave){
        listaClaves.add(clave);
        ordenar();
    }
    
    public void ordenar(){
        for (int i = 1; i < listaClaves.size(); i++) {
            int bandera = i;
            Nodo aux = listaClaves.get(i);
                
            while (bandera>0 && aux.clave<listaClaves.get(bandera-1).clave) {                    
                listaClaves.set(bandera, listaClaves.get(bandera-1));
                listaClaves.set(bandera-1, aux);
                bandera--;
                
            } 
        }
    }
    
    public int getTamanio(){
        return listaClaves.size();
    }
    
}
