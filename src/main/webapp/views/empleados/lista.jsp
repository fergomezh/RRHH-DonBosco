<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="container mt-5">

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="bi bi-people-fill"></i> Gestión de Empleados</h2>


    <a href="${pageContext.request.contextPath}/EmpleadoServlet?accion=Nuevo" class="btn btn-success">
        <i class="bi bi-plus-circle"></i> Agregar Nuevo
    </a>
</div>

<div class="card shadow">
    <div class="card-body">
        <table class="table table-hover">
            <thead class="table-dark">
            <tr>
                <th>DUI</th>
                <th>Nombre</th>
                <th>Usuario</th>
                <th>Teléfono</th>
                <th class="text-center">Acciones</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="emp" items="${lista}">
                <tr>
                    <td>${emp.numeroDui}</td>
                    <td>${emp.nombrePersona}</td>
                    <td><span class="badge bg-info text-dark">${emp.usuario}</span></td>
                    <td>${emp.numeroTelefono}</td>
                    <td class="text-center">

                        <a href="${pageContext.request.contextPath}/EmpleadoServlet?accion=Editar&id=${emp.idEmpleado}"
                           class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil-square"></i> Editar
                        </a>


                        <a href="${pageContext.request.contextPath}/EmpleadoServlet?accion=Eliminar&id=${emp.idEmpleado}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Estás seguro de eliminar a este empleado?')">
                            <i class="bi bi-trash"></i> Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lista}">
                <tr>
                    <td colspan="5" class="text-center text-muted">No hay empleados registrados.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>


<div class="mt-3">
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-link">Volver al Inicio</a>
</div>

</body>
</html>