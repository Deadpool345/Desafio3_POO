package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private int idLinea;
    private String fechaVenta;
    private String descripcion;

    public Venta() {
    }

    public Venta(int idVenta, int idLinea, String fechaVenta, String descripcion) {
        this.idVenta = idVenta;
        this.idLinea = idLinea;
        this.fechaVenta = fechaVenta;
        this.descripcion = descripcion;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos CRUD
    public static void agregarVenta(int idLinea, Date fechaVenta, String descripcion) throws SQLException {
        // Establecer conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/inventario";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Preparar la consulta SQL para agregar una nueva venta
            String sql = "INSERT INTO ventas (id_linea, fecha_venta, descripcion) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idLinea);
                pstmt.setDate(2, fechaVenta);
                pstmt.setString(3, descripcion);

                // Ejecutar la consulta SQL
                pstmt.executeUpdate();
            }
        }
    }
}

