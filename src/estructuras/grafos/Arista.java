
package estructuras.grafos;

import objetos.Registro;

public class Arista {
    public NodoG destino;
    public Registro registro;

    public Arista(NodoG destino, Registro registro) {
        this.destino = destino;
        this.registro = registro;
    }

    public NodoG getDestino() {
        return destino;
    }

    public void setDestino(NodoG destino) {
        this.destino = destino;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
    
    
    
    
}
