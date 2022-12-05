public class NodoABBInterpretes implements InterfazInterpretes{

    private Interprete info;
    private NodoABBInterpretes izq;
    private NodoABBInterpretes der;

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
     * Busca un intérprete en la lista y lo devuelve
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
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
