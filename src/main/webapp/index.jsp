<%@ page import="java.sql.*" %>
<%@ page import="Model.Venta" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Ventas</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-[#CDE8E5]">
<div class="container mx-auto py-8">
    <h1 class="text-3xl text-center text-gray-800 font-bold mb-8">Gestion de Ventas</h1>
    <div class="flex justify-center mb-4">
        <a href="editarVenta.jsp" class="bg-[#4D869C] hover:bg-[#73adc4] text-white font-bold py-2 px-4 rounded shadow-md">
            Agregar Venta
        </a>
    </div>
    <div class="overflow-x-auto p-[2rem]">
        <table class="table-auto w-full bg-white shadow-md rounded-lg">
            <thead>
            <tr class="bg-[#7AB2B2] ">
                <th class="px-4 py-2">#</th>
                <th class="px-4 py-2">Linea</th>
                <th class="px-4 py-2">Fecha</th>
                <th class="px-4 py-2">Descripcion</th>
                <th class="px-4 py-2">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    String url = "jdbc:mysql://localhost:3306/inventario";
                    String user = "root";
                    String password = "";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM ventas JOIN lineas_de_venta ON ventas.id_linea = lineas_de_venta.id_linea");
                    while (rs.next()) {
            %>
            <tr class="border-b border-gray-200">
                <td class="px-4 py-2"><%= rs.getInt("id_venta") %></td>
                <td class="px-4 py-2"><%= rs.getString("Linea") %></td>
                <td class="px-4 py-2"><%= rs.getString("fecha_venta") %></td>
                <td class="px-4 py-2"><%= rs.getString("descripcion") %></td>
                <td class="px-4 py-2">
                    <div class="flex justify-center items-center space-x-4">
                        <form action="EliminarVenta" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta venta?');">
                            <input type="hidden" name="idVenta" value="<%= rs.getInt("id_venta") %>">
                            <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-md transition duration-300 ease-in-out">Eliminar</button>
                        </form>
                        <div>
                            <a href="editarVenta.jsp?idVenta=<%= rs.getInt("id_venta") %>" class="inline-block bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md transition duration-300 ease-in-out">
                                Editar
                            </a>
                        </div>
                    </div>
                </td>
            </tr>
            <%
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>
