
package estructuras.arboles;

public class Arbol {
    Pagina raiz;
    int id;
    String texto = null;
    
    public Arbol(){
        raiz = null;
    }
    
    public void insertar(Nodo clave){
        if(raiz == null){
            raiz = new Pagina(null);
            raiz.insertarNodo(clave);
        }else{
            insertarBusqueda(raiz, clave);
        }
    }
    
    private void insertarBusqueda(Pagina actual, Nodo clave){
        if(actual.listaClaves.get(0).izquierda == null){
            actual.insertarNodo(clave);
            if(actual.getTamanio()==5){
                subirNodo(actual);
            }
        }else{
            for (int i = 0; i < actual.listaClaves.size(); i++) {
                if(clave.clave <actual.listaClaves.get(i).clave){
                    insertarBusqueda(actual.listaClaves.get(i).izquierda, clave);
                    break;
                }else if((i+1)== actual.listaClaves.size()){
                    insertarBusqueda(actual.listaClaves.get(i).derecha, clave);
                    break;
                }
            }
            
        }
        
    }
    
    private void subirNodo(Pagina actual){
        if(actual.padre == null){
            Pagina nuevaPagina = new Pagina(null);                      //Creamos la nueva pagina que sera la raiz
            Pagina nuevaPaginaDe = new Pagina(nuevaPagina);             //se crea una pagina que ira a la Derecha de la raiz
            Nodo nuevo = actual.listaClaves.get(2);                     //se obtiene el nodo de emedio de la pagina actual
            nuevo.izquierda = actual;                                   //se agrega el puntero de la derecha del nuevo nodo
            nuevo.derecha = nuevaPaginaDe;                              //se agrega el puntero de la Derecha del nuevo nodo
            Nodo nodoIz = actual.listaClaves.get(3);
            Nodo nodoDe = actual.listaClaves.get(4);
            if(nodoIz.izquierda!=null){
               nodoIz.izquierda.padre = nuevaPaginaDe; 
               nodoDe.izquierda.padre = nuevaPaginaDe; 
               nodoDe.derecha.padre = nuevaPaginaDe; 
            }
            nuevaPaginaDe.insertarNodo(nodoIz);   //se agregan las claves de para la nueva pagina de la derecha
            nuevaPaginaDe.insertarNodo(nodoDe);   
            
            
            actual.listaClaves.remove(2);                               //se borran las claves de la pagina de la izquierda
            actual.listaClaves.remove(2);
            actual.listaClaves.remove(2);
            nuevaPagina.insertarNodo(nuevo);                            //se agrega el nodo a la nueva pagina raiz
            actual.padre = nuevaPagina;                                 //se agrega el padre de la pagina de la izquierda
            nuevaPaginaDe.padre = nuevaPagina;
            raiz = nuevaPagina;                                         // hace la referencia de la nueva raiz
        }else{
            Pagina nuevaPaginaDe = new Pagina(actual.padre);             //se crea una pagina que ira a la derecha de la raiz
            Nodo nuevo = actual.listaClaves.get(2);                     //se obtiene el nodo de emedio de la pagina actual
            nuevo.izquierda = actual;                                   //se agrega el puntero de la derecha del nuevo nodo
            nuevo.derecha = nuevaPaginaDe;                              //se agrega el puntero de la izquierda del nuevo nodo
            Nodo nodoIz = actual.listaClaves.get(3);
            Nodo nodoDe = actual.listaClaves.get(4);
            if(nodoIz.izquierda!=null){
               nodoIz.izquierda.padre = nuevaPaginaDe; 
               nodoDe.izquierda.padre = nuevaPaginaDe; 
               nodoDe.derecha.padre = nuevaPaginaDe; 
            }
            nuevaPaginaDe.insertarNodo(nodoIz);   //se agregan las claves de para la nueva pagina de la derecha
            nuevaPaginaDe.insertarNodo(nodoDe);    
            
            actual.listaClaves.remove(2);                               //se borran las claves de la pagina de la izquierda
            actual.listaClaves.remove(2);
            actual.listaClaves.remove(2);
            //nuevaPaginaDe.padre = actual.padre;
//            System.out.println("clave:"+nuevo.clave);
//            System.out.println("Padre actual: "+actual.padre.listaClaves.get(0).clave);
            agregarNodoOrdenar(actual.padre, nuevo);                    //se agrega el nodo Ordenado
            
            if(actual.padre.listaClaves.size()==5){
                
                subirNodo(actual.padre);
            }
        }
    }
    
    private void agregarNodoOrdenar(Pagina padre, Nodo nuevo){
        //System.out.println("n");
        for (int i = 0; i < padre.getTamanio(); i++) {
            if(nuevo.clave<padre.listaClaves.get(i).clave && i==0){
                padre.listaClaves.get(i).izquierda = nuevo.derecha;
//                System.out.println("padre: :"+padre.listaClaves.get(i).clave);
//                System.out.println("clave:"+nuevo.clave);
//                System.out.println("izquierda: "+nuevo.izquierda.toString());
                
                break;
            }else if(nuevo.clave<padre.listaClaves.get(i).clave){
                padre.listaClaves.get(i).izquierda = nuevo.derecha;
                padre.listaClaves.get(i-1).derecha = nuevo.izquierda;
//                System.out.println("clave:"+nuevo.clave);
//                System.out.println("izquierda: "+nuevo.izquierda.toString());
//                System.out.println("derecha: "+nuevo.derecha.toString());
                break;
            }else if(nuevo.clave>padre.listaClaves.get(i).clave && (i+1)==padre.getTamanio()){
                padre.listaClaves.get(i).derecha = nuevo.izquierda;
//                System.out.println("padre: :"+padre.listaClaves.get(i).clave);
//                System.out.println("clave:"+nuevo.clave);
                
//                System.out.println("derecha: "+nuevo.derecha.toString());
                break;
            }
        }
        padre.insertarNodo(nuevo);
    }
    
    public String busqueda(){
        id=0;
        texto = "digraph G {\nnode [shape=record];\n";
        recorrer(raiz);
        generarPunteros(raiz);
        texto += "}";
        return texto;
    }
    
    
    public void recorrer(Pagina actual){
        
        if(actual!=null){
            if(actual.listaClaves.get(0).izquierda==null){
            
                texto += "node"+id+"[label = \"";
                actual.id = id;
            for (int i = 0; i < actual.getTamanio(); i++) {
                texto += "<f"+(i*2)+"> | ";
                texto += "<f"+(i*2+1)+"> "+actual.listaClaves.get(i).ruta.texto+" | ";
                if((i+1)==actual.getTamanio()){
                    //texto += "<f"+(i*2+2)+">"+actual.padre.listaClaves.get(0).clave+" \"]\n";
                    texto += "<f"+(i*2+2)+"> \"]\n";
                }
            }
            //id++;
            }else{
                    texto += "node"+id+"[label = \"";
                    actual.id = id;
                for (int i = 0; i < actual.getTamanio(); i++) {
                    texto += "<f"+(i*2)+"> | ";
                    texto += "<f"+(i*2+1)+"> "+actual.listaClaves.get(i).ruta.texto+" | ";
                    if((i+1)==actual.getTamanio()){
                        texto += "<f"+(i*2+2)+"> \"]\n";
                        //actual.listaClaves.get(i).izquierda.padre=actual;
                    }
                }
                for (int i = 0; i < actual.getTamanio(); i++) {
                    id++;
                    recorrer(actual.listaClaves.get(i).izquierda);
                    //id++;
                    if((i+1)==actual.getTamanio()){
                        id++;
                        recorrer(actual.listaClaves.get(i).derecha);
                        //id++;
                    }
                }  
            }
        }else{
            texto += "Vacio";
        }
    }
    
    public void generarPunteros(Pagina actual){
        if(actual!=null){
            if(actual.listaClaves.get(0).izquierda==null){
            
            }else{

                for (int i = 0; i < actual.getTamanio(); i++) {
                    texto += "node"+actual.id+":f"+(i*2)+" -> "+"node"+actual.listaClaves.get(i).izquierda.id+";\n";
                    System.out.println("clave: "+actual.listaClaves.get(i).ruta.texto+"    iz:"+actual.listaClaves.get(i).izquierda.id+"    de:"+actual.listaClaves.get(i).derecha.id);
                    if((i+1)==actual.getTamanio()){
                        texto += "node"+actual.id+":f"+(i*2+2)+" -> "+"node"+actual.listaClaves.get(i).derecha.id+";\n";
                        //texto += "<f"+(i*2+2)+"> \"]\n";
                    }
                }
                for (int i = 0; i < actual.getTamanio(); i++) {
                    id++;
                    generarPunteros(actual.listaClaves.get(i).izquierda);
                    //id++;
                    if((i+1)==actual.getTamanio()){
                        id++;
                        generarPunteros(actual.listaClaves.get(i).derecha);
                        //id++;
                    }
                }  
            }
        }
    }
    
    public void borrarArbol(){
        
        limpiar(raiz);
        raiz =null;
    }
    
    public void limpiar(Pagina actual){
        if(actual.listaClaves.get(0).izquierda==null){
            
            actual.listaClaves.clear();
        }else{
            
            for (int i = 0; i < actual.getTamanio(); i++) {
                
                recorrer(actual.listaClaves.get(i).izquierda);
                actual.listaClaves.get(i).izquierda.listaClaves.clear();
                actual.listaClaves.get(i).izquierda = null;
                if((i+1)==actual.getTamanio()){
                    id++;
                    recorrer(actual.listaClaves.get(i).derecha);
                    actual.listaClaves.get(i).derecha.listaClaves.clear();
                    actual.listaClaves.get(i).derecha= null;
                }
            } 
            actual.listaClaves.clear();
            
        }
        
        
    }
}
