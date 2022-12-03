public class Interprete {
    private String nombre;
    private float rating;
    private final ListaPeliculas filmografia;

    /**
     * Calcula y asigna el rating del int�rprete bas�ndose en el rating de sus pel�culas
     * Es de orden O(n).
     */

    public void calcularRating() {
        float sumRating = 0;
        int numvotos = 0;
        for (Pelicula pel : filmografia.getLista()) {
            if (pel.getRating() != -1) {
                sumRating += pel.getRating() * pel.getNumvotos();
                numvotos += pel.getNumvotos();
            }
        }
        if (numvotos > 0) setRating(sumRating / numvotos);
        else setRating(-1);

    }

    /**
     * A�ade una pel�cula al int�rprete. Es de orden O(1)
     *
     * @param pel Pel�cula a a�adir
     *            POST: El rating del int�rprete NO se modifica en este momento
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

    public int getNumPeliculas() {return getFilmografia().getLista().size();}

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Nombre: %s%n", getNombre()));
        str.append(String.format("Rating: %f%n",getRating()));
        str.append(String.format("Total de pel�culas del int�rprete: %d%n", getNumPeliculas()));
        for (Pelicula pel: getFilmografia().getLista()) 
            str.append(pel.getTitulo()).append(System.lineSeparator());
        return str.toString();
    }
}
