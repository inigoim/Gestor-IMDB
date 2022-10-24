import java.util.ArrayList;

public class ListaInterpretes {
    private ArrayList<Interprete> lista = new ArrayList<>();
    /**
     * A�ade un int�rprete a la lista. Es de orden O(1), el coste de a�adir un elemento a un ArrayList
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter) {
        lista.add(inter);
    }
    /**
     * Busca un int�rprete en la lista y lo devuelve.  Es de orden O(n)
     * @param nombre Nombre del int�rprete a buscar
     * @return el int�rprete (si est� en la lista), null en caso contrario
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
