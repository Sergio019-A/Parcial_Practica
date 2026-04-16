package co.edu.poli.examen2_Acero.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Acero.modelo.Apartamento;
import co.edu.poli.examen2_Acero.modelo.Casa;
import co.edu.poli.examen2_Acero.modelo.Inmueble;
import co.edu.poli.examen2_Acero.modelo.Propietario;
import co.edu.poli.examen2_Acero.servicios.DAOInmueble;

public class TestDAOInmueble {

    DAOInmueble dao = new DAOInmueble();

    @Test
    void create_debito_y_readone() throws Exception {

        Propietario titular = new Propietario("T001", "Test");

        Apartamento debito = new Apartamento(
                "999001",
                "2025-12-25",
                true,
                titular,
                5000.0
        );

        String result = dao.create(debito);

        assertTrue(result.contains("guardada"));

        Inmueble t = dao.readone("999001");

        assertNotNull(t);
        assertTrue(t instanceof Apartamento);

        Apartamento d = (Apartamento) t;
        assertEquals(5000.0, d.getSaldo());
    }

    @Test
    void create_credito_y_readone() throws Exception {

        Propietario titular = new Propietario("T001", "Test");

        Casa credito = new Casa(
                "999002",
                "2025-12-25",
                true,
                titular,
                10000.0
        );

        String result = dao.create(credito);

        assertTrue(result.contains("guardada"));

        Inmueble t = dao.readone("999002");

        assertNotNull(t);
        assertTrue(t instanceof Casa);

        Casa c = (Casa) t;
        assertEquals(10000.0, c.getLimite());
    }

    @Test
    void readone_noExiste() throws Exception {

        Inmueble t = dao.readone("000000");

        assertNull(t);
    }
}
