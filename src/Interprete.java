public class Interprete {
    private String nombre;
    private double rating;
    private ListaPeliculas filmografia;
    /**
     * Calcula y asigna el rating del int�rprete en base al rating de sus pel�culas
     */
    public void calcularRating() {
        double sumRating = 0; int numvotos = 0;
        for (Pelicula pel: filmografia.getLista()) {
            sumRating += pel.getRating() * pel.getNumvotos();
            numvotos += pel.getNumvotos();
        }
        rating = sumRating / numvotos;
    }
    /**
     * A�ade una pel�cula al int�rprete
     * @param pel Pel�cula a a�adir
     * POST: El rating del int�rprete NO se modifica en este momento
     */
    public void anadirPelicula(Pelicula pel) {
        filmografia.anadirPelicula(pel);
    }



    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
