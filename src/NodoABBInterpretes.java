public class NodoABBInterpretes  implements InterfazInterpretes{

    Interprete info;
    NodoABBInterpretes izq;
    NodoABBInterpretes der;

    //métodos básicos
    public NodoABBInterpretes() {
    }
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    public boolean esHoja() {
        return (izq == null && der == null);
    }
    // Devuelve si el nodo tiene subárbol izquierdo
    public boolean tieneIzq() {
        return (izq != null);
    }
    // Devuelve si el nodo tiene subárbol derecho
    public boolean tieneDer() {
        return (der != null);
    }

    /**
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter){
        if (inter.getNombre().compareTo(this.info.getNombre())<0) {
            if (this.tieneIzq()) this.izq.anadirInterprete(inter);
            else this.izq = new NodoABBInterpretes(inter);
        }else {
            if (this.tieneDer()) this.der.anadirInterprete(inter);
            else this.der = new NodoABBInterpretes(inter);
        }
    }
    /**
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        return null;
    }

}
