import java.util.HashMap;

public class HashMapInterpretes implements InterfazInterpretes {
    private final HashMap<String,Interprete> hmInterpretes = new HashMap<>();


    /**
     *Añade un intérprete al hashMap.
     *Es de orden constante, O(1).
     *@param inter Intérprete que se va a añadir.
     */
    public void anadirInterprete(Interprete inter) {
        hmInterpretes.put(inter.getNombre(),inter);
    }
    
    /**
     *Busca el intérprete en el hashMap.
     *Es de orden constante, O(1).
     * @param nombre nombre del intérprete que se quiere a buscar.
     * @return Intérprete buscado o null en caso de no ser encontrado.
     */
    public Interprete buscarInterprete(String nombre) {
        return hmInterpretes.get(nombre);
    }

    /**
     *Elimina el intérprete del hashMap, en caso de que el
     * intérprete no este devolverá null
     * Es de orden constante, O(1).
     * @param nombre nombre del intérprete a eliminar.
     * @return Intérprete eliminado del Hashmap o null
     * en caso de estar en el Hashmap.
     */
    public Interprete eliminarInterprete(String nombre) {
        return hmInterpretes.remove(nombre);
    }

    /**
     *Devuelve el número de elementos del hashMap
     * Es de orden constante, O(1).
     * @return Integer con el número de intérpretes del HashMap
     */
    public int size() {
        return hmInterpretes.size();
    }
}
