/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author USER
 */


import modelo.Prestamo;
import modelo.MaterialBibliografico;
import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {

    private ArrayList<MaterialBibliografico> materiales;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca(){
        this.materiales = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public void registrar(MaterialBibliografico material){
        if (material == null) {
            throw new IllegalArgumentException("No hay material para registrar.");
        }
        if (material.getCodigo() == null || material.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio.");
        }
        if (material.getTitulo() == null || material.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (material.getPublicacion() < 1450 || material.getPublicacion() > anioActual) {
            throw new IllegalArgumentException("El año debe estar entre 1450 y " + anioActual + ".");
        }
        if (buscarPorCodigo(material.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un material con el código " + material.getCodigo().trim() + ".");
        }
        material.setCodigo(material.getCodigo().trim());
        materiales.add(material);
    }

    public MaterialBibliografico buscarPorCodigo(String codigo){
        if (codigo == null) {
            return null;
        }
        return buscarRecursivo(codigo.trim(), 0);
    }

    private MaterialBibliografico buscarRecursivo(String codigo, int indice){
        if (indice >= materiales.size()) {
            return null;
        }
        if (materiales.get(indice).getCodigo().equalsIgnoreCase(codigo)) {
            return materiales.get(indice);
        }
        return buscarRecursivo(codigo, indice + 1);
    }

    public Prestamo prestar(String codigo){
        return prestar(codigo, Calendar.getInstance());
    }

    public Prestamo prestar(String codigo, Calendar fecha){
        MaterialBibliografico material = buscarPorCodigo(codigo);
        if (material == null) {
            throw new IllegalArgumentException("No existe un material con el código " + codigo + ".");
        }
        if (!material.isDisponible()) {
            throw new IllegalArgumentException("El material " + material.getCodigo() + " no está disponible.");
        }
        Prestamo prestamo = new Prestamo(material, fecha);
        prestamos.add(prestamo);
        material.setDisponible(false);
        return prestamo;
    }

    public Prestamo devolver(String codigo){
        return devolver(codigo, Calendar.getInstance());
    }

    public Prestamo devolver(String codigo, Calendar fecha){
        MaterialBibliografico material = buscarPorCodigo(codigo);
        if (material == null) {
            throw new IllegalArgumentException("No existe un material con el código " + codigo + ".");
        }
        Prestamo prestamo = buscarPrestamoActivo(material.getCodigo());
        if (prestamo == null) {
            throw new IllegalArgumentException("El material " + material.getCodigo() + " no tiene un préstamo activo.");
        }
        if (prestamo.registrarDevolucion(fecha)) {
            material.setDisponible(true);
        }
        return prestamo;
    }

    public Prestamo buscarPrestamoActivo(String codigo){
        for (Prestamo p : prestamos) {
            if (p.isActivo() && p.getMaterial().getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<MaterialBibliografico> getMateriales(){
        return materiales;
    }

    public ArrayList<MaterialBibliografico> getDisponibles(){
        ArrayList<MaterialBibliografico> disponibles = new ArrayList<>();
        for (MaterialBibliografico m : materiales) {
            if (m.isDisponible()) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    public ArrayList<Prestamo> getPrestamosActivos(){
        ArrayList<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public ArrayList<Prestamo> getPrestamos(){
        return prestamos;
    }
}