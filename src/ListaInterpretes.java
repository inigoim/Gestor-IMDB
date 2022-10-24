import java.util.ArrayList;

public class ListaInterpretes {
    private ArrayList<Interprete> lista = new ArrayList<>();
    /**
     * Añade un intérprete a la lista
     * @param inter Intérprete a añadir
     */
    public void anadirInterprete(Interprete inter) {
        lista.add(inter);
    }
    /**
     * Busca un intérprete en la lista y lo devuelve
     * @param nombre Nombre del intérprete a buscar
     * @return el Interprete (si está en la lista), null en caso contrario
     */
    public Interprete buscarInterprete(String nombre) {
        for (Interprete inter: lista)
            if (inter.getNombre().equals(nombre)) return inter;
        return null;
    }

    public ArrayList<Interprete> getLista() {
        return lista;
    }
}
