import java.util.LinkedList;

public class ABBInterpretes implements InterfazInterpretes{
    NodoABBInterpretes raiz;

    //m�todos b�sicos
    public ABBInterpretes() {
        this.raiz = null;
    }
    public ABBInterpretes(Interprete info) {
        this.raiz = new NodoABBInterpretes();
    }
    public ABBInterpretes(NodoABBInterpretes raiz) {
        this.raiz = raiz;
    }
    public boolean esVacio() {
        return (raiz == null);
    }



    /**
     * A�ade un int�rprete a la lista
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter){
        if (esVacio()) {
            this.raiz = new NodoABBInterpretes(inter);
        }
        else{
            this.raiz.anadirInterprete(inter);
        }
    }
    /**
     * Busca un int�rprete en la lista y lo devuelve
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
        return null;
    }

    /**
     * Devuelve el n� de elementos del �rbol.
     * @return n� de elementos del �rbol
     */
    public int size(){
        if (esVacio()) return 0;
        else return raiz.size();
    }
}
