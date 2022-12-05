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
        if (esVacio()) {
            this.raiz = new NodoABBInterpretes(inter);
        }
        else{
            this.raiz.anadirInterprete(inter);
        }
    }
    /**
     * Busca un intérprete en la lista y lo devuelve
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        if (esVacio()) return null;
        else return raiz.buscarInterprete(nombre);
    }
}
