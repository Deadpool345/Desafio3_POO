package Controller;
import Model.Venta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VentaController {
    private Venta ventaModel;

    public VentaController() {
        this.ventaModel = new Venta();
    }

    public boolean crearVenta(int idLinea, String fechaVenta, String descripcion, Connection connection) throws SQLException {
        Venta venta = new Venta();
        venta.setIdLinea(idLinea);
        venta.setFechaVenta(fechaVenta);
        venta.setDescripcion(descripcion);
        return venta.crearVenta(connection);
    }

    public List<Venta> obtenerVentas(Connection connection) throws SQLException {
        return Venta.obtenerVentas(connection);
    }

    public boolean actualizarVenta(int idVenta, int idLinea, String fechaVenta, String descripcion, Connection connection) throws SQLException {
        Venta venta = new Venta();
        venta.setIdVenta(idVenta);
        venta.setIdLinea(idLinea);
        venta.setFechaVenta(fechaVenta);
        venta.setDescripcion(descripcion);
        return venta.actualizarVenta(connection);
    }

    public boolean eliminarVenta(int idVenta, Connection connection) throws SQLException {
        Venta venta = new Venta();
        venta.setIdVenta(idVenta);
        return venta.eliminarVenta(connection);
    }
}

