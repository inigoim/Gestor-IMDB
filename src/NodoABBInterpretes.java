public class NodoABBInterpretes implements InterfazInterpretes{

    private Interprete info;
    private NodoABBInterpretes left;
    private NodoABBInterpretes right;


    //métodos básicos
    public NodoABBInterpretes() {}
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    private boolean esHoja() {
        return (left == null && right == null);
    }
    // Devuelve si el nodo tiene subárbol izquierdo
    private boolean tieneIzq() {
        return (left != null);
    }
    // Devuelve si el nodo tiene subárbol derecho
    private boolean tieneDer() {
        return (right != null);
    }
    private Interprete getInfo() {return info;}


    /**
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter){
        if (inter.getNombre().compareTo(this.getInfo().getNombre())<0) {
            if (tieneIzq()) left.anadirInterprete(inter);
            else left = new NodoABBInterpretes(inter);
        }else {
            if (tieneDer()) right.anadirInterprete(inter);
            else{ this.right = new NodoABBInterpretes(inter);}
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
                return left.buscarInterprete(nombre);
            }
            else return null;
        }
        else {
            if(tieneDer()){
                return right.buscarInterprete(nombre);
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
            cont += left.size();
        if (tieneDer())
            cont += right.size();
        return cont;
    }
}
