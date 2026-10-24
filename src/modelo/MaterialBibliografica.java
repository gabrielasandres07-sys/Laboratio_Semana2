package modelo;
/**
 *
 * @author kendr
 */
import java.util.Calendar;

public class MaterialBibliografica {
    private String codigo;
    private String titulo;
    private int publicacion;
    private boolean disponible;
    private String imagen;
    
    public MaterialBibliografica(String codigo, String titulo, int publicacion, String imagen) {

    this.codigo=codigo;
    this.titulo=titulo;
    this.publicacion=publicacion;
    this.imagen=imagen;
    this.disponible=true;
}
    
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public int plazoPrestamo() {
         return 0;
    }

    public double multaDiaria() {
       return 0.0;
    }
    
    
}