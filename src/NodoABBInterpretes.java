public class NodoABBInterpretes implements InterfazInterpretes{

    private Interprete info;
    private NodoABBInterpretes izq;
    private NodoABBInterpretes der;

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
     * Busca un int�rprete en la lista y lo devuelve
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        if (nombre.equalsIgnoreCase(info.getNombre()))
            return info;
        else if(nombre.compareTo(info.getNombre())<0){
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

}
