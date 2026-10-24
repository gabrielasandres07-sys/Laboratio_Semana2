package modelo;

import modelo.MaterialBibliografico;

/**
 *
 * @author kendr
 */
public class Tesis extends MaterialBibliografico{
    private String tesista;

    public Tesis(String codigo, String titulo, int publicacion, String imagen, String tesista) {

        super(codigo, titulo, publicacion, imagen);
        this.tesista = tesista;
    }
    public String getTesista() {
        return tesista;
    }
    public int plazoPrestamo() {
        return 3;
    }
    public double multaDiaria() {
        return 3.00;
    }
}
