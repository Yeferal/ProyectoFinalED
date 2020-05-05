
package objetos;

import estructuras.grafos.Grafo;
import estructuras.grafos.NodoG;
import java.util.ArrayList;

public class VerificadorRutas {
    
    Grafo grafo;
    String texto;
    
    public VerificadorRutas(Grafo grafo){
        this.grafo = grafo;
    }
    
    
    public String generarTexto(){
        texto = "digraph A{\n";
        texto += "node [shape = circle];\n";
        //grafo.limpiarRutas();
        texto += grafo.generarTextoCabeza();
        generarNodosCaminos(grafo.listaNodosRutas);
        //grafo.limpiarRutas();
        texto += "rankdir=LR;\n";
        texto += "}\n";
        
        
        return texto;
    }
    
    public void generarNodosCaminos(ArrayList<NodoG> lista){
        for (int i = 0; i < lista.size(); i++) {
            texto += lista.get(i).idNodo+" [color = red];\n";
        }
    }
    public void actualizarDatos(ArrayList<Ruta> lista){
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).calcularDatos();
        }
    }
    
    public void verificarMejorRuta(ArrayList<Ruta> lista){
        
        String mejor = "la mejor ruta es: "+lista.get(0).texto+" con una distancia de: "+lista.get(0).distancia;
        for (int i = 0; i < lista.size()-1; i++) {
            if(lista.get(i).distancia>lista.get(i+1).distancia){
                mejor = "la mejor ruta es: "+lista.get(i+1).texto+" con una distancia de: "+lista.get(i+1).distancia;
            }
        }
        System.out.println(mejor);
        
    }
    public void verificarPeorRuta(ArrayList<Ruta> lista){
        
        String mejor = "la peor ruta es: "+lista.get(0).texto+" con una distancia de: "+lista.get(0).distancia;
        for (int i = 0; i < lista.size()-1; i++) {
            if(lista.get(i).distancia<lista.get(i+1).distancia){
                mejor = "la mejor ruta es: "+lista.get(i+1).texto+" con una distancia de: "+lista.get(i+1).distancia;
            }
        }
        System.out.println(mejor);
        
    }
    
    
    
    
}
