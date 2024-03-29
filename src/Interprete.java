import java.util.HashSet;

public class Interprete {
    private String nombre;
    private float rating;
    private final ListaPeliculas filmografia;

    /**
     * Calcula y asigna el rating del int�rprete bas�ndose en el rating de sus pel�culas
     * Es de orden O(n), n="n�mero de pel�culas del int�rprete"
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

    /**
     * @return Interprete en el formato en el que aparece en el ejemplo
     * Es de orden O(n), n="n�mero de pel�culas del int�rprete"
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Nombre: %s%n", getNombre()));
        str.append(String.format("Rating: %.1f%n",getRating()));
        str.append(String.format("Total de pel�culas del int�rprete: %d%n", getNumPeliculas()));
        for (Pelicula pel: getFilmografia().getLista()) 
            str.append(pel.getTitulo()).append(System.lineSeparator());
        return str.toString();
    }

    /**
     * Elimina una pelicular de la lista de pel�culas del int�rprete.
     * @param pel la pel�cula a eliminar
     * @return true si el autor participaba en la pel�cula
     */
    public boolean eliminarPelicula(Pelicula pel) {
        boolean eliminada = getFilmografia().eliminarPelicula(pel);
        if (eliminada) calcularRating();
        return eliminada;
    }

    /**
     * Devuelve un HashSet con todos los adyacentes del int�rprete, es decir,
     * aquellos int�rpretes con los que ha participado en alguna pel�cula.
     * Orden temporal: O(n * m), con n="n�mero de pel�culas del int�rprete", m="n�mero de interpretes de cada pel�cula)
     * @return el HashSet con los int�rpretes que son adyacentes.
     */
    public HashSet<Interprete> obtenerAdyacentes() {
       HashSet<Interprete> hsAdyacentes = new HashSet<>();
       for (Pelicula pel:filmografia.getLista()) {
            for (Interprete inter:pel.getReparto().getLista()){
                if(!inter.equals(this)) hsAdyacentes.add(inter);
            }
       }
       return hsAdyacentes;
    }
}
