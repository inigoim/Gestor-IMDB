import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatalogoIMDB {
    ListaPeliculas peliculas = new ListaPeliculas();
    ListaInterpretes interpretes = new ListaInterpretes();
    /**
     * Carga las películas del catálogo desde el fichero indicado
     * @param nomF Nombre del fichero que contiene las películas
     */
    public void cargarPeliculas(String nomF) {
        Path pth = Paths.get(nomF);
        try {
            /*Como tendremos un archivo muy grande, este método usará mucha memoria,
            pero también será muy rápido*/
            List<String> lineas = Files.readAllLines(pth);

            Pelicula pel;
            for (String linea : lineas) {
                String[] pelDatos = linea.split("\t");
                pel = new Pelicula(pelDatos[0], Integer.parseInt(pelDatos[2]),
                        Float.parseFloat(pelDatos[3]), Integer.parseInt(pelDatos[4]));
                peliculas.anadirPelicula(pel);
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
        }


    }
    /**
     * Carga los intérpretes del catálogo desde el fichero indicado
     * POST: se han cargado los intérpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los intérpretes
     */
    public void cargarInterpretes(String nomF) {}
    /**
     * Imprime por pantalla el nº de intérpretes de una película y sus nombres
     * @param titulo Título de la película
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.printf("%s -> (nª de interpretes = %s): %s%n", pel.getTitulo(), pel.getReparto().getLista().size(), pel.getReparto().toString());
        else
            System.out.println("Película no encontrada en la base de datos.");
    }
    /**
     * Imprime por pantalla el nombre del intérprete, su rating y los títulos
     * de sus películas.
     * @param nombre Nombre del intérprete
     */
    public void imprimirInfoInterprete(String nombre) {

        Interprete inter = interpretes.buscarInterprete(nombre);
        if (inter != null)
            System.out.printf("%s (Rating: %s) -> %s%n",inter.getNombre() ,inter.getRating(), inter.getFilmografia().toString());
        else
            System.out.println("Actor no encontrado en la base de datos.");
    }
    /**
     * Añade un nuevo voto a una película
     * PRE: el valor del voto está entre 0.0 y 10.0.
     * @param titulo Título de la película
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {

        if (voto < 0) voto = 0;
        else if (voto > 10) voto = 10;

        peliculas.buscarPelicula(titulo).anadirVoto(voto);
    }
}
