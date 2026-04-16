package co.edu.poli.examen2_Acero.tests.integracion;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Acero.modelo.Propietario;
import co.edu.poli.examen2_Acero.servicios.DAOPropietario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestDAOPropietario {

    // ✔️ 1. Test: la lista no es null
    @Test
    void readAll_noDebeRetornarNull() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        assertNotNull(lista);
    }

    // ✔️ 2. Test: la lista puede estar vacía pero válida
    @Test
    void readAll_listaInicializada() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        assertTrue(lista.size() >= 0);
    }

    // ✔️ 3. Test: si hay datos, los objetos están bien formados
    @Test
    void readAll_objetosValidos() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        if (!lista.isEmpty()) {
            Propietario t = lista.get(0);

            assertNotNull(t.getId());
            assertNotNull(t.getNombre());
        }
    }
}
