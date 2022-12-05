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
}
