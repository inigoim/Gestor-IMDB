import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatalogoIMDB {
    private CatalogoIMDB miCatalogo;
    ListaPeliculas peliculas = new ListaPeliculas();
    ListaInterpretes interpretes = new ListaInterpretes();


    private CatalogoIMDB() {}
    public CatalogoIMDB getInstance() {
        if (miCatalogo == null) miCatalogo = new CatalogoIMDB();
        return miCatalogo;
    }

    /**
     * Carga las pel�culas del cat�logo desde el fichero indicado. Es de orden O(n)
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
     * Carga los int�rpretes del cat�logo desde el fichero indicado. Es de orden O(n*m)
     * POST: se han cargado los int�rpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los int�rpretes
     */
    public void cargarInterpretes(String nomF) {
        Path pth = Paths.get(nomF);
        try {
            /*Como tendremos un archivo muy grande, este m�todo usar� mucha memoria,
            pero tambi�n ser� muy r�pido*/
            List<String> lineas = Files.readAllLines(pth);

            Interprete inter;
            for (String linea : lineas) {
                String[] interDatos = linea.split("->");
                inter = new Interprete(interDatos[0]);

                String[] pels = interDatos[1].split("\\Q||\\E"); //Hay que poner eso para escapar los caracteres
                Pelicula pel;
                for (String pelTitulo : pels) {
                    pel = peliculas.buscarPelicula(pelTitulo);
                    if (pel != null) inter.anadirPelicula(pel);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
        }
    }
    /**
     * Imprime por pantalla el n� de int�rpretes de una pel�cula y sus nombres. Es de orden O(n)
     * @param titulo T�tulo de la pel�cula
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.printf("%s -> (n� de interpretes = %s): %s%n",
                    pel.getTitulo(), pel.getReparto().getLista().size(), pel.getReparto().toString());
        else
            System.out.println("Pel�cula no encontrada en la base de datos.");
    }
    /**
     * Imprime por pantalla el nombre del int�rprete, su rating y los t�tulos. Es de orden O(n)
     * de sus pel�culas.
     * @param nombre Nombre del int�rprete
     */
    public void imprimirInfoInterprete(String nombre) {

        Interprete inter = interpretes.buscarInterprete(nombre);
        if (inter != null)
            System.out.printf("%s (Rating: %s) -> %s%n",
                    inter.getNombre() ,inter.getRating(), inter.getFilmografia().toString());
        else
            System.out.println("Actor no encontrado en la base de datos.");
    }
    /**
     * A�ade un nuevo voto a una pel�cula
     * PRE: el valor del voto est� entre 0.0 y 10.0.
     * @param titulo T�tulo de la pel�cula
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {
        peliculas.buscarPelicula(titulo).anadirVoto(voto);
    }
}
