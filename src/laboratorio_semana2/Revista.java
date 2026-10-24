package laboratorio_semana2;

/**
 *
 * @author kendr
 */
public class Revista extends MaterialBibliografico{
    
    private int numeroEdicion;
    public Revista(String codigo, String titulo, int publicacion, String imagen, int numeroEdicion) {

        super(codigo, titulo,publicacion, imagen);
        this.numeroEdicion=numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }
    public int plazoPrestamo() {
        return 7;
    }

    public double multaDiaria() {
        return 0.75;
    }
}
