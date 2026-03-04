<%--
  Created by IntelliJ IDEA.
  User: fergo
  Date: 27/2/2026
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2>Gestión de Empleados</h2>
<a href="EmpleadoServlet?accion=Nuevo" class="btn btn-success mb-3">Agregar Nuevo</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>DUI</th>
        <th>Nombre</th>
        <th>Usuario</th>
        <th>Teléfono</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${lista}">
        <tr>
            <td>${emp.numeroDui}</td>
            <td>${emp.nombrePersona}</td>
            <td>${emp.usuario}</td>
            <td>${emp.numeroTelefono}</td>
            <td>
                <a href="EmpleadoServlet?accion=Editar&id=${emp.idEmpleado}" class="btn btn-warning btn-sm">Editar</a>
                <a href="EmpleadoServlet?accion=Eliminar&id=${emp.idEmpleado}"
                   class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar?')">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
