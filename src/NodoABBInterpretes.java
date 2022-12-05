import java.util.LinkedList;

public class NodoABBInterpretes implements InterfazInterpretes{

    private Interprete info;
    private NodoABBInterpretes izq;
    private NodoABBInterpretes der;


    //m�todos b�sicos
    public NodoABBInterpretes() {}
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    private boolean esHoja() {
        return (izq == null && der == null);
    }
    // Devuelve si el nodo tiene sub�rbol izquierdo
    private boolean tieneIzq() {
        return (izq != null);
    }
    // Devuelve si el nodo tiene sub�rbol derecho
    private boolean tieneDer() {
        return (der != null);
    }
    private Interprete getInfo() {return info;}


    /**
     * A�ade un int�rprete a la lista
     * @param inter Int�rprete a a�adir
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
     * Busca un int�rprete en la lista y lo devuelve
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
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
     * Elimina un int�rprete del �rbol (puede seguir estando en las listas de
     * int�rpretes de las pel�culas)
     * @param nombre Nombre del int�rprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        return null;
    }

    /**
     * Devuelve el n� de elementos del �rbol.
     * @return n� de elementos del �rbol
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
