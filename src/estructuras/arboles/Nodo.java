
package estructuras.arboles;

import objetos.Ruta;

public class Nodo {
    
    Pagina izquierda;
    Pagina derecha;
    int clave;
    Ruta ruta;
    
    public Nodo(int clave, Ruta ruta){
        this.clave = clave;
        this.ruta = ruta;
    }
}
