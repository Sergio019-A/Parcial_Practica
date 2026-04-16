package co.edu.poli.examen2_Acero.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Acero.modelo.Apartamento;
import co.edu.poli.examen2_Acero.modelo.Inmueble;
import co.edu.poli.examen2_Acero.modelo.Propietario;

public class TestInmueble {

    @Test
    void bloquear_cambiaEstadoAFalso() {
        Propietario titular = new Propietario("1", "Test");
        // Nota: El 'saldo' en tu modelo es double, así que usamos 1000.0
        Inmueble t = new Apartamento("123", "2025-12-25", true, titular, 1000.0);

        String mensaje = t.bloquear();

        assertFalse(t.isEstado());
        // Verificamos que el mensaje contenga la palabra clave (ignora si es ADO o ADA)
        assertTrue(mensaje.toUpperCase().contains("BLOQUEAD"));
    }

    @Test
    void activar_cambiaEstadoAVerdadero() {
        Propietario titular = new Propietario("1", "Test");
        Inmueble t = new Apartamento("123", "2025-12-25", false, titular, 1000.0);

        String mensaje = t.activar();

        assertTrue(t.isEstado());
        assertTrue(mensaje.toUpperCase().contains("ACTIVAD"));
    }

    @Test
    void getters_retornaValoresCorrectos() {
        Propietario titular = new Propietario("1", "Test");
        Inmueble t = new Apartamento("123", "2025-12-25", true, titular, 1000.0);

        assertEquals("123", t.getNumero());
        assertEquals("2025-12-25", t.getFechaExp());
        assertTrue(t.isEstado());
        assertEquals(titular, t.getTitular());
    }

    @Test
    void setters_modificanValores() {
        Propietario titular = new Propietario("1", "Test");
        Propietario nuevo = new Propietario("2", "Nuevo");

        Inmueble t = new Apartamento("123", "2025-12-25", true, titular, 1000.0);

        t.setNumero("999");
        t.setFechaExp("2030-01-01");
        t.setEstado(false);
        t.setTitular(nuevo);

        assertEquals("999", t.getNumero());
        assertEquals("2030-01-01", t.getFechaExp());
        assertFalse(t.isEstado());
        assertEquals(nuevo, t.getTitular());
    }

    @Test
    void toString_contieneDatos() {
        Propietario titular = new Propietario("1", "Test");
        Inmueble t = new Apartamento("123", "2025-12-25", true, titular, 1000.0);

        String texto = t.toString();

        // Verificamos datos esenciales que definiste en el toString de Inmueble
        assertTrue(texto.contains("123"));
        assertTrue(texto.contains("2025-12-25"));
    }
}