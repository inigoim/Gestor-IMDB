import java.util.HashMap;

public class HashMapInterpretes implements InterfazInterpretes {
    private final HashMap<String,Interprete> hmInterpretes = new HashMap<>();


    /**
     *A�ade un int�rprete al hashMap.
     *Es de orden constante, O(1).
     *@param inter Int�rprete que se va a a�adir.
     */
    public void anadirInterprete(Interprete inter) {
        hmInterpretes.put(inter.getNombre(),inter);
    }
    
    /**
     *Busca el int�rprete en el hashMap.
     *Es de orden constante, O(1).
     * @param nombre nombre del int�rprete que se quiere a buscar.
     * @return Int�rprete buscado o null en caso de no ser encontrado.
     */
    public Interprete buscarInterprete(String nombre) {
        return hmInterpretes.get(nombre);
    }

    /**
     *Elimina el int�rprete del hashMap, en caso de que el
     * int�rprete no este devolver� null
     * Es de orden constante, O(1).
     * @param nombre nombre del int�rprete a eliminar.
     * @return Int�rprete eliminado del Hashmap o null
     * en caso de estar en el Hashmap.
     */
    public Interprete eliminarInterprete(String nombre) {
        return hmInterpretes.remove(nombre);
    }

    /**
     *Devuelve el n�mero de elementos del hashMap
     * Es de orden constante, O(1).
     * @return Integer con el n�mero de int�rpretes del HashMap
     */
    public int size() {
        return hmInterpretes.size();
    }
}
