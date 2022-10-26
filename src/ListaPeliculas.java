import java.util.ArrayList;

public class ListaPeliculas {
    // Lista interna
    private final ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * Añade una película a la lista. Es de orden O(1), el coste de añadir un elemento a un ArrayList
     *
     * @param pel Película a añadir
     */

    public void anadirPelicula(Pelicula pel) {
        lista.add(pel);
    }

    /**
     * Busca una película en la lista y la devuelve. Es de orden O(log(n)), se ha usado la busqueda dicotomica.
     *
     * @param titulo Título de la película a buscar
     * @return la Película (si está en la lista), null en caso contrario
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
     * override del método toString para imprimir las peliculas de lista.
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
