public class Pelicula {
    private String titulo;
    private float rating;
    private int numvotos;

    private ListaInterpretes reparto;
    /**
     * Añade un intérprete a la película. Es de orden O(1)
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter)
    {
        reparto.anadirInterprete(inter);
    }

    /**
     * Añade un nuevo voto a la película. Es de orden O(n*m)
     * POST: se han recalculado los ratings de sus intérpretes
     * @param voto
     */
    public void anadirVoto(float voto) {
        setRating((getRating() * getNumvotos() + voto) / (getNumvotos() + 1));
        setNumvotos(getNumvotos() + 1);
        for (Interprete i : reparto.getLista()) {
            i.calcularRating();
        }
    }



    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getRating() {
        return rating;
    }

    private void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumvotos() {
        return numvotos;
    }

    private void setNumvotos(int numvotos) {
        this.numvotos = numvotos;
    }
}
