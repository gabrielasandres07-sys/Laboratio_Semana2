package modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */


import modelo.MaterialBibliografica;
import java.util.Calendar;
public class Prestamo {

    private MaterialBibliografica material;
    private Calendar fechaPrestamo;
    private Calendar fechaPrevista;
    private Calendar fechaDevolucion;
    private boolean activo;
    private int diasRetraso;
    private double multa;

    public Prestamo(MaterialBibliografica material){
        this(material, Calendar.getInstance());
    }

    public Prestamo(MaterialBibliografica material, Calendar fechaPrestamo){
        this.material = material;
        this.fechaPrestamo = limpiarHora(fechaPrestamo);

        this.fechaPrevista = (Calendar) this.fechaPrestamo.clone();
        this.fechaPrevista.add(Calendar.DAY_OF_MONTH, obtenerDiasPrestamo());

        this.fechaDevolucion = null;
        this.activo = true;
        this.diasRetraso = 0;
        this.multa = 0.0;
    }

    public boolean registrarDevolucion(){
        return registrarDevolucion(Calendar.getInstance());
    }

    public boolean registrarDevolucion(Calendar fechaDevolucion){
        if(!activo){
            return false;
        }
        this.fechaDevolucion=limpiarHora(fechaDevolucion);
        this.diasRetraso=calcularDiasRetraso();
        this.multa=obtenerMulta(diasRetraso);
        this.activo=false;
        return true;
    }

    private int obtenerDiasPrestamo(){
        return material.plazoPrestamo();
    }

    private double obtenerMulta(int dias){
        return dias * material.multaDiaria();
    }

    private Calendar limpiarHora(Calendar fecha){
        Calendar copia=(Calendar) fecha.clone();
        copia.set(Calendar.HOUR_OF_DAY, 0);
        copia.set(Calendar.MINUTE, 0);
        copia.set(Calendar.SECOND, 0);
        copia.set(Calendar.MILLISECOND, 0);
        return copia;
    }

    private int calcularDiasRetraso(){
        if (!fechaDevolucion.after(fechaPrevista)) {
            return 0;
        }
        Calendar contador = (Calendar) fechaPrevista.clone();
        int dias = 0;
        while (contador.before(fechaDevolucion)) {
            contador.add(Calendar.DAY_OF_MONTH, 1);
            dias++;
        }
        return dias;
    }

    public String formatearFecha(Calendar fecha){
        if (fecha == null) {
            return "-";
        }
        return String.format("%02d/%02d/%d",fecha.get(Calendar.DAY_OF_MONTH), fecha.get(Calendar.MONTH) + 1, fecha.get(Calendar.YEAR));
    }

    public MaterialBibliografica getMaterial(){
        return material;
    }

    public Calendar getFechaPrestamo(){
        return fechaPrestamo;
    }

    public Calendar getFechaPrevista(){
        return fechaPrevista;
    }

    public Calendar getFechaDevolucion(){
        return fechaDevolucion;
    }

    public boolean isActivo(){
        return activo;
    }

    public int getDiasRetraso(){
        return diasRetraso;
    }

    public double getMulta(){
        return multa;
    }

    public String toString(){
        String estado = activo ? "Activo" : "Devuelto";

        return String.format("Préstamo | %s | Prevista: %s | %s | Multa: %.2f €",formatearFecha(fechaPrestamo), formatearFecha(fechaPrevista), estado, multa);
    }
}