import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
     * Carga las pel�culas del cat�logo desde el fichero indicado. Es de orden O(n)
     * @param nomF Nombre del fichero que contiene las pel�culas
     */
    public void cargarPeliculas(String nomF) {
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
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
            e.printStackTrace();
        }
    }
    /**
     * Carga los int�rpretes del cat�logo desde el fichero indicado. Es de orden O(n*m*log(l))
     * POST: se han cargado los int�rpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los int�rpretes
     */
    public void cargarInterpretes(String nomF) {
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
            }
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
        }
    }
    /**
     * Imprime por pantalla el n� de int�rpretes de una pel�cula y sus nombres. Es de orden O(log(n))
     * @param titulo T�tulo de la pel�cula
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.printf("%s %n%t%t %d interpretes: %s%n",
                    pel.getTitulo(), pel.getReparto().getLista().size(), pel.getReparto().toString());
        else
            System.out.println("Pel�cula no encontrada en la base de datos.");
    }
    /**
     * Imprime por pantalla el nombre del int�rprete, su rating y los t�tulos
     * de sus pel�culas. Es de orden O(n)
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
     * Es de orden O(log(n)*m*l)
     * @param titulo T�tulo de la pel�cula
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {
        peliculas.buscarPelicula(titulo).anadirVoto(voto);
    }
}
