public class ABBInterpretes implements InterfazInterpretes{
    private NodoABBInterpretes raiz;
    private int size;

    //m�todos b�sicos
    public ABBInterpretes() {
        this.raiz = null;
    }
    public ABBInterpretes(Interprete info) {
        this.raiz = new NodoABBInterpretes(info);
    }

    public boolean esVacio() {
        return (raiz == null);
    }



    /**
     * A�ade un int�rprete a la lista. Orden O(n). n="altura del �rbol"
     * @param inter Int�rprete a a�adir
     */
    public synchronized void anadirInterprete(Interprete inter){
        if (esVacio()) this.raiz = new NodoABBInterpretes(inter);
        else this.raiz.anadirInterprete(inter);
        size++;
    }
    /**
     * Busca un int�rprete en la lista y lo devuelve. Orden O(n). n="altura del �rbol"
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        if (esVacio()) return null;
        else return raiz.buscarInterprete(nombre);
    }

    /**
     * Elimina un int�rprete del �rbol (puede seguir estando en las listas de
     * int�rpretes de las pel�culas)
     * @param nombre Nombre del int�rprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        if(esVacio()) return null;
        Interprete res = raiz.eliminarInterprete(nombre).getInfo();
        if (res != null) size--;
        return res;
    }

    /**
     * Devuelve el n� de elementos del �rbol. Coste O(1).
     * @return n� de elementos del �rbol
     */
    public int size(){return size;}

    public void imprimirABB(){
        if (esVacio()) System.out.println("EL ARBOL ESTA VACIO");
        else raiz.imprimirABB();
    }
}
