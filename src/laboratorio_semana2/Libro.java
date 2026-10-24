package laboratorio_semana2;

/**
 *
 * @author kendr
 */
public class Libro extends MaterialBibliografico {
    
    private String autor;

    public Libro(String codigo, String titulo,
            int anioPublicacion, String imagen, String autor) {

        super(codigo, titulo, anioPublicacion, imagen);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }


    public int plazoPrestamo() {
        return 14;
    }


    public double multaDiaria() {
        return 1.50;
    }
}
