import java.util.ArrayList;

public class ListaInterpretes implements InterfazInterpretes{
    private final ArrayList<Interprete> lista = new ArrayList<>();

    public ArrayList<Interprete> getLista() {
        return lista;
    }

    /**
     * Añade un intérprete a la lista. Es de orden O(1), el coste de añadir un elemento a un ArrayList
     *
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter) {
        lista.add(inter);
    }

    /**
     * Busca un intérprete en la lista y lo devuelve. Es de orden O(n)
     * n="número de intérpretes en la lista"
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        for (Interprete inter : lista)
            if (inter.getNombre().equalsIgnoreCase(nombre)) return inter;
        return null;
    }

    /**
     * Elimina un intérprete del árbol (puede seguir estando en las listas de
     * intérpretes de las películas)
     * @param nombre Nombre del intérprete a eliminar
     * @return el Interprete (si se ha eliminado), null en caso contrario
     */
    public Interprete eliminarInterprete(String nombre){
        Interprete elem = buscarInterprete(nombre);
        lista.remove(elem);
        return elem;
    }

    /**
     * Devuelve el nº de elementos del árbol.
     * @return nº de elementos del árbol
     */
    public int size(){return lista.size();}



    /**
     * Siguiendo el formato del fichero cast.txt, override del método toString para imprimir los intérpretes de lista
     * Es de orden O(n), n="número de intérpretes en la lista"
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
