public interface InterfazInterpretes {
    void anadirInterprete(Interprete inter);

    Interprete buscarInterprete(String nombre);

    Interprete eliminarInterprete(String nombre);

    default boolean eliminarInterprete(Interprete inter) {
        if (inter == null) return false;
        return (eliminarInterprete(inter.getNombre()) != null);
    }

   int size();
}
