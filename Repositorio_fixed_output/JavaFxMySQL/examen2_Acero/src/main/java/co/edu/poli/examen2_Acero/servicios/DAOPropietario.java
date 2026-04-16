package co.edu.poli.examen2_Acero.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.examen2_Acero.modelo.Propietario;

public class DAOPropietario implements CRUD<Propietario> {

    @Override
    public String create(Propietario t) {
        // Por ahora lo dejamos nulo o puedes implementarlo similar al readall
        return null;
    }
    
    @Override
    public <K> Propietario readone(K id) throws Exception {
        return null;
    }

    @Override
    public List<Propietario> readall() throws Exception {

        Connection con = ConexionBD.getInstancia().getConexion();
        List<Propietario> lista = new ArrayList<>();

        // CAMBIO: Ahora la tabla se llama 'propietario' y las columnas 'id' y 'nombre'
        String SQL_SELECT_PROPIETARIO = "SELECT id, nombre FROM propietario;";

        PreparedStatement ps = con.prepareStatement(SQL_SELECT_PROPIETARIO);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // Usamos los nombres de columna de la nueva tabla
            Propietario p = new Propietario(rs.getString("id"), rs.getString("nombre"));
            lista.add(p);
        }
        return lista;
    }
}