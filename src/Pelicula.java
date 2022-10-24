public class Pelicula {
    private String titulo;
    private double rating;
    private int numvotos;
    /**
     * A�ade un int�rprete a la pel�cula
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter) {}
    /**
     * A�ade un nuevo voto a la pel�cula.
     * POST: se han recalculado los ratings de sus int�rpretes
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
