import java.util.ArrayList;

public class ListaPeliculas {
    // Lista interna
    private ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * Añade una película a la lista
     * @param pel Película a añadir
     */
    public void anadirPelicula(Pelicula pel) {
        lista.add(pel);
    }
    /**
     * Busca una película en la lista y la devuelve
     * @param titulo Título de la película a buscar
     * @return la Película (si está en la lista), null en caso contrario
     */
    public Pelicula buscarPelicula(String titulo) {
        for (Pelicula pel: lista)
            if (pel.getTitulo().equals(titulo)) return pel;
        return null;
    }

    public ArrayList<Pelicula> getLista() {
        return lista;
    }
}
