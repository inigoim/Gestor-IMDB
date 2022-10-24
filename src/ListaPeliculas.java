import java.util.ArrayList;

public class ListaPeliculas {
    // Lista interna
    private ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * A�ade una pel�cula a la lista
     * @param pel Pel�cula a a�adir
     */
    public void anadirPelicula(Pelicula pel) {
        lista.add(pel);
    }
    /**
     * Busca una pel�cula en la lista y la devuelve
     * @param titulo T�tulo de la pel�cula a buscar
     * @return la Pel�cula (si est� en la lista), null en caso contrario
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
