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
    //public ArrayList<NodoG> listaGrafosCaminos;
    public ArrayList<Camino> listaCaminos;
    public double gasolina,distancia, desgaste, tiempo,distanciaDesgaste,distanciaGasolina;
    public String texto;
    
    
    public Ruta(ArrayList<Camino> listaCaminos){
        this.listaCaminos = listaCaminos;
        distancia =0;
    }
    
//    public Ruta(ArrayList<NodoG> listaGrafosCaminos){
//        this.listaGrafosCaminos = listaGrafosCaminos;
//        distancia =0;
//    }
    public void resetear(){
        gasolina = 0;
        distancia = 0;
        desgaste = 0;
        tiempo = 0;
        distanciaDesgaste = 0;
        distanciaGasolina = 0;
    }
    public void calcularDatos(){
        resetear();
        if(listaCaminos.size()>0){
            texto = listaCaminos.get(0).getNodo().idNodo;
            for (int i = 0; i < (listaCaminos.size()-1); i++) {
                texto += " = "+listaCaminos.get(i+1).getNodo().idNodo;
                Arista dato = listaCaminos.get(i).getNodo().listaAristas.get(listaCaminos.get(i+1).id);
                if(dato!=null){
                    gasolina += dato.registro.getConsumoGas();
                    desgaste += dato.registro.getDesgastePersona();
                    distancia += dato.registro.getDistancia();
                    distanciaGasolina += dato.registro.getDistancia()*dato.registro.getConsumoGas()*dato.registro.getTiempoVehiculo();
                    distanciaDesgaste += dato.registro.getDistancia()*dato.registro.getDesgastePersona()*dato.registro.getTiempoPie();
                }else{
                    gasolina += 0;
                    desgaste += 0;
                    distancia +=0;
                    distanciaGasolina = 0;
                    distanciaDesgaste = 0;
                }
            }
        }
        
    }
    public void calcularDatosCaminando(){
        resetear();
        if(listaCaminos.size()>0){
            texto = listaCaminos.get(0).getNodo().idNodo;
            for (int i = 0; i < (listaCaminos.size()-1); i++) {
                texto += " = "+listaCaminos.get(i+1).getNodo().idNodo;
                Arista dato = listaCaminos.get(i).getNodo().listaAristasNoDirigida.get(listaCaminos.get(i+1).id);
                if(dato!=null){
                    gasolina += dato.registro.getConsumoGas();
                    desgaste += dato.registro.getDesgastePersona();
                    distancia += dato.registro.getDistancia();
                    distanciaGasolina += dato.registro.getDistancia()*dato.registro.getConsumoGas()*dato.registro.getTiempoVehiculo();
                    distanciaDesgaste += dato.registro.getDistancia()*dato.registro.getDesgastePersona()*dato.registro.getTiempoPie();
                }else{
                    gasolina += 0;
                    desgaste += 0;
                    distancia +=0;
                    distanciaGasolina = 0;
                    distanciaDesgaste = 0;
                }
            }
        }
        
    }
    
   
    
//    public void calcularDatos(){
//        NodoG buscar = null;
//        texto = listaCaminos.get(0).getNodo().idNodo;
//        distancia = 0;
//        for (int i = 0; i < (listaCaminos.size()-1); i++) {
//            
//                //buscar = listaGrafosCaminos.get(i+1);
//            texto += " = "+listaCaminos.get(i+1).getNodo().idNodo;
//            //Arista dato = buscarNodo(buscar,listaGrafosCaminos.get(i).listaAristas);
//            Arista dato = listaCaminos.get(i).getNodo().listaAristas.get(listaCaminos.get(i+1).id);
//            if(dato!=null){
//                System.out.println("->"+dato.destino.idNodo+" distancia: "+dato.registro.getDistancia());
//                distancia += dato.registro.getDistancia();
//            }else{
//                distancia +=0;
//            }
//            
//        }
//        System.out.println("===============");
//    }
    
    public Arista buscarNodo(NodoG nodo, ArrayList<Arista> lista){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).destino.equals(nodo)){
                return lista.get(i);
            }
        }
        return null;
    }
    
    
    
}
