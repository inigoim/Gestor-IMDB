import java.util.LinkedList;

public class NodoABBInterpretes implements InterfazInterpretes{

    private Interprete info;
    private NodoABBInterpretes izq;
    private NodoABBInterpretes der;


    //métodos básicos
    public NodoABBInterpretes() {}
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    private boolean esHoja() {
        return (izq == null && der == null);
    }
    // Devuelve si el nodo tiene subárbol izquierdo
    private boolean tieneIzq() {
        return (izq != null);
    }
    // Devuelve si el nodo tiene subárbol derecho
    private boolean tieneDer() {
        return (der != null);
    }
    private Interprete getInfo() {return info;}


    /**
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter){
        if (inter.getNombre().compareTo(this.getInfo().getNombre())<0) {
            if (tieneIzq()) izq.anadirInterprete(inter);
            else izq = new NodoABBInterpretes(inter);
        }else {
            if (tieneDer()) der.anadirInterprete(inter);
            else{ this.der = new NodoABBInterpretes(inter);};
        }
    }
    /**
     * Busca un intérprete en la lista y lo devuelve
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        if (nombre.equalsIgnoreCase(getInfo().getNombre()))
            return getInfo();
        else if(nombre.compareTo(getInfo().getNombre())<0){
            if(tieneIzq()){
                return izq.buscarInterprete(nombre);
            }
            else return null;
        }
        else {
            if(tieneDer()){
                return der.buscarInterprete(nombre);
            }
            else return null;
        }
    }
    /**
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        return null;
    }

    /**
     * Devuelve el nº de elementos del árbol.
     * @return nº de elementos del árbol
     */
    public int size(){
        int cont = 0;
        cont ++;
        if (tieneIzq())
            cont += izq.size();
        if (tieneDer())
            cont += der.size();
        return cont;
    }
}
