package mx.itson.vivario.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Obtiene la conexión a SQL Server. */
public class Conexion {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=Vivario;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "JavaUser";
    private static final String PASSWORD = "password123";

    public static Connection obtener() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de SQL Server", ex);
        }
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
