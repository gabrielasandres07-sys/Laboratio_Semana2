/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author USER
 */

import modelo.Tesis;
import modelo.Revista;
import modelo.Prestamo;
import modelo.Libro;
import java.util.Calendar;

public class PruebaFechas {

    private static int correctas = 0;
    private static int fallidas = 0;

    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.registrar(new Libro("L1", "El principito", 1943, "", "Saint-Exupery"));
        biblioteca.registrar(new Revista("R1", "National Geographic", 2024, "", 152));
        biblioteca.registrar(new Tesis("T1", "Redes neuronales", 2023, "", "Ana Lopez"));

        System.out.println("===== PRUEBAS DE FECHAS Y MULTAS =====");
        System.out.println();

        // Prueba: cambio de mes (enero -> febrero)
        Prestamo p = biblioteca.prestar("L1", fecha(25, 1, 2026));
        verificar("Libro 25/01 -> vence en febrero", "08/02/2026", p.formatearFecha(p.getFechaPrevista()));

        // Prueba: devolucion en la fecha prevista
        p = biblioteca.devolver("L1", fecha(8, 2, 2026));
        verificar("Devolucion en fecha prevista: dias", "0", "" + p.getDiasRetraso());
        verificar("Devolucion en fecha prevista: multa", "0.00", formatoMulta(p.getMulta()));

        // Prueba: cambio de anio (diciembre -> enero)
        p = biblioteca.prestar("L1", fecha(28, 12, 2026));
        verificar("Libro 28/12 -> vence en enero", "11/01/2027", p.formatearFecha(p.getFechaPrevista()));

        // Prueba: devolucion 1 dia tarde
        p = biblioteca.devolver("L1", fecha(12, 1, 2027));
        verificar("Libro 1 dia tarde: dias", "1", "" + p.getDiasRetraso());
        verificar("Libro 1 dia tarde: multa", "1.50", formatoMulta(p.getMulta()));

        // Prueba: febrero de 28 dias y retraso de revista
        p = biblioteca.prestar("R1", fecha(25, 2, 2026));
        verificar("Revista 25/02 -> vence en marzo", "04/03/2026", p.formatearFecha(p.getFechaPrevista()));
        p = biblioteca.devolver("R1", fecha(10, 3, 2026));
        verificar("Revista 6 dias tarde: dias", "6", "" + p.getDiasRetraso());
        verificar("Revista 6 dias tarde: multa", "4.50", formatoMulta(p.getMulta()));

        // Prueba: mes de 31 dias y retraso de tesis
        p = biblioteca.prestar("T1", fecha(30, 1, 2026));
        verificar("Tesis 30/01 -> vence en febrero", "02/02/2026", p.formatearFecha(p.getFechaPrevista()));
        p = biblioteca.devolver("T1", fecha(5, 2, 2026));
        verificar("Tesis 3 dias tarde: multa", "9.00", formatoMulta(p.getMulta()));

        // Prueba: devolucion antes de la fecha prevista
        biblioteca.prestar("T1", fecha(10, 3, 2026));
        p = biblioteca.devolver("T1", fecha(11, 3, 2026));
        verificar("Devolucion anticipada: dias", "0", "" + p.getDiasRetraso());

        // Prueba: prestar material no disponible
        biblioteca.prestar("L1", fecha(1, 4, 2026));
        verificar("Prestar material no disponible", "rechazado", intentarPrestar(biblioteca, "L1"));

        // Prueba: devolver dos veces
        biblioteca.devolver("L1", fecha(20, 4, 2026));
        verificar("Devolver dos veces", "rechazado", intentarDevolver(biblioteca, "L1"));

        // Prueba: busqueda recursiva
        verificar("Buscar codigo existente", "R1", biblioteca.buscarPorCodigo("R1").getCodigo());
        verificar("Buscar codigo inexistente", "no encontrado", biblioteca.buscarPorCodigo("X99") == null ? "no encontrado" : "encontrado");

        System.out.println("======================================");
        System.out.println("Pruebas superadas: " + correctas + " de " + (correctas + fallidas));
        if (fallidas == 0) {
            System.out.println("Todas las pruebas de fechas y multas funcionan correctamente.");
        } else {
            System.out.println("Hay " + fallidas + " prueba(s) con resultado distinto al esperado.");
        }
    }

    private static Calendar fecha(int dia, int mes, int anio){
        Calendar c = Calendar.getInstance();
        c.set(anio, mes - 1, dia, 10, 30, 0);
        return c;
    }

    private static String formatoMulta(double multa){
        return String.format("%.2f", multa).replace(',', '.');
    }

    private static void verificar(String nombre, String esperado, String obtenido){
        int numero = correctas + fallidas + 1;
        System.out.println("Prueba " + numero + ": " + nombre);
        System.out.println("   Esperado: " + esperado + " | Obtenido: " + obtenido);
        if (esperado.equals(obtenido)) {
            correctas++;
            System.out.println("   Resultado: CORRECTO");
        } else {
            fallidas++;
            System.out.println("   Resultado: INCORRECTO, revisar este caso");
        }
        System.out.println();
    }

    private static String intentarPrestar(Biblioteca biblioteca, String codigo){
        try {
            biblioteca.prestar(codigo);
            return "permitido";
        } catch (IllegalArgumentException e) {
            return "rechazado";
        }
    }

    private static String intentarDevolver(Biblioteca biblioteca, String codigo){
        try {
            biblioteca.devolver(codigo);
            return "permitido";
        } catch (IllegalArgumentException e) {
            return "rechazado";
        }
    }
}