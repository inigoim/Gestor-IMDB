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
        if (this.esVacio()) {
            this.raiz = new NodoABBInterpretes(inter);
        }
        else{
            this.raiz.anadirInterprete(inter);
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
