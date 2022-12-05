import java.util.ArrayList;

public class ListaInterpretes implements InterfazInterpretes{
    private final ArrayList<Interprete> lista = new ArrayList<>();

    public ArrayList<Interprete> getLista() {
        return lista;
    }

    /**
     * A�ade un int�rprete a la lista. Es de orden O(1), el coste de a�adir un elemento a un ArrayList
     *
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter) {
        lista.add(inter);
    }

    /**
     * Busca un int�rprete en la lista y lo devuelve. Es de orden O(n)
     * n="n�mero de int�rpretes en la lista"
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        for (Interprete inter : lista)
            if (inter.getNombre().equalsIgnoreCase(nombre)) return inter;
        return null;
    }

    /**
     * Elimina un int�rprete del �rbol (puede seguir estando en las listas de
     * int�rpretes de las pel�culas)
     * @param nombre Nombre del int�rprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        Interprete elem = buscarInterprete(nombre);
        lista.remove(elem);
        return elem;
    }

    /**
     * Devuelve el n� de elementos del �rbol.
     * @return n� de elementos del �rbol
     */
    public int size(){return lista.size();}



    /**
     * Siguiendo el formato del fichero cast.txt, override del m�todo toString para imprimir los int�rpretes de lista
     * Es de orden O(n), n="n�mero de int�rpretes en la lista"
     */

    public String toString() {
        if (lista.size() == 0) return "";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < lista.size() - 1; i++) {
            str.append(lista.get(i).getNombre());
            str.append("; ");
        }
        str.append(lista.get(lista.size() - 1));
        return str.toString();
    }
}
