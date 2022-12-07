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
     * Busca una película en la lista y la devuelve. Es de orden O(log(n))
     * n="número de películas en la lista", se ha usado la búsqueda dicotómica.
     *
     * @param titulo Título de la película a buscar
     * @return la Película (si está en la lista), null en caso contrario
     */
    public Pelicula buscarPelicula(String titulo) {
        int izq, der, medio;
        izq = 0;
        der = lista.size() - 1;
        medio = (izq + der) / 2;
        while (izq < der) {
            if (titulo.equals(lista.get(medio).getTitulo())) return lista.get(medio);
            if (titulo.compareTo(lista.get(medio).getTitulo()) < 0)
                der = medio - 1;
            else izq = medio + 1;
            medio = (izq + der) / 2;
        }
        if (titulo.equals(lista.get(der).getTitulo())) return lista.get(der);
        else return null;
    }

    public ArrayList<Pelicula> getLista() {
        return lista;
    }

    /**
     * Elimina de la lista la película pasada como parámetro. Orden O(n), con n="número de películas en la lista"
     * @param pel: película a eliminar
     * @return true si se ha eliminado, false en caso contrario
     */
    public boolean eliminarPelicula(Pelicula pel){
        return lista.remove(pel);
    }

    /**
     * Siguiendo el formato del fichero cast.txt,
     * override del método toString para imprimir las películas de lista.
     * Es de orden O(n), con n="número de películas en la lista"
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
