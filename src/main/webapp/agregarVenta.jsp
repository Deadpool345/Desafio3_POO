<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Venta</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">
<div class="container mx-auto py-8">
    <h1 class="text-3xl text-center text-gray-800 font-bold mb-8">Agregar Nueva Venta</h1>
    <form action="AgregarVentaServlet" method="POST" class="max-w-md mx-auto">
        <div class="mb-4">
            <label for="idLinea" class="block text-gray-700 text-sm font-bold mb-2">Linea:</label>
            <select name="idLinea" id="idLinea" class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <%
                    try {
                        String url = "jdbc:mysql://localhost:3306/inventario";
                        String user = "root";
                        String password = "";
                        Connection conn = DriverManager.getConnection(url, user, password);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT id_linea, Linea FROM lineas_de_venta");
                        while (rs.next()) {
                            int idLinea = rs.getInt("id_linea");
                            String nombreLinea = rs.getString("Linea");
                %>
                <option value="<%= idLinea %>"><%= nombreLinea %></option>
                <%
                        }
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </select>
        </div>
        <div class="mb-4">
            <label for="fechaVenta" class="block text-gray-700 text-sm font-bold mb-2">Fecha:</label>
            <input type="date" name="fechaVenta" id="fechaVenta" class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-4">
            <label for="descripcion" class="block text-gray-700 text-sm font-bold mb-2">Descripcion:</label>
            <textarea name="descripcion" id="descripcion" class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"></textarea>
        </div>
        <div class="flex justify-center">
            <button type="submit" class="bg-[#4D869C] hover:bg-[#73adc4] text-white font-bold py-2 px-4 rounded shadow-md">Agregar</button>
        </div>
    </form>
</div>
</body>

</html>
