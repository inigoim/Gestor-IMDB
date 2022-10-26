public class Interprete {
    private String nombre;
    private float rating;
    private final ListaPeliculas filmografia;

    /**
     * Calcula y asigna el rating del intérprete basándose en el rating de sus películas
     * Es de orden O(n).
     */

    public void calcularRating() {
        float sumRating = 0;
        int numvotos = 0;
        for (Pelicula pel : filmografia.getLista()) {
            sumRating += pel.getRating() * pel.getNumvotos();
            numvotos += pel.getNumvotos();
        }
        setRating(sumRating / numvotos);
    }

    /**
     * Añade una película al intérprete. Es de orden O(1)
     *
     * @param pel Película a añadir
     *            POST: El rating del intérprete NO se modifica en este momento
     */
    public void anadirPelicula(Pelicula pel) {
        filmografia.anadirPelicula(pel);
    }

    public Interprete(String nombre) {
        this.nombre = nombre;
        filmografia = new ListaPeliculas();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public ListaPeliculas getFilmografia() {
        return filmografia;
    }

    public String toString() {
        return getNombre();
    }
}
