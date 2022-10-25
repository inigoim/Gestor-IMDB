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
     * Carga las pel�culas del cat�logo desde el fichero indicado
     * @param nomF Nombre del fichero que contiene las pel�culas
     */
    public void cargarPeliculas(String nomF) {
        Path pth = Paths.get(nomF);
        try {
            /*Como tendremos un archivo muy grande, este m�todo usar� mucha memoria,
            pero tambi�n ser� muy r�pido*/
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
     * Carga los int�rpretes del cat�logo desde el fichero indicado
     * POST: se han cargado los int�rpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los int�rpretes
     */
    public void cargarInterpretes(String nomF) {}
    /**
     * Imprime por pantalla el n� de int�rpretes de una pel�cula y sus nombres
     * @param titulo T�tulo de la pel�cula
     */
    public void imprimirInfoPelicula(String titulo) {}
    /**
     * Imprime por pantalla el nombre del int�rprete, su rating y los t�tulos
     * de sus pel�culas.
     * @param nombre Nombre del int�rprete
     */
    public void imprimirInfoInterprete(String nombre) {}
    /**
     * A�ade un nuevo voto a una pel�cula
     * PRE: el valor del voto est� entre 0.0 y 10.0.
     * @param titulo T�tulo de la pel�cula
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {}
}
