package Controller;

import java.io.IOException;
import java.sql.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AgregarVentaServlet")
public class AgregarVenta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idLinea = Integer.parseInt(request.getParameter("idLinea"));
        Date fechaVenta = Date.valueOf(request.getParameter("fechaVenta"));
        String descripcion = request.getParameter("descripcion");

        // Agregar la nueva venta a la base de datos
        try {
            agregarVenta(idLinea, fechaVenta, descripcion);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier error que ocurra al agregar la venta
            // (por ejemplo, mostrar un mensaje de error al usuario)
        }

        // Redirigir al usuario de vuelta a index.jsp después de agregar la venta
        response.sendRedirect("index.jsp");
    }

    private void agregarVenta(int idLinea, Date fechaVenta, String descripcion) throws SQLException {
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
