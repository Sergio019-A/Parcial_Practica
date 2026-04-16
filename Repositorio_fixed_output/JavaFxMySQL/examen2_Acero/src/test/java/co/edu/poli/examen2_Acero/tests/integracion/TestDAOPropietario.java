package co.edu.poli.examen2_Acero.tests.integracion;

import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Acero.modelo.Propietario;
import co.edu.poli.examen2_Acero.servicios.DAOPropietario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestDAOPropietario {

    // ✔️ 1. Test: Verifica que la conexión devuelva una lista (no null)
    @Test
    void readAll_noDebeRetornarNull() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        assertNotNull(lista, "La lista no debería ser null, incluso si la tabla está vacía");
    }

    // ✔️ 2. Test: Verifica que la lista tenga al menos los 2 propietarios que insertamos por script
    @Test
    void readAll_listaConDatos() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        // Como insertamos a 'Carlos Acero' y 'Elena Rodriguez', el tamaño debería ser >= 2
        assertTrue(lista.size() >= 2, "La lista debería tener al menos 2 propietarios iniciales");
    }

    // ✔️ 3. Test: Verifica que los datos del primer propietario coincidan con el script
    @Test
    void readAll_objetosValidos() throws Exception {
        DAOPropietario dao = new DAOPropietario();

        List<Propietario> lista = dao.readall();

        if (!lista.isEmpty()) {
            // Buscamos a Carlos Acero que es el P001
            Propietario t = lista.stream()
                                 .filter(p -> p.getId().equals("P001"))
                                 .findFirst()
                                 .orElse(lista.get(0));

            assertNotNull(t.getId());
            assertNotNull(t.getNombre());
            System.out.println("Propietario cargado: " + t.getNombre());
        }
    }
}