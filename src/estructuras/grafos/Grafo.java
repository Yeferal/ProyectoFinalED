
package estructuras.grafos;

import java.util.ArrayList;
import objetos.Registro;

public class Grafo {
    
    ArrayList<NodoG> listaNodo;
    String texto;
    
    public Grafo(){
        listaNodo = new ArrayList<>();
    }
    
    public void insertaNodo(NodoG nodo){
        listaNodo.add(nodo);
    }
    
    public void agregarArista(Registro registro){
        String idOringen = registro.getOrigen();
        String idDestino = registro.getDestino();
        for (int i = 0; i < listaNodo.size(); i++) {
            if(listaNodo.get(i).idNodo.equals(idOringen)){
                NodoG nuevo = buscarNodo(idDestino);
                    Arista aris = new Arista(nuevo, registro);
                    listaNodo.get(i).agregarArista(aris);
                    //System.out.println("Se agrego la arista");
                    break;
                
            }
        }
    }
    
    public NodoG buscarNodo(String id){
        for (int i = 0; i < listaNodo.size(); i++) {
            //System.out.println(listaNodo.get(i).idNodo);
            if(listaNodo.get(i).idNodo.equals(id)){
                //System.out.println(listaNodo.get(i).idNodo);
                return listaNodo.get(i);
            }
            
        }
        return null;
    }
    public void pintar(){
        for (int i = 0; i < listaNodo.size(); i++) {
            System.out.println(listaNodo.get(i).idNodo);
            
            
        }
    }
    
    public String generarTexto(){
        texto = "digraph A{\n";
        if(listaNodo.size()>0){
            recorrer(listaNodo.get(0));
        }else{
            texto += "Vacio";
        }
        texto += "rankdir=LR;\n";
        texto += "}\n";
        return texto;
    }
    
    
    public void recorrer(NodoG actual){
        for (int i = 0; i < listaNodo.size(); i++) {
            for (int j = 0; j < listaNodo.get(i).listaAristas.size(); j++) {
                texto += listaNodo.get(i).idNodo+" -> "+listaNodo.get(i).listaAristas.get(j).destino.idNodo+";\n";
            }
        }
    }
    
    
}
