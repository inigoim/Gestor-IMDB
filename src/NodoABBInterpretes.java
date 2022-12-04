public class NodoABBInterpretes  implements InterfazInterpretes{

    Interprete info;
    NodoABBInterpretes izq;
    NodoABBInterpretes der;

    //m�todos b�sicos
    public NodoABBInterpretes() {
    }
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    public boolean esHoja() {
        return (izq == null && der == null);
    }
    // Devuelve si el nodo tiene sub�rbol izquierdo
    public boolean tieneIzq() {
        return (izq != null);
    }
    // Devuelve si el nodo tiene sub�rbol derecho
    public boolean tieneDer() {
        return (der != null);
    }

    /**
     * A�ade un int�rprete a la lista
     * @param inter Int�rprete a a�adir
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
     * Elimina un int�rprete del �rbol (puede seguir estando en las listas de
     * int�rpretes de las pel�culas)
     * @param nombre Nombre del int�rprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        return null;
    }

}
