public class NodoABBInterpretes{

    private Interprete info;
    private NodoABBInterpretes left;
    private NodoABBInterpretes right;


    //m�todos b�sicos
    public NodoABBInterpretes(Interprete info) {
        this.info = info;
    }
    // Devuelve si el nodo es hoja
    private boolean esHoja() {
        return (left == null && right == null);
    }
    // Devuelve si el nodo tiene sub�rbol izquierdo
    private boolean tieneIzq() {
        return (left != null);
    }
    // Devuelve si el nodo tiene sub�rbol derecho
    private boolean tieneDer() {
        return (right != null);
    }
    public Interprete getInfo() {return info;}


    /**
     * A�ade un int�rprete a la lista. Orden O(log(n)). n="altura del �rbol"
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter){
        if (inter.getNombre().compareToIgnoreCase(this.info.getNombre())<0) {
            if (tieneIzq()) left.anadirInterprete(inter);
            else left = new NodoABBInterpretes(inter);
        }else {
            if (tieneDer()) right.anadirInterprete(inter);
            else{ this.right = new NodoABBInterpretes(inter);}
        }
    }
    /**
     * Busca un int�rprete en la lista y lo devuelve. Orden O(n). n="altura del �rbol".
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        if (nombre.equalsIgnoreCase(info.getNombre()))
            return info;
        else if(nombre.compareToIgnoreCase(info.getNombre())<0){
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

    // Variables usadas por eliminarMin y eliminarInterprete
    private static Interprete infoAux1;
    private static Interprete infoAux2;
    private static NodoABBInterpretes nodoAux;

    /**
     * Elimina el m�nimo del sub�rbol. Coste O(n). n="altura del �rbol".
     */
    private void eliminarMin(){
        if(tieneIzq()){
            left.eliminarMin();
            left = nodoAux;
            nodoAux = this;
        }
        else{
            infoAux1 = info;
            nodoAux = right;
        }
    }

    /**
     * Elimina un int�rprete del sub�rbol. Coste O(n). n="altura del �rbol".
     * @param nombre Nombre del int�rprete a eliminar
     * @return Objeto con el int�rprete (si se ha eliminado), y el nodo eliminado.
     */
    public Interprete eliminarInterprete(String nombre){
        int comp = nombre.compareToIgnoreCase(info.getNombre());
        infoAux2 = null;

        if(comp < 0) {
            if (tieneIzq()) {
                left.eliminarInterprete(nombre);
                left = nodoAux;
            }
            nodoAux = this;
            }
        else if (comp > 0) {
            if(tieneDer()){
                right.eliminarInterprete(nombre);
                right = nodoAux;
            }
            nodoAux = this;
        }
        else { // comp == 0
            infoAux2 = info;
            if(!tieneIzq()) nodoAux = right;
            else if(!tieneDer())nodoAux = left;
            else{
                right.eliminarMin();
                right = nodoAux;
                info = infoAux1;
                nodoAux = this;
            }
        }
        return infoAux2;
    }
    
    public void imprimirABB (){
        System.out.println(info.toString());
        if(tieneDer())
            right.imprimirABB();
        if(tieneIzq())
            left.imprimirABB();
    }
}