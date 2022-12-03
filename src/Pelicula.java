public class Pelicula {
    private String titulo;
    private int ano;
    private float rating;
    private int numvotos;

    private final ListaInterpretes reparto = new ListaInterpretes();

    public Pelicula(String titulo, int ano, float rating, int votos) {
        this.titulo = titulo;
        this.ano = ano;
        this.rating = rating;
        this.numvotos = votos;
    }

    /**
     * A�ade un int�rprete a la pel�cula. Es de orden O(1)
     *
     * @param inter Int�rprete a a�adir
     */
    public void anadirInterprete(Interprete inter) {
        reparto.anadirInterprete(inter);
    }

    /**
     * A�ade un nuevo voto a la pel�cula. Es de orden O(n*m)
     * POST: se han recalculado los ratings de sus int�rpretes
     *
     * @param voto Voto entre 0.0 y 10.0
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ListaInterpretes getReparto() {
        return reparto;
    }

    public int getNumInterpretes() {return getReparto().getLista().size();}

    /**
     * Devuelve toda la informaci�n de la pel�cula
     * @return m�ltiples lineas con informaci�n como el titulo, a�o, rating...
     * Es de orden O(N) (N = numero de interpretes de la pel�cula)
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("T�tulo: %s%n", getTitulo()));
        str.append(String.format("A�o: %d%n", getAno()));
        str.append(String.format("Rating: %.1f%n", getRating()));
        str.append(String.format("Num. votos: %,d%n", getNumvotos()));
        str.append(String.format("Total de int�rpretes de la pel�cula: %,d%n", getNumInterpretes()));
        for (Interprete inter: getReparto().getLista())
            str.append(inter.getNombre()).append(System.lineSeparator());
        return str.toString();
    }
}
