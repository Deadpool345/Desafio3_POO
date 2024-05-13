package Controller;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/EliminarVenta")
public class EliminarVenta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));

        try {
            eliminarVenta(idVenta);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }

    private void eliminarVenta(int idVenta) throws SQLException {
        // Establecer conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/inventario";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM ventas WHERE id_venta = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idVenta);

                pstmt.executeUpdate();
            }
        }
    }

    public void destroy() {
    }
}