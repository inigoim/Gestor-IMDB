import java.util.ArrayList;

public class ListaInterpretes {
    private ArrayList<Interprete> lista = new ArrayList<>();
    /**
     * Añade un intérprete a la lista. Es de orden O(1), el coste de añadir un elemento a un ArrayList
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter) {
        lista.add(inter);
    }
    /**
     * Busca un intérprete en la lista y lo devuelve.  Es de orden O(n)
     * @param nombre Nombre del intérprete a buscar
     * @return el intérprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        for (Interprete inter: lista)
            if (inter.getNombre().equals(nombre)) return inter;
        return null;
    }

    public ArrayList<Interprete> getLista() {
        return lista;
    }
}
