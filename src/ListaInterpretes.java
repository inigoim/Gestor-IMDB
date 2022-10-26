import java.util.ArrayList;

public class ListaInterpretes {
    private final ArrayList<Interprete> lista = new ArrayList<>();
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
            if (inter.getNombre().equalsIgnoreCase(nombre)) return inter;
        return null;
    }

    public ArrayList<Interprete> getLista() {
        return lista;
    }

    // Siguiendo el formato del fichero cast.txt, override del m�todo toString para imprimir los interpretes de lista
    public String toString() {
        if (lista.size() == 0) return "";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < lista.size() - 1; i++) {
            str.append(lista.get(i)); str.append("; ");
        }
        str.append(lista.get(lista.size()-1));
        return str.toString();
    }
}
