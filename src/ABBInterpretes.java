public class ABBInterpretes implements InterfazInterpretes{
    NodoABBInterpretes raiz;

    //métodos básicos
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
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
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
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre){
        return null;
    }
}
