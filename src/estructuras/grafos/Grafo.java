
package estructuras.grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import objetos.Registro;
import objetos.Ruta;

public class Grafo {
    
    public ArrayList<NodoG> listaNodo;
    public ArrayList<NodoG> listaNodosRutas;
    public ArrayList<Ruta> listaRutas;
    String texto;
    
    public Grafo(){
        listaNodo = new ArrayList<>();
        listaNodosRutas = new ArrayList<>();
        listaRutas = new ArrayList<>();
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
                    listaNodo.get(i).agregarAristaNoDirigida(aris);
                    buscarNodo(idDestino).agregarAristaNoDirigida(aris);
                    break;
                
            }
        }
    }
    public void limpiarRutas(){
        if(listaNodosRutas.size()>0){
            listaNodosRutas.clear();
            listaRutas.clear();
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
    
    public void calcularRutaVehiculo(NodoG actual, String destino,String camino, LinkedList<NodoG> pila){
        System.out.println("Actual: "+actual.idNodo);
        if(actual.idNodo.equals(destino)){
            System.out.println("Termina");
            ArrayList<NodoG> lista = new ArrayList<>();
            LinkedList<NodoG> pilaAux = new LinkedList<>();
            while (pila.peek()!=null) {                
                NodoG aux = pila.pop();
                pilaAux.push(aux);
                listaNodosRutas.add(aux);
            }
            NodoG aux1 = pilaAux.pop();
            NodoG aux2 = pilaAux.pop();
            System.out.println("NOmbreP: "+aux2.idNodo);
            listaNodosRutas.add(aux1);
            listaNodosRutas.add(aux2);
            lista.add(aux1);
            lista.add(aux2);
                pila.push(aux1);
                pila.push(aux2);
//                System.out.print("->"+aux);
            while (pilaAux.peek()!=null) {                
                //String aux = colaAux.poll();
                NodoG aux = pilaAux.pop();
                pila.push(aux);
                listaNodosRutas.add(aux);
                lista.add(aux);
                System.out.print("->"+aux);
            }
            
            listaRutas.add(new Ruta(lista));
            System.out.println("");
            
            
        }else{
            for (int i = 0; i < actual.listaAristas.size(); i++) {
                if(!actual.listaAristas.get(i).destino.paso){
                    actual.listaAristas.get(i).destino.paso = true;
                    pila.push(actual.listaAristas.get(i).destino);
                    
                    calcularRutaVehiculo(actual.listaAristas.get(i).destino, destino, camino,pila);
                    
                }
            
            }
            
        }
        actual.paso = false;
        pila.pop();
        
    }
    
    public String generarTexto(){
        texto = "digraph A{\n";
        texto += "node [shape = circle];\n";
        if(listaNodo.size()>0){
            recorrer(listaNodo.get(0));
        }else{
            texto += "Vacio";
        }
        texto += "rankdir=LR;\n";
        texto += "}\n";
        return texto;
    }
    public String generarTextoCabeza(){
        texto = "";
        if(listaNodo.size()>0){
            recorrer(listaNodo.get(0));
        }else{
            texto += "Vacio";
        }
        
        return texto;
    }
    
    
    public void recorrer(NodoG actual){
        for (int i = 0; i < listaNodo.size(); i++) {
            for (int j = 0; j < listaNodo.get(i).listaAristas.size(); j++) {
                texto += listaNodo.get(i).idNodo+" -> "+listaNodo.get(i).listaAristas.get(j).destino.idNodo+"[label = \""+listaNodo.get(i).listaAristas.get(j).registro.getDistancia()+"\"];\n";
            }
        }
    }
    
    
}
