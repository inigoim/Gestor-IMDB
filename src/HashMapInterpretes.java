import java.util.concurrent.ConcurrentHashMap;

public class HashMapInterpretes implements InterfazInterpretes {
    private final ConcurrentHashMap<String,Interprete> hmInterpretes = new ConcurrentHashMap<>();


    /*
     *A�ade un int�rprete al hashMap
     */
    public void anadirInterprete(Interprete inter) {
        hmInterpretes.put(inter.getNombre(),inter);
    }
    /*
     *Busca el int�rprete en el hashMap
     */
    public Interprete buscarInterprete(String nombre) {
        return  hmInterpretes.get(nombre);
    }

    /*
     *Elimina el int�rprete del hashMap, en caso de que el
     * int�rprete no este devolver� null
     */
    public Interprete eliminarInterprete(String nombre) {
        return hmInterpretes.remove(nombre);
    }

    /*
     *Devuelve el n�mero de elementos del hashMap
     */
    public int size() {
        return hmInterpretes.size();
    }
}
