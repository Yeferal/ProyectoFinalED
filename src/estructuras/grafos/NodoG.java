
package estructuras.grafos;

import java.util.ArrayList;
import objetos.Registro;

public class NodoG {
    public ArrayList<Arista> listaAristas;
    public ArrayList<Arista> listaAristasNoDirigida;
    public String idNodo;
    
    public boolean paso;
    Registro registro;
    
    public NodoG(String idNodo){
        listaAristas = new ArrayList<>();
        listaAristasNoDirigida = new ArrayList<>();
        this.idNodo = idNodo;
        paso = false;
        //this.registro = registro;
    }
    
    public void agregarArista(Arista arista){
        listaAristas.add(arista);
    }
    public void agregarAristaNoDirigida(Arista arista){
        listaAristasNoDirigida.add(arista);
    }
    
    
}
