<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Formulario de Empleado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="container mt-5">

<div class="card shadow">
    <div class="card-header bg-primary text-white">
        <h3><i class="bi bi-person-badge"></i> ${empleado.idEmpleado != 0 ? "Editar Empleado" : "Nuevo Empleado"}</h3>
    </div>
    <div class="card-body">

        <form action="${pageContext.request.contextPath}/EmpleadoServlet" method="POST">


            <input type="hidden" name="id" value="${empleado.idEmpleado}">
            <input type="hidden" name="accion" value="${empleado.idEmpleado != 0 ? 'Actualizar' : 'Insertar'}">

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">DUI (00000000-0):</label>
                    <input type="text"
                           name="txtDui"
                           class="form-control"
                           value="${empleado.numeroDui}"
                           pattern="\d{8}-\d"
                           placeholder="12345678-9"
                           required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Nombre Completo:</label>
                    <input type="text"
                           name="txtNombre"
                           class="form-control"
                           value="${empleado.nombrePersona}"
                           required>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Nombre de Usuario:</label>
                    <input type="text"
                           name="txtUsuario"
                           class="form-control"
                           value="${empleado.usuario}"
                           required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Teléfono:</label>
                    <input type="text"
                           name="txtTelefono"
                           class="form-control"
                           value="${empleado.numeroTelefono}"
                           placeholder="7777-8888">
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Correo Institucional:</label>
                    <input type="email"
                           name="txtCorreo"
                           class="form-control"
                           value="${empleado.correoInstitucional}"
                           placeholder="ejemplo@rrhh.com">
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Fecha de Nacimiento:</label>
                    <input type="date"
                           name="txtFecha"
                           class="form-control"
                           value="${empleado.fechaNacimiento}">
                </div>
            </div>

            <div class="mt-4 d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Guardar Datos
                </button>
                <a href="${pageContext.request.contextPath}/EmpleadoServlet?accion=Listar"
                   class="btn btn-secondary">
                    <i class="bi bi-x-circle"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</div>


<script>

    document.querySelector('input[name="txtDui"]').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 8) {
            value = value.substring(0, 8) + '-' + value.substring(8, 9);
        }
        e.target.value = value;
    });
</script>

</body>
</html>