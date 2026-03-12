<%--
  Created by IntelliJ IDEA.
  User: fergo
  Date: 27/2/2026
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario de Empleado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<div class="card">
    <div class="card-header">
        <h3>${empleado.idEmpleado != 0 ? "Editar Empleado" : "Nuevo Empleado"}</h3>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/empleados" method="POST">
            <input type="hidden" name="id" value="${empleado.idEmpleado}">

            <input type="hidden" name="accion" value="${empleado.idEmpleado != 0 ? 'actualizar' : 'insertar'}">

            <div class="mb-3">
                <label>DUI (00000000-0):</label>
                <input type="text" name="txtDui" class="form-control" value="${empleado.numeroDui}" required>
            </div>
            <div class="mb-3">
                <label>Nombre Completo:</label>
                <input type="text" name="txtNombre" class="form-control" value="${empleado.nombrePersona}" required>
            </div>
            <div class="mb-3">
                <label>Nombre de Usuario:</label>
                <input type="text" name="txtUsuario" class="form-control" value="${empleado.usuario}" required>
            </div>
            <div class="mb-3">
                <label>Teléfono:</label>
                <input type="text" name="txtTelefono" class="form-control" value="${empleado.numeroTelefono}">
            </div>
            <div class="mb-3">
                <label>Correo Institucional:</label>
                <input type="email" name="txtCorreo" class="form-control" value="${empleado.correoInstitucional}">
            </div>
            <div class="mb-3">
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="txtFecha" class="form-control" value="${empleado.fechaNacimiento}">
            </div>

            <button type="submit" class="btn btn-primary">Guardar Datos</button>
            <a href="${pageContext.request.contextPath}/empleados?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>
</body>
</html>