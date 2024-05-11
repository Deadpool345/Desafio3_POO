package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private int idLinea;
    private String fechaVenta;
    private String descripcion;

    public Venta() {
        // Constructor vacío
    }

    public Venta(int idVenta, int idLinea, String fechaVenta, String descripcion) {
        this.idVenta = idVenta;
        this.idLinea = idLinea;
        this.fechaVenta = fechaVenta;
        this.descripcion = descripcion;
    }

    // Getters y setters
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
    public boolean crearVenta(Connection connection) throws SQLException {
        String query = "INSERT INTO ventas (id_linea, fecha_venta, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.idLinea);
            statement.setString(2, this.fechaVenta);
            statement.setString(3, this.descripcion);
            return statement.executeUpdate() > 0;
        }
    }

    public static List<Venta> obtenerVentas(Connection connection) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM ventas";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(resultSet.getInt("id_venta"));
                venta.setIdLinea(resultSet.getInt("id_linea"));
                venta.setFechaVenta(resultSet.getString("fecha_venta"));
                venta.setDescripcion(resultSet.getString("descripcion"));
                ventas.add(venta);
            }
        }
        return ventas;
    }

    public boolean actualizarVenta(Connection connection) throws SQLException {
        String query = "UPDATE ventas SET id_linea=?, fecha_venta=?, descripcion=? WHERE id_venta=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.idLinea);
            statement.setString(2, this.fechaVenta);
            statement.setString(3, this.descripcion);
            statement.setInt(4, this.idVenta);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean eliminarVenta(Connection connection) throws SQLException {
        String query = "DELETE FROM ventas WHERE id_venta=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.idVenta);
            return statement.executeUpdate() > 0;
        }
    }
}

