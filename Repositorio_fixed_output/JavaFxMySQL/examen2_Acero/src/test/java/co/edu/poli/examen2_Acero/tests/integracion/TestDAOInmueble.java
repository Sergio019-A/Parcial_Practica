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
    void create_apartamento_y_readone() throws Exception {
        // NOTA: Asegúrate de que 'P001' existe en tu tabla propietario de MySQL
        Propietario titular = new Propietario("P001", "Carlos Acero");

        Apartamento apto = new Apartamento(
                "999001",
                "25/12/2025", // Formato DD/MM/YYYY para que tu nuevo DAO lo procese
                true,
                titular,
                5.0 // Esto se guarda en tu variable 'saldo' (que mapeamos a num_piso)
        );

        String result = dao.create(apto);

        // Cambié "guardada" por "guardado" para que coincida con el mensaje del DAO
        assertTrue(result.contains("guardado"));

        Inmueble t = dao.readone("999001");

        assertNotNull(t);
        assertTrue(t instanceof Apartamento);

        Apartamento d = (Apartamento) t;
        assertEquals(5.0, d.getSaldo());
    }

    @Test
    void create_casa_y_readone() throws Exception {
        Propietario titular = new Propietario("P001", "Carlos Acero");

        Casa casa = new Casa(
                "999002",
                "25/12/2025",
                true,
                titular,
                3.0 // Esto se guarda en 'limite' (que mapeamos a cant_pisos)
        );

        String result = dao.create(casa);

        assertTrue(result.contains("guardado"));

        Inmueble t = dao.readone("999002");

        assertNotNull(t);
        assertTrue(t instanceof Casa);

        Casa c = (Casa) t;
        assertEquals(3.0, c.getLimite());
    }

    @Test
    void readone_noExiste() throws Exception {
        // Un número que sepamos que no está en la DB
        Inmueble t = dao.readone("000000");

        assertNull(t);
    }
}
