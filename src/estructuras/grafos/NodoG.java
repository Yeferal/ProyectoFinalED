
package estructuras.grafos;

import java.util.ArrayList;
import objetos.Registro;

public class NodoG {
    ArrayList<Arista> listaAristas;
    String idNodo;
    Registro registro;
    
    public NodoG(String idNodo){
        listaAristas = new ArrayList<>();
        this.idNodo = idNodo;
        //this.registro = registro;
    }
    
    public void agregarArista(Arista arista){
        listaAristas.add(arista);
    }
    
    
}
