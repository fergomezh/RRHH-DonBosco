<%--
  Created by IntelliJ IDEA.
  User: fergo
  Date: 27/2/2026
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<nav aria-label="breadcrumb" class="mb-3">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/empleados?accion=listar">
                Empleados
            </a>
        </li>
        <li class="breadcrumb-item active">
            ${empleado.idEmpleado != 0 ? "Editar Empleado" : "Nuevo Empleado"}
        </li>
    </ol>
</nav>

<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card form-card">
            <div class="card-header">
                <h6 class="mb-0">
                    <i class="fas fa-user me-2"></i>${empleado.idEmpleado != 0 ? "Editar Empleado" : "Nuevo Empleado"}
                </h6>
            </div>

            <div class="card-body p-4">
                <form method="post"
                      action="${pageContext.request.contextPath}/empleados"
                      novalidate>

                    <input type="hidden" name="id" value="${empleado.idEmpleado}">
                    <input type="hidden" name="accion" value="${empleado.idEmpleado != 0 ? 'actualizar' : 'insertar'}">

                    <div class="mb-3">
                        <label for="txtDui" class="form-label">
                            DUI
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="txtDui"
                               name="txtDui"
                               maxlength="10"
                               value="${empleado.numeroDui}"
                               placeholder="00000000-0"
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="txtNombre" class="form-label">
                            Nombre Completo
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="txtNombre"
                               name="txtNombre"
                               maxlength="100"
                               value="${empleado.nombrePersona}"
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="txtUsuario" class="form-label">
                            Usuario
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="txtUsuario"
                               name="txtUsuario"
                               maxlength="50"
                               value="${empleado.usuario}"
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="txtTelefono" class="form-label">
                            Teléfono
                        </label>
                        <input type="text"
                               class="form-control"
                               id="txtTelefono"
                               name="txtTelefono"
                               maxlength="15"
                               value="${empleado.numeroTelefono}"
                               placeholder="7777-8888">
                    </div>

                    <div class="mb-3">
                        <label for="txtCorreo" class="form-label">
                            Correo Institucional
                        </label>
                        <input type="email"
                               class="form-control"
                               id="txtCorreo"
                               name="txtCorreo"
                               maxlength="100"
                               value="${empleado.correoInstitucional}"
                               placeholder="ejemplo@rrhh.com">
                    </div>

                    <div class="mb-4">
                        <label for="txtFecha" class="form-label">
                            Fecha de Nacimiento
                        </label>
                        <input type="date"
                               class="form-control"
                               id="txtFecha"
                               name="txtFecha"
                               value="${empleado.fechaNacimiento}">
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/empleados?accion=listar"
                           class="btn btn-outline-secondary">
                            <i class="fas fa-arrow-left me-2"></i>Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById('txtDui').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 8) {
            value = value.substring(0, 8) + '-' + value.substring(8, 9);
        }
        e.target.value = value;
    });
</script>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>