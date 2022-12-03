import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class CatalogoIMDB {
    private static CatalogoIMDB miCatalogo;
    ListaPeliculas peliculas = new ListaPeliculas();
    ListaInterpretes interpretes = new ListaInterpretes();


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
        int numPeliculas = 0;
        Path pth = Path.of(nomF);
        Charset windows1252 = Charset.forName("windows-1252");

        try (BufferedReader reader = Files.newBufferedReader(pth, windows1252)) {
            Pelicula pel;
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] pelDatos = linea.split("\t");
                pel = new Pelicula(pelDatos[0], Integer.parseInt(pelDatos[1]),
                        Float.parseFloat(pelDatos[2]), Integer.parseInt(pelDatos[3]));
                peliculas.anadirPelicula(pel);
                if (++numPeliculas % 100000 == 0)
                    System.out.printf("Cargadas %,d películas\r", numPeliculas);
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
            e.printStackTrace();
        }
        System.out.printf("En el catálogo hay %,d películas.%n", numPeliculas);

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
        int numInterpretes = 0;
        Path pth = Path.of(nomF);
        Charset windows1252 = Charset.forName("windows-1252");

        try (BufferedReader reader = Files.newBufferedReader(pth, windows1252)) {
            Interprete inter;
            String linea;
            while ((linea = reader.readLine()) != null) {

                String[] interDatos = linea.split("->");
                inter = new Interprete(interDatos[0]);
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
                if (++numInterpretes % 100000 == 0)
                    System.out.printf("Cargados %,d intérpretes\r", numInterpretes);
            }
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
        }
        System.out.printf("En el catálogo hay %,d intérpretes.%n", numInterpretes);
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
}
