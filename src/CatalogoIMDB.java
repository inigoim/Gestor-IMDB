import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class CatalogoIMDB {
    private static CatalogoIMDB miCatalogo;
    ListaPeliculas peliculas = new ListaPeliculas();
    InterfazInterpretes interpretes;


    private CatalogoIMDB() {}
    public static CatalogoIMDB getInstance() {
        if (miCatalogo == null) miCatalogo = new CatalogoIMDB();
        return miCatalogo;
    }

    public int numPeliculas() {
        return peliculas.getLista().size();
    }

    public int numInterpretes() {
        return interpretes.size();
    }

    /**
     * Carga las pel�culas del cat�logo desde el fichero indicado.
     * Es de orden O(n), con n="n�mero de pel�culas en el fichero"
     * @param nomF Nombre del fichero que contiene las pel�culas
     */
    public void cargarPeliculas(String nomF) {
        System.out.println("Cargando pel�culas...");

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
        System.out.printf("En el cat�logo hay %,d pel�culas.%n", peliculas.getLista().size());

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

        Path pth = Path.of(nomF);
        Charset windows1252 = Charset.forName("windows-1252");

        try  {
            Stream<String> lineas = Files.readAllLines(pth, windows1252).parallelStream();
            lineas.forEach(this::cargarInterprete);
            System.out.printf("En el cat�logo hay %,d int�rpretes.%n", interpretes.size());
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo:");
            throw new RuntimeException(e);
        }

    }

    /**
     * Funci�n auxiliar que se usa en cargarInterpretes
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
     * Imprime por pantalla el n�mero de int�rpretes de una pel�cula y sus nombres. Es de orden O(log(n))
     * n="n�mero de pel�culas en el cat�logo"
     * @param titulo T�tulo de la pel�cula
     */
    public void imprimirInfoPelicula(String titulo) {

        Pelicula pel = peliculas.buscarPelicula(titulo);
        if (pel != null)
            System.out.println(pel);
        else
            System.out.printf("La pel�cula %s no se encuentra en el cat�logo.%n%n", titulo);
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
            System.out.printf("El int�rprete %s no est� en el cat�logo.%n%n", nombre);
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

    /**
     * Elimina del cat�logo la pel�cula cuyo t�tulo se pasa como par�metro.
     * Adem�s, elimina la pel�cula de la lista de pel�culas de cada uno de los
     * int�rpretes de dicha pel�cula.
     * Aquellos int�rpretes que se quedan sin pel�culas son eliminados del
     * cat�logo, y al resto se les actualiza el rating.
     * @param titulo: Nombre de la pel�cula a eliminar.
     * @return Pel�cula eliminada.
     */
    public Pelicula eliminarPelicula(String titulo){
        Pelicula peliculaEliminada = peliculas.eliminarPelicula(titulo);
        if (peliculaEliminada == null) return null;

        for (Interprete inter: peliculaEliminada.getReparto().getLista()) {
            inter.eliminarPelicula(peliculaEliminada);
            if (inter.getNumPeliculas() == 0)
                interpretes.eliminarInterprete(inter);
        }
        return peliculaEliminada;
    }


    /**
     * Inicializa el conjunto de int�rpretes del cat�logo con el conjunto de
     * int�rpretes que se le pasa como par�metro
     * @param interpretes: conjunto de int�rpretes
     */
    public void setInterpretes(InterfazInterpretes interpretes){
        this.interpretes = interpretes;
    }

    /**
     * Devuelve la distancia m�nima entre dos int�rpretes dados.
     * @param inter1: nombre del primer int�rprete
     * @param inter2: nombre del segundo int�rprete
     * @return distancia m�nima entre ambos int�rpretes. En caso de que no
     * est�n conectados, devuelve -1.
     */
    public int distancia(String inter1, String inter2){
        LinkedList<Interprete> camino = obtenerCamino(inter1,inter2);
        int distancia = -1;
        return distancia + camino.size();
    }


    /**
     * Imprime el camino m�s corto entre dos int�rpretes. Si no existe camino,
     * imprime un mensaje indicando este hecho.
     * @param inter1: nombre del primer int�rprete
     * @param inter2: nombre del segundo int�rprete
     */
    public void imprimirCamino(String inter1, String inter2) {
        LinkedList<Interprete> camino = obtenerCamino(inter1,inter2);
        for (Interprete inter : camino)
            imprimirInfoInterprete(inter.getNombre());
    }



    /*
    clase auxiliar de imprimir camino,
    que guarda un Linkedlist con el camino m�s corto hasta el nodo destino.
    */
    //TODO probar si el recorrido en profundidad es m�s r�pido
    //TODO tratar cuando el interprete no existe
    public LinkedList<Interprete> obtenerCamino(String inter1, String inter2){

        LinkedList<Interprete> resultado = new LinkedList<>();
        HashMap<Interprete,Interprete> visitados = new HashMap<>();
        Queue<Interprete> cola = new LinkedList<>();

        //Obtenemos los int�rpretes para poder usar buscarAdyacentes en caso de que existan
        Interprete iorigen = interpretes.buscarInterprete(inter1);
        Interprete idestino = interpretes.buscarInterprete(inter2);
        if (iorigen==null || idestino==null) return resultado;


        cola.add(iorigen);
        visitados.put(iorigen,null);
        boolean encontrado = false;
        while(!cola.isEmpty() && !encontrado){
            Interprete interActual = cola.remove();
            if( interActual.equals(idestino)){encontrado = true;}
            else {
                for (Interprete aux : interActual.obtenerAdyacentes()){
                    if(!visitados.containsKey(aux)){
                        cola.add(aux);
                        visitados.put(aux,interActual);
                    }
                }
            }
        }

        if (encontrado){
            Interprete actual = idestino;
            while(actual != null){
                resultado.addFirst(actual);
                actual = visitados.get(actual);
            }
        }
        return resultado;
    }


}
