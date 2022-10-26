import java.util.ArrayList;

public class ListaPeliculas {
    // Lista interna
    private final ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * A�ade una pel�cula a la lista. Es de orden O(1), el coste de a�adir un elemento a un ArrayList
     *
     * @param pel Pel�cula a a�adir
     */

    public void anadirPelicula(Pelicula pel) {
        lista.add(pel);
    }

    /**
     * Busca una pel�cula en la lista y la devuelve. Es de orden O(log(n)), se ha usado la busqueda dicotomica.
     *
     * @param titulo T�tulo de la pel�cula a buscar
     * @return la Pel�cula (si est� en la lista), null en caso contrario
     */
    public Pelicula buscarPelicula(String titulo) {
        int lower = 0;
        int upper = lista.size() - 1;
        int pos;
        String tituloActual;

        while (lower <= upper) {
            pos = (upper + lower) / 2;
            tituloActual = lista.get(pos).getTitulo();

            if (titulo.compareTo(tituloActual) < 0) upper = pos - 1;
            else if (titulo.compareTo(tituloActual) > 0) lower = pos + 1;
            else return lista.get(pos);
        }
        return null;
    }

    public ArrayList<Pelicula> getLista() {
        return lista;
    }

    /**
     * Siguiendo el formato del fichero cast.txt,
     * override del m�todo toString para imprimir las peliculas de lista.
     * Es de orden O(n)
     */

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < lista.size() - 1; i++) {
            str.append(lista.get(i).getTitulo());
            str.append("||");
        }
        str.append(lista.get(lista.size() - 1).getTitulo());
        return str.toString();
    }
}
