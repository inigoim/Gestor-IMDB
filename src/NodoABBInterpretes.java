public class NodoABBInterpretes{

    private Interprete info;
    private NodoABBInterpretes left;
    private NodoABBInterpretes right;


    //métodos básicos
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
    public Interprete getInfo() {return info;}

    static class Aux {
        private Interprete info;
        private NodoABBInterpretes nodo;
        public Interprete getInfo(){return info;}
        public NodoABBInterpretes getNodo() {return nodo;}
    }

    /**
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
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
     * Busca un intérprete en la lista y lo devuelve
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
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

    private Aux eliminarMin(){
        Aux res = new Aux();
        if(!this.tieneIzq()){
            res.info = this.info;
            res.nodo = this.right;
        }
        else{
            Aux resIzq = this.left.eliminarMin();
            this.left = resIzq.nodo;
            res.info = resIzq.getInfo();
            res.nodo = this;
        }
    return res;
    }

    /**
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Aux eliminarInterprete(String nombre){
        int comp = nombre.compareToIgnoreCase(info.getNombre());
        Aux res = new Aux();
        if(comp == 0)
        {
            res.info = info;
            if(!tieneIzq()){
                res.nodo = right;
                return res;
            }
            else if(!tieneDer()){
                res.nodo = left;
                return res;
            }
            else{
                Aux min = right.eliminarMin();
                right = min.getNodo();
                info = min.getInfo();
                res.nodo = this;
                return res;
            }
        }
        else if(comp<0)
        {
            if (tieneIzq()){
                res = left.eliminarInterprete(nombre);
                left = res.getNodo();
            }
            res.nodo = this;
            return res;
        }
        else
        {
            if(tieneDer()){
                res = right.eliminarInterprete(nombre);
                right = res.getNodo();
            }
            res.nodo = this;
            return res;
        }
    }

    /**
     * Devuelve el nº de elementos del árbol.
     * @return nº de elementos del árbol
     */
    public int size() {
        int cont = 1;
        if (tieneIzq())
            cont += left.size();
        if (tieneDer())
            cont += right.size();
        return cont;
    }

    public void imprimirABB (){
        System.out.println(info.toString());
        if(tieneDer())
            right.imprimirABB();
        if(tieneIzq())
            left.imprimirABB();
    }
}