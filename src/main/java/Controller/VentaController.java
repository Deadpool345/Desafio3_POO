package Controller;

import Model.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Conexion;

import java.io.IOException;
import java.sql.*;


@WebServlet("/ventas")
public class VentaController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Por ejemplo, puedes obtener los parámetros del formulario
        String linea = request.getParameter("linea");
        String fecha = request.getParameter("fecha");
        String descripcion = request.getParameter("descripcion");

        try {
            // Establecer conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/inventario";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            // Preparar la consulta SQL para insertar una nueva venta
            String sql = "INSERT INTO ventas (id_linea, fecha_venta, descripcion) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, linea);
            pstmt.setString(2, fecha);
            pstmt.setString(3, descripcion);

            // Ejecutar la consulta SQL
            pstmt.executeUpdate();

            // Cerrar recursos
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirigir al usuario de vuelta a la página de ventas después de agregar la nueva venta
        response.sendRedirect(request.getContextPath() + "/ventas");
    }
}
