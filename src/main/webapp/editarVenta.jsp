<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Venta</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-[#EEF7FF]">

  <h1 class="text-3xl text-center text-gray-800 font-bold mb-8">Editar Venta</h1>
  <%
    PrintWriter a = response.getWriter();
    String idVenta = request.getParameter("idVenta");

      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "");
          Statement statement = connection.createStatement();

          String query = "SELECT * FROM ventas WHERE id_venta = '" + idVenta + "'";
          ResultSet resultSet = statement.executeQuery(query);
          if (!resultSet.next()) {
              response.sendRedirect("index.jsp");
              return;
          }

          String linea = resultSet.getString("id_linea");
          Date fechaVenta = resultSet.getDate("fecha_venta");
          String descripcion = resultSet.getString("descripcion");

          connection.close();
  %>
  <form action="EditarVenta" method="POST" class="max-w-md mx-auto bg-white p-8 my-8 rounded shadow-md">
      <input type="hidden" name="idVenta" value="<%= idVenta %>">
      <div class="mb-4">
          <label for="linea" class="block text-gray-700 text-sm font-bold mb-2">Línea:</label>
          <input type="text" name="linea" id="linea" value="<%= linea %>" class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
      </div>
      <div class="mb-4">
          <label for="fechaVenta" class="block text-gray-700 text-sm font-bold mb-2">Fecha de Venta:</label>
          <input type="date" name="fechaVenta" id="fechaVenta" value="<%= fechaVenta %>"
                 class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
      </div>
      <div class="mb-4">
          <label for="descripcion" class="block text-gray-700 text-sm font-bold mb-2">Descripción:</label>
          <textarea name="descripcion" id="descripcion"
                    class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    rows="4"><%= descripcion %></textarea>
      </div>
      <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Guardar
          Cambios
      </button>
  </form>
  <%
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      a.println("<h2>Error al cargar los datos de la venta.</h2>");
    }
  %>
</body>
</html>
