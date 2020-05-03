
package finalestructuradatos;

import estructuras.arboles.Arbol;
import estructuras.arboles.Nodo;
import java.io.IOException;
import ventanas.Inicio;

public class Main {

    
    public static void main(String[] args) throws IOException {
        //Inicio inicio = new Inicio();
        //inicio.setVisible(true);
        Arbol ar = new Arbol();
        ar.insertar(new Nodo(50));
        ar.insertar(new Nodo(23));
        ar.insertar(new Nodo(34));
        ar.insertar(new Nodo(23));
        ar.insertar(new Nodo(45));
        ar.insertar(new Nodo(78));
        ar.insertar(new Nodo(76));
        ar.insertar(new Nodo(65));
        ar.insertar(new Nodo(51));
        ar.insertar(new Nodo(22));
        ar.insertar(new Nodo(33));
        ar.insertar(new Nodo(24));
        ar.insertar(new Nodo(44));
        ar.insertar(new Nodo(79));
        ar.insertar(new Nodo(77));
        ar.insertar(new Nodo(62));
        ar.insertar(new Nodo(1));
        ar.insertar(new Nodo(25));
        ar.insertar(new Nodo(15));
        ar.insertar(new Nodo(2));
        ar.insertar(new Nodo(0));
        ar.insertar(new Nodo(10));
        ar.insertar(new Nodo(100));
        ar.insertar(new Nodo(102));
        ar.insertar(new Nodo(99));
        ar.insertar(new Nodo(90));
        ar.insertar(new Nodo(84));
        ar.insertar(new Nodo(6));
        ar.insertar(new Nodo(13));
        ar.insertar(new Nodo(17));
        ar.insertar(new Nodo(29));
        ar.insertar(new Nodo(80));
        ar.insertar(new Nodo(81));
        
        
        System.out.println(ar.busqueda());
//        System.console();
//        Runtime.getRuntime().exec("sudo su");
    }
    
}
