import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CatalogoIMDB {
    private static CatalogoIMDB miCatalogo;
    ListaPeliculas peliculas = new ListaPeliculas();
     InterfazInterpretes interpretes = new ABBInterpretes();


    private CatalogoIMDB() {}
    public static CatalogoIMDB getInstance() {
        if (miCatalogo == null) miCatalogo = new CatalogoIMDB();
        return miCatalogo;
    }

    /**
     * Carga las películas del catálogo desde el fichero indicado.
     * Es de orden O(n), con n="número de películas en el fichero"
     * @param nomF Nombre del fichero que contiene las películas
     */
    public void cargarPeliculas(String nomF) {
        System.out.println("Cargando películas...");

        Path pth = Path.of(nomF);
        Charset windows1252 = Charset.forName("windows-1252");

        try (BufferedReader reader = Files.newBufferedReader(pth, windows1252)) {
            Pelicula pel;
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    String[] pelDatos = linea.split("\t");
                    pel = new Pelicula(pelDatos[0], Integer.parseInt(pelDatos[1]),
                            Float.parseFloat(pelDatos[2]), Integer.parseInt(pelDatos[3]));
                    peliculas.anadirPelicula(pel);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.printf("Error de formato en:%n\"%s\"%n", linea);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
            throw new RuntimeException(e);
        }
        System.out.printf("En el catálogo hay %,d películas.%n", peliculas.getLista().size());

    }
    /**
     * Carga los intérpretes del catálogo desde el fichero indicado. Es de orden O(n*m*log(l))
     * n="número de intérpretes en el fichero", m="promedio de películas en las que aparece cada intérprete"
     * l="número de películas en el catálogo"
     * POST: se han cargado los intérpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los intérpretes
     */
    public void cargarInterpretes(String nomF) {
        System.out.println("Cargando intérpretes...");

        Path pth = Path.of(nomF);
        Charset windows1252 = Charset.forName("windows-1252");

        try  {
            Stream<String> lineas = Files.readAllLines(pth, windows1252).parallelStream();
            lineas.forEach(this::cargarInterprete);
            System.out.printf("En el catálogo hay %,d intérpretes.%n", interpretes.size());
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo:");
            throw new RuntimeException(e);
        }

    }

    /**
     * Función auxiliar que se usa en cargarInterpretes
     */
    private void cargarInterprete(String linea) {
        try {
            String[] interDatos = linea.split("->");
            Interprete inter = new Interprete(interDatos[0]);
            String[] pels = interDatos[1].split("\\Q||\\E"); //Hay que poner eso para escapar los caracteres
            Pelicula pel;
            for (String pelTitulo : pels) {
                pel = peliculas.buscarPelicula(pelTitulo);
                if (pel != null) {
                    inter.anadirPelicula(pel);
                    pel.anadirInterprete(inter);
                }
            }
            inter.calcularRating();
            interpretes.anadirInterprete(inter);
        }
        catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.printf("Error de formato en:%n\"%s\"%n", linea);
            System.out.println(e.getMessage());
        }
    }


    /**
     * Imprime por pantalla el número de intérpretes de una película y sus nombres. Es de orden O(log(n))
     * n="número de películas en el catálogo"
     * @param titulo Título de la película
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.println(pel);
        else
            System.out.println("Película no encontrada en la base de datos.");
    }
    /**
     * Imprime por pantalla el nombre del intérprete, su rating y los títulos
     * de sus películas. Es de orden O(n), n="número de intérpretes en el catálogo"
     * @param nombre Nombre del intérprete
     */
    public void imprimirInfoInterprete(String nombre) {

        Interprete inter = interpretes.buscarInterprete(nombre);
        if (inter != null)
            System.out.println(inter);
        else
            System.out.println("Intérprete no encontrado en la base de datos.");
    }
    /**
     * Añade un nuevo voto a una película
     * PRE: el valor del voto está entre 0.0 y 10.0.
     * Es de orden O(log(n) + m*l) n="número de películas en el catálogo",
     * m="número de intérpretes de la película", l="promedio de películas de cada intérprete"
     * @param titulo Título de la película
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {
        Pelicula pelicula = peliculas.buscarPelicula(titulo);
        if (pelicula != null)
            pelicula.anadirVoto(voto);
    }

    /**
     * Elimina del catálogo la película cuyo título se pasa como parámetro.
     * Además, elimina la película de la lista de películas de cada uno de los
     * intérpretes de dicha película.
     * Aquellos intérpretes que se quedan sin películas son eliminados del
     * catálogo, y al resto se les actualiza el rating.
     * @param titulo: Nombre de la película a eliminar.
     * @return Película eliminada.
     */
    public Pelicula eliminarPelicula(String titulo){
        Pelicula peli = peliculas.buscarPelicula(titulo);
        if (peli == null){
            return null;
        }
        peliculas.eliminarPelicula(peli);
        return peli;
    }


    /**
     * Inicializa el conjunto de intérpretes del catálogo con el conjunto de
     * intérpretes que se le pasa como parámetro
     * @param interpretes: conjunto de intérpretes
     */
    public void setInterpretes(InterfazInterpretes interpretes){

    }
}
