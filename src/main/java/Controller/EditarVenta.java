package Controller;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/EditarVenta")
public class EditarVenta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        String nuevaLinea = request.getParameter("linea");
        String nuevaDescripcion = request.getParameter("descripcion");
        String nuevaFechaVenta = request.getParameter("fechaVenta");

        try {
            String url = "jdbc:mysql://localhost:3306/inventario";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE ventas SET id_linea = ?, descripcion = ?, fecha_venta = ? WHERE id_venta = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevaLinea);
            pstmt.setString(2, nuevaDescripcion);
            pstmt.setString(3, nuevaFechaVenta);
            pstmt.setInt(4, idVenta);

            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
