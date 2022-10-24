public class Pelicula {
    private String titulo;
    private double rating;
    private int numvotos;
    /**
     * Añade un intérprete a la película
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter) {}
    /**
     * Añade un nuevo voto a la película.
     * POST: se han recalculado los ratings de sus intérpretes
     * @param voto
     */
    public void anadirVoto(float voto) {}



    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumvotos() {
        return numvotos;
    }

    public void setNumvotos(int numvotos) {
        this.numvotos = numvotos;
    }
}
