import java.util.HashMap;

public class HashMapInterpretes implements InterfazInterpretes {
    private HashMap<String,Interprete> hmInterpretes = new HashMap<>();


    /*
     *Añade un intérprete al hashMap
     */
    @Override
    public void anadirInterprete(Interprete inter) {
        hmInterpretes.put(inter.getNombre(),inter);
    }
    /*
     *Busca el intérprete en el hashMap
     */
    @Override
    public Interprete buscarInterprete(String nombre) {
        return  hmInterpretes.get(nombre);
    }

    /*
     *Elimina el intérprete del hashMap, en caso de que el
     * intérprete no este devolverá null
     */
    @Override
    public Interprete eliminarInterprete(String nombre) {
        return hmInterpretes.remove(nombre);
    }

    /*
     *Devuelve el número de elementos del hashMap
     */
    @Override
    public int size() {
        return hmInterpretes.size();
    }
}
