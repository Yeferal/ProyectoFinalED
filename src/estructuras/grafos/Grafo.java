
package estructuras.grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import objetos.Camino;
import objetos.Registro;
import objetos.Ruta;

public class Grafo {
    
    public ArrayList<NodoG> listaNodo;
    public ArrayList<NodoG> listaNodosRutas;
    public ArrayList<Ruta> listaRutas;
//    public ArrayList<Caminos> listaCaminosRutas;
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
                    Arista arisNoDe = new Arista(listaNodo.get(i), registro);
                    listaNodo.get(i).agregarArista(aris);
                    listaNodo.get(i).agregarAristaNoDirigida(aris);
                    buscarNodo(idDestino).agregarAristaNoDirigida(arisNoDe);
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
    public void resetearPasos(){
        for (int i = 0; i < listaNodo.size(); i++) {
            listaNodo.get(i).paso=false;
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
    public void calcularRuta(NodoG actual, String destino,int index, LinkedList<Camino> pila){
        pila.push(new Camino(actual, index));
        actual.paso = true;
        if(actual.idNodo.equals(destino)){
            ArrayList<Camino> lista = new ArrayList<>();
            LinkedList<Camino> pilaAux = new LinkedList<>();
            while (pila.peek()!=null) {                
                Camino aux = pila.pop();
                pilaAux.push(aux);
                listaNodosRutas.add(aux.getNodo());
            }
            Camino aux1 = pilaAux.pop();
            Camino aux2 = pilaAux.pop();
            System.out.println("NOmbreP: "+aux2.getNodo().idNodo);
            listaNodosRutas.add(aux1.getNodo());
            listaNodosRutas.add(aux2.getNodo());
            lista.add(aux1);
            lista.add(aux2);
                pila.push(aux1);
                pila.push(aux2);
            while (pilaAux.peek()!=null) {                
                //String aux = colaAux.poll();
                Camino aux = pilaAux.pop();
                pila.push(aux);
                listaNodosRutas.add(aux.getNodo());
                lista.add(aux);
                System.out.print("->"+aux.getNodo().idNodo);
            }
            listaRutas.add(new Ruta(lista));
        }else{
            
            for (int i = 0; i < actual.listaAristas.size(); i++) {
                if(!actual.listaAristas.get(i).destino.paso){
                    calcularRuta(actual.listaAristas.get(i).destino, destino, i,pila);
                }
            }
        }
        actual.paso = false;
        pila.pop();
    }
    
    
    public void calcularRutaCaminando(NodoG actual, String destino,int index, LinkedList<Camino> pila){
        pila.push(new Camino(actual, index));
        actual.paso = true;
        if(actual.idNodo.equals(destino)){
            ArrayList<Camino> lista = new ArrayList<>();
            LinkedList<Camino> pilaAux = new LinkedList<>();
            while (pila.peek()!=null) {                
                Camino aux = pila.pop();
                pilaAux.push(aux);
                listaNodosRutas.add(aux.getNodo());
            }
            Camino aux1 = pilaAux.pop();
            Camino aux2 = pilaAux.pop();
            System.out.println("NOmbreP: "+aux2.getNodo().idNodo);
            listaNodosRutas.add(aux1.getNodo());
            listaNodosRutas.add(aux2.getNodo());
            lista.add(aux1);
            lista.add(aux2);
                pila.push(aux1);
                pila.push(aux2);
            while (pilaAux.peek()!=null) {                
                //String aux = colaAux.poll();
                Camino aux = pilaAux.pop();
                pila.push(aux);
                listaNodosRutas.add(aux.getNodo());
                lista.add(aux);
                System.out.print("->"+aux.getNodo().idNodo);
            }
            listaRutas.add(new Ruta(lista));
        }else{
            
            for (int i = 0; i < actual.listaAristasNoDirigida.size(); i++) {
                if(!actual.listaAristasNoDirigida.get(i).destino.paso){
                    calcularRutaCaminando(actual.listaAristasNoDirigida.get(i).destino, destino, i,pila);
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
    public String generarTextoCabezaCaminando(){
        texto = "";
        if(listaNodo.size()>0){
            recorrerCaminando(listaNodo.get(0));
        }else{
            texto += "Vacio";
        }
        
        return texto;
    }
    
    
    public void recorrerCaminando(NodoG actual){
        for (int i = 0; i < listaNodo.size(); i++) {
            for (int j = 0; j < listaNodo.get(i).listaAristas.size(); j++) {
                texto += listaNodo.get(i).idNodo+" -- "+listaNodo.get(i).listaAristas.get(j).destino.idNodo+"[label = \""+listaNodo.get(i).listaAristas.get(j).registro.getDistancia()+"\"];\n";
            }
        }
    }
    
    
}
