
package objetos;

import estructuras.grafos.Grafo;
import estructuras.grafos.NodoG;
import java.util.ArrayList;

public class VerificadorRutas {
    
    Grafo grafo;
    String texto;
    public double distanciaMejor, gasolinaMejor, gasolinaDistanciaMejor, desgasteFisicoMejor, desgasteDistanciaMejor;
    public double distanciaPeor, gasolinaPeor, gasolinaDistanciaPeor, desgasteFisicoPeor, desgasteDistanciaPeor;
    public String distanciaMejorTexto, gasolinaMejorTexto, gasolinaDistanciaMejorTexto, desgasteFisicoMejorTexto, desgasteDistanciaMejorTexto;
    public String distanciaPeorTexto, gasolinaPeorTexto, gasolinaDistanciaPeorTexto, desgasteFisicoPeorTexto, desgasteDistanciaPeorTexto;
    
    public VerificadorRutas(Grafo grafo){
        this.grafo = grafo;
    }
    
    
    public String generarTexto(String origen, String destino){
        texto = "digraph A{\n";
        texto += "node [shape = circle];\n";
        texto += origen+" [shape = doublecircle];\n";
        texto += destino+" [shape = Mcircle];\n";
        //grafo.limpiarRutas();
        texto += grafo.generarTextoCabeza();
        generarNodosCaminos(grafo.listaNodosRutas);
        //grafo.limpiarRutas();
        texto += "rankdir=LR;\n";
        texto += "}\n";
        
        
        return texto;
    }
    public String generarTextoCaminando(String origen, String destino){
        texto = "graph A{\n";
        texto += "node [shape = circle];\n";
        texto += origen+" [shape = doublecircle];\n";
        texto += destino+" [shape = Mcircle];\n";
        //grafo.limpiarRutas();
        texto += grafo.generarTextoCabezaCaminando();
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
        System.out.println("Tamnio rutas: "+lista.size());
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).calcularDatos();
        }
    }
    public void actualizarDatosCaminando(ArrayList<Ruta> lista){
        System.out.println("Tamnio rutas: "+lista.size());
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).calcularDatosCaminando();
        }
    }
    
    public void verificarMejorRuta(ArrayList<Ruta> lista){
        //mejores
        if(lista.size()>0){
            gasolinaMejor = lista.get(0).gasolina;
            distanciaMejor = lista.get(0).distancia;
            gasolinaDistanciaMejor = lista.get(0).distanciaGasolina;
            desgasteFisicoMejor = lista.get(0).desgaste;
            desgasteDistanciaMejor = lista.get(0).distanciaDesgaste;

            gasolinaMejorTexto = lista.get(0).texto;
            distanciaMejorTexto = lista.get(0).texto;
            gasolinaDistanciaMejorTexto = lista.get(0).texto;
            desgasteFisicoMejorTexto = lista.get(0).texto;
            desgasteDistanciaMejorTexto = lista.get(0).texto;
            //peores
            gasolinaPeor = lista.get(0).gasolina;
            distanciaPeor = lista.get(0).distancia;
            gasolinaDistanciaPeor = lista.get(0).distanciaGasolina;
            desgasteFisicoPeor = lista.get(0).desgaste;
            desgasteDistanciaPeor = lista.get(0).distanciaDesgaste;

            gasolinaPeorTexto = lista.get(0).texto;
            distanciaPeorTexto = lista.get(0).texto;
            gasolinaDistanciaPeorTexto = lista.get(0).texto;
            desgasteFisicoPeorTexto = lista.get(0).texto;
            desgasteDistanciaPeorTexto = lista.get(0).texto;
        }
        
        for (int i = 0; i < lista.size()-1; i++) {
//            System.out.println("NDODODO:    "+lista.get(i).texto+"        distancia: "+lista.get(i).distancia);
            if(gasolinaMejor>lista.get(i+1).gasolina){
                gasolinaMejor = lista.get(i+1).gasolina;
                gasolinaMejorTexto = lista.get(i+1).texto;
            }
            
            if(distanciaMejor>lista.get(i+1).distancia){
                distanciaMejor = lista.get(i+1).distancia;
                distanciaMejorTexto = lista.get(i+1).texto;
            }
            
            if(gasolinaDistanciaMejor>lista.get(i+1).distanciaGasolina){
                gasolinaDistanciaMejor = lista.get(i+1).distanciaGasolina;
                gasolinaDistanciaMejorTexto = lista.get(i+1).texto;
            }
            
            
            if(desgasteFisicoMejor>lista.get(i+1).desgaste){
                desgasteFisicoMejor = lista.get(i+1).desgaste;
                desgasteFisicoMejorTexto = lista.get(i+1).texto;
            }
            
            if(desgasteDistanciaMejor>lista.get(i+1).distanciaDesgaste){
                desgasteDistanciaMejor = lista.get(i+1).distanciaDesgaste;
                desgasteDistanciaMejorTexto = lista.get(i+1).texto;
            }
            
            //peores
            if(gasolinaPeor<lista.get(i+1).gasolina){
                gasolinaPeor = lista.get(i+1).gasolina;
                gasolinaPeorTexto = lista.get(i+1).texto;
            }
            
            if(distanciaPeor<lista.get(i+1).distancia){
                distanciaPeor = lista.get(i+1).distancia;
                distanciaPeorTexto = lista.get(i+1).texto;
                
            }
            
            if(gasolinaDistanciaPeor<lista.get(i+1).distanciaGasolina){
                gasolinaDistanciaPeor = lista.get(i+1).distanciaGasolina;
                gasolinaDistanciaPeorTexto = lista.get(i+1).texto;
            }
            
            
            if(desgasteFisicoPeor<lista.get(i+1).desgaste){
                desgasteFisicoPeor = lista.get(i+1).desgaste;
                desgasteFisicoPeorTexto = lista.get(i+1).texto;
            }
            
            if(desgasteDistanciaPeor<lista.get(i+1).distanciaDesgaste){
                desgasteDistanciaPeor = lista.get(i+1).distanciaDesgaste;
                desgasteDistanciaPeorTexto = lista.get(i+1).texto;
            }
        }
        
        
    }
    public void verificarPeorRuta(ArrayList<Ruta> lista){
        
        gasolinaPeor = lista.get(0).gasolina;
        distanciaPeor = lista.get(0).distancia;
        gasolinaDistanciaPeor = lista.get(0).distanciaGasolina;
        desgasteFisicoPeor = lista.get(0).desgaste;
        desgasteDistanciaPeor = lista.get(0).distanciaDesgaste;
        
        gasolinaPeorTexto = lista.get(0).texto;
        distanciaPeorTexto = lista.get(0).texto;
        gasolinaDistanciaPeorTexto = lista.get(0).texto;
        desgasteFisicoPeorTexto = lista.get(0).texto;
        desgasteDistanciaPeorTexto = lista.get(0).texto;
        
        for (int i = 0; i < lista.size()-1; i++) {
            if(lista.get(i).gasolina<lista.get(i+1).gasolina){
                gasolinaPeor = lista.get(i+1).gasolina;
                gasolinaPeorTexto = lista.get(i+1).texto;
            }
            
            if(lista.get(i).distancia<lista.get(i+1).distancia){
                distanciaPeor = lista.get(i+1).distancia;
                distanciaPeorTexto = lista.get(i+1).texto;
            }
            
            if(lista.get(i).distanciaGasolina<lista.get(i+1).distanciaGasolina){
                gasolinaDistanciaPeor = lista.get(i+1).distanciaGasolina;
                gasolinaDistanciaPeorTexto = lista.get(i+1).texto;
            }
            
            
            if(lista.get(i).desgaste<lista.get(i+1).desgaste){
                desgasteFisicoPeor = lista.get(i+1).desgaste;
                desgasteFisicoPeorTexto = lista.get(i+1).texto;
            }
            
            if(lista.get(i).distanciaDesgaste<lista.get(i+1).distanciaDesgaste){
                desgasteDistanciaPeor = lista.get(i+1).distanciaDesgaste;
                desgasteDistanciaPeorTexto = lista.get(i+1).texto;
            }
        }
        
    }
    
    
    
    
}
