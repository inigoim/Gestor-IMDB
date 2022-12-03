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
     * Carga las pel�culas del cat�logo desde el fichero indicado.
     * Es de orden O(n), con n="n�mero de pel�culas en el fichero"
     * @param nomF Nombre del fichero que contiene las pel�culas
     */
    public void cargarPeliculas(String nomF) {
        System.out.println("Cargando pel�culas...");
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
                    System.out.printf("Cargadas %,d pel�culas\r", numPeliculas);
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
            e.printStackTrace();
        }
        System.out.printf("En el cat�logo hay %,d pel�culas.%n", numPeliculas);

    }
    /**
     * Carga los int�rpretes del cat�logo desde el fichero indicado. Es de orden O(n*m*log(l))
     * n="n�mero de int�rpretes en el fichero", m="promedio de pel�culas en las que aparece cada int�rprete"
     * l="n�mero de pel�culas en el cat�logo"
     * POST: se han cargado los int�rpretes y se han calculado sus ratings
     * @param nomF Nombre del fichero que contiene los int�rpretes
     */
    public void cargarInterpretes(String nomF) {
        System.out.println("Cargando int�rpretes...");
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
                    System.out.printf("Cargados %,d int�rpretes\r", numInterpretes);
            }
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error en el formato del archivo");
        }
        System.out.printf("En el cat�logo hay %,d int�rpretes.%n", numInterpretes);
    }
    /**
     * Imprime por pantalla el n�mero de int�rpretes de una pel�cula y sus nombres. Es de orden O(log(n))
     * n="n�mero de pel�culas en el cat�logo"
     * @param titulo T�tulo de la pel�cula
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.println(pel);
        else
            System.out.println("Pel�cula no encontrada en la base de datos.");
    }
    /**
     * Imprime por pantalla el nombre del int�rprete, su rating y los t�tulos
     * de sus pel�culas. Es de orden O(n), n="n�mero de int�rpretes en el cat�logo"
     * @param nombre Nombre del int�rprete
     */
    public void imprimirInfoInterprete(String nombre) {

        Interprete inter = interpretes.buscarInterprete(nombre);
        if (inter != null)
            System.out.println(inter);
        else
            System.out.println("Int�rprete no encontrado en la base de datos.");
    }
    /**
     * A�ade un nuevo voto a una pel�cula
     * PRE: el valor del voto est� entre 0.0 y 10.0.
     * Es de orden O(log(n) + m*l) n="n�mero de pel�culas en el cat�logo",
     * m="n�mero de int�rpretes de la pel�cula", l="promedio de pel�culas de cada int�rprete"
     * @param titulo T�tulo de la pel�cula
     * @param voto Valor del voto
     */
    public void anadirVoto(String titulo, float voto) {
        Pelicula pelicula = peliculas.buscarPelicula(titulo);
        if (pelicula != null)
            pelicula.anadirVoto(voto);
    }
}
