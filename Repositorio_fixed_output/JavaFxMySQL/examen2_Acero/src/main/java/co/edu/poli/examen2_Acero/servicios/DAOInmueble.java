package co.edu.poli.examen2_Acero.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import co.edu.poli.examen2_Acero.modelo.Apartamento;
import co.edu.poli.examen2_Acero.modelo.Casa;
import co.edu.poli.examen2_Acero.modelo.Inmueble;
import co.edu.poli.examen2_Acero.modelo.Propietario;

public class DAOInmueble implements CRUD<Inmueble> {

    @Override
public String create(Inmueble t) throws Exception {

    Connection con = ConexionBD.getInstancia().getConexion();
    con.setAutoCommit(false);

    // 1. Lógica para formatear la fecha de DD/MM/YYYY a YYYY-MM-DD
    String fechaSQL = "";
    try {
        String fechaOriginal = t.getFechaExp(); // "29/04/2026"
        String[] partes = fechaOriginal.split("/");
        // Reorganizamos a "2026-04-29"
        fechaSQL = partes[2] + "-" + partes[1] + "-" + partes[0];
    } catch (Exception e) {
        // Si la fecha no viene con "/" o está mal escrita, intentamos mandarla tal cual
        fechaSQL = t.getFechaExp();
    }

    // 2. Insertar en la tabla base: inmueble
    String SQL_INSERT_INMUEBLE = "INSERT INTO inmueble (numero, fecha_compra, estado, propietario_id) "
            + "VALUES (?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(SQL_INSERT_INMUEBLE);
    ps.setString(1, t.getNumero());
    ps.setString(2, fechaSQL); // Enviamos la fecha ya formateada para MySQL
    ps.setBoolean(3, t.isEstado());
    ps.setString(4, t.getTitular().getId()); 
    ps.executeUpdate();

    // 3. Insertar en la tabla hija correspondiente (Apartamento o Casa)
    String SQL_INSERT_APTO = "INSERT INTO apartamento (numero, num_piso) VALUES (?, ?)";
    String SQL_INSERT_CASA = "INSERT INTO casa (numero, cant_pisos) VALUES (?, ?)";

    String sql = (t instanceof Apartamento) ? SQL_INSERT_APTO : SQL_INSERT_CASA;
    ps = con.prepareStatement(sql);
    ps.setString(1, t.getNumero());
    
    if (t instanceof Apartamento)
        ps.setDouble(2, ((Apartamento) t).getSaldo());
    else
        ps.setDouble(2, ((Casa) t).getLimite());
    
    try {
        ps.executeUpdate();
        con.commit();
        return "✔ " + t.getClass().getSimpleName() + " [" + t.getNumero() + "] guardado correctamente.";
    } catch (Exception e) {
        con.rollback();
        return "Error en tablas hijas: " + e.getMessage();
    } finally {
        con.setAutoCommit(true);
    }
}
    @Override
    public <K> Inmueble readone(K num) throws Exception {

        Connection con = ConexionBD.getInstancia().getConexion();

        // SELECT para Apartamento (mapeando columnas de DB a tus atributos de Java)
        String SQL_SELECT_APTO = "SELECT i.numero, i.fecha_compra, i.estado, "
                + "       p.id AS prop_id, p.nombre AS prop_nombre, " 
                + "       a.num_piso "
                + "FROM   apartamento a " 
                + "INNER JOIN inmueble i ON a.numero = i.numero "
                + "INNER JOIN propietario p ON i.propietario_id = p.id " 
                + "WHERE  a.numero = ?";

        PreparedStatement ps = con.prepareStatement(SQL_SELECT_APTO);
        ps.setString(1, (String) num);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return new Apartamento(
                rs.getString("numero"), 
                rs.getString("fecha_compra"), // Se guarda en fechaExp
                rs.getBoolean("estado"),
                new Propietario(rs.getString("prop_id"), rs.getString("prop_nombre")), 
                rs.getDouble("num_piso") // Se guarda en saldo
            );
        }

        // SELECT para Casa
        String SQL_SELECT_CASA = "SELECT i.numero, i.fecha_compra, i.estado, "
                + "       p.id AS prop_id, p.nombre AS prop_nombre, " 
                + "       c.cant_pisos "
                + "FROM   casa c " 
                + "INNER JOIN inmueble i ON c.numero = i.numero "
                + "INNER JOIN propietario p ON i.propietario_id = p.id " 
                + "WHERE  c.numero = ?";

        ps = con.prepareStatement(SQL_SELECT_CASA);
        ps.setString(1, (String) num);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            return new Casa(
                rs.getString("numero"), 
                rs.getString("fecha_compra"), 
                rs.getBoolean("estado"),
                new Propietario(rs.getString("prop_id"), rs.getString("prop_nombre")), 
                rs.getDouble("cant_pisos") // Se guarda en limite
            );
        }

        return null;
    }

    @Override
    public List<Inmueble> readall() {
        return null;
    }
}