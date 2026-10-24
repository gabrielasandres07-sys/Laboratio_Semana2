package modelo;

import modelo.MaterialBibliografico;

/**
 *
 * @author kendr
 */
public class Libro extends MaterialBibliografico {
    
    private String autor;

    public Libro(String codigo, String titulo,
            int publicacion, String imagen, String autor) {

        super(codigo, titulo, publicacion, imagen);
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
