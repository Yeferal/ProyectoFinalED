/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import estructuras.grafos.NodoG;

/**
 *
 * @author yefer
 */
public class Camino {
    
    NodoG nodo;
    int id;

    public Camino(NodoG nodo, int id) {
        this.nodo = nodo;
        this.id = id;
    }

    public NodoG getNodo() {
        return nodo;
    }

    public void setNodo(NodoG nodo) {
        this.nodo = nodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
