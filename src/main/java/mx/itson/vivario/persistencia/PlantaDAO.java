package mx.itson.vivario.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.vivario.entidades.Planta;

/** Acceso a datos de la tabla planta. */
public class PlantaDAO {

    /** Trae todos los registros guardados en la tabla. */
    public List<Planta> obtenerTodas() throws SQLException {
        List<Planta> lista = new ArrayList<>();
        String sql = "SELECT id, nombre_comun, nombre_cient, tipo, riego_dias, luz, precio, fecha_registro "
                   + "FROM Planta ORDER BY id DESC";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Planta p = new Planta();
                p.setId(rs.getInt("id"));
                p.setNombreComun(rs.getString("nombre_comun"));
                p.setNombreCient(rs.getString("nombre_cient"));
                p.setTipo(rs.getString("tipo"));
                p.setRiegoDias(rs.getInt("riego_dias"));
                p.setLuz(rs.getString("luz"));
                p.setPrecio(rs.getDouble("precio"));
                p.setFechaRegistro(rs.getDate("fecha_registro"));
                lista.add(p);
            }
        }
        return lista;
    }

    /** Guarda un nuevo registro en la tabla. */
    public void guardar(Planta p) throws SQLException {
        String sql = "INSERT INTO Planta (nombre_comun, nombre_cient, tipo, riego_dias, luz, precio, fecha_registro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombreComun());
            ps.setString(2, p.getNombreCient());
            ps.setString(3, p.getTipo());
            ps.setInt(4, p.getRiegoDias());
            ps.setString(5, p.getLuz());
            ps.setDouble(6, p.getPrecio());
            ps.setDate(7, new Date(System.currentTimeMillis()));
            ps.executeUpdate();
        }
    }
}
