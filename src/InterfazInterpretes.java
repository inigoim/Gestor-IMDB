public interface InterfazInterpretes {
    public void anadirInterprete(Interprete inter);

    public Interprete buscarInterprete(String nombre);

    public Interprete eliminarInterprete(String nombre);

    default boolean eliminarInterprete(Interprete inter) {
        if (inter == null) return false;
        return (eliminarInterprete(inter.getNombre()) != null);
    }

    public int size();
}
