package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection obtenerConexion() throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/inventario";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "";
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
