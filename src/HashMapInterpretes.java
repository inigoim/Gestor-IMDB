import java.util.concurrent.ConcurrentHashMap;

public class HashMapInterpretes implements InterfazInterpretes {
    private final ConcurrentHashMap<String,Interprete> hmInterpretes = new ConcurrentHashMap<>();


    /**
     *A�ade un int�rprete al hashMap.
     *@param inter Int�rprete que se va a a�adir.
     */
    public void anadirInterprete(Interprete inter) {
        hmInterpretes.put(inter.getNombre(),inter);
    }
    
    /**
     *Busca el int�rprete en el hashMap.
     * @param nombre nombre del int�rprete que se quiere a buscar.
     * @return Int�rprete buscado o null en caso de no ser encontrado.
     */
    public Interprete buscarInterprete(String nombre) {
        if (hmInterpretes.containsKey(nombre))
            return hmInterpretes.get(nombre);
        return null;
    }

    /**
     *Elimina el int�rprete del hashMap, en caso de que el
     * int�rprete no este devolver� null
     * @param nombre nombre del int�rprete a eliminar.
     * @return Int�rprete eliminado del Hashmap o null
     * en caso de estar en el Hashmap.
     */
    public Interprete eliminarInterprete(String nombre) {
        return hmInterpretes.remove(nombre);
    }

    /**
     *Devuelve el n�mero de elementos del hashMap
     * @return Integer con el n�mero de int�rpretes del HashMap
     */
    public int size() {
        return hmInterpretes.size();
    }
}
