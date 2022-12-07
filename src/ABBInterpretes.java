public class ABBInterpretes implements InterfazInterpretes{
    private NodoABBInterpretes raiz;
    private int size;

    //métodos básicos
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
     * Añade un intérprete a la lista. Orden O(n). n="altura del árbol"
     * @param inter Intérprete a añadir
     */
    public synchronized void anadirInterprete(Interprete inter){
        if (esVacio()) this.raiz = new NodoABBInterpretes(inter);
        else this.raiz.anadirInterprete(inter);
        size++;
    }
    /**
     * Busca un intérprete en la lista y lo devuelve. Orden O(n). n="altura del árbol"
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        if (esVacio()) return null;
        else return raiz.buscarInterprete(nombre);
    }

    /**
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        if(esVacio()) return null;
        Interprete res = raiz.eliminarInterprete(nombre).getInfo();
        if (res != null) size--;
        return res;
    }

    /**
     * Devuelve el nº de elementos del árbol. Coste O(1).
     * @return nº de elementos del árbol
     */
    public int size(){return size;}

    public void imprimirABB(){
        if (esVacio()) System.out.println("EL ARBOL ESTA VACIO");
        else raiz.imprimirABB();
    }
}
