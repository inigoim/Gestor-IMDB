import java.util.ArrayList;

public class ListaPeliculas {
    // Lista interna
    private final ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * A�ade una pel�cula a la lista. Es de orden O(1), el coste de a�adir un elemento a un ArrayList
     *
     * @param pel Pel�cula a a�adir
     */

    public void anadirPelicula(Pelicula pel) {
        lista.add(pel);
    }

    /**
     * Busca una pel�cula en la lista y la devuelve. Es de orden O(log(n))
     * n="n�mero de pel�culas en la lista", se ha usado la b�squeda dicot�mica.
     *
     * @param titulo T�tulo de la pel�cula a buscar
     * @return la Pel�cula (si est� en la lista), null en caso contrario
     */
    public Pelicula buscarPelicula(String titulo) {
        int izq, der, medio;
        izq = 0;
        der = lista.size() - 1;
        medio = (izq + der) / 2;
        while (izq < der) {
            if (titulo.equals(lista.get(medio).getTitulo())) return lista.get(medio);
            if (titulo.compareTo(lista.get(medio).getTitulo()) < 0)
                der = medio - 1;
            else izq = medio + 1;
            medio = (izq + der) / 2;
        }
        if (titulo.equals(lista.get(der).getTitulo())) return lista.get(der);
        else return null;
    }

    public ArrayList<Pelicula> getLista() {
        return lista;
    }

    /**
     * Elimina de la lista la pel�cula pasada como par�metro. Orden O(n), con n="n�mero de pel�culas en la lista"
     * @param pel: pel�cula a eliminar
     * @return true si se ha eliminado, false en caso contrario
     */
    public boolean eliminarPelicula(Pelicula pel){
        return lista.remove(pel);
    }

    /**
     * Siguiendo el formato del fichero cast.txt,
     * override del m�todo toString para imprimir las pel�culas de lista.
     * Es de orden O(n), con n="n�mero de pel�culas en la lista"
     */

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < lista.size() - 1; i++) {
            str.append(lista.get(i).getTitulo());
            str.append("||");
        }
        str.append(lista.get(lista.size() - 1).getTitulo());
        return str.toString();
    }
}
