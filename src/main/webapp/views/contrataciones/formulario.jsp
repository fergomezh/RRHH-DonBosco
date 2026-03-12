<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<nav aria-label="breadcrumb" class="mb-3">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/contrataciones/lista">
                Contrataciones
            </a>
        </li>
        <li class="breadcrumb-item active">${titulo}</li>
    </ol>
</nav>

<div class="row justify-content-center">
    <div class="col-md-7">
        <div class="card form-card">
            <div class="card-header">
                <h6 class="mb-0">
                    <i class="fas fa-file-contract me-2"></i>${titulo}
                </h6>
            </div>

            <div class="card-body p-4">
                <!-- Mostrar mensaje de error si existe -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fas fa-exclamation-triangle me-2"></i>${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <form method="post"
                      action="${pageContext.request.contextPath}/contrataciones/guardar"
                      novalidate>

                    <input type="hidden" name="accion" value="guardar">

                    <c:if test="${not empty contratacion}">
                        <c:if test="${contratacion.idContratacion > 0}">
                            <input type="hidden"
                                   name="idContratacion"
                                   value="${contratacion.idContratacion}">
                        </c:if>
                    </c:if>

                    <!-- DEPARTAMENTOS -->
                    <div class="mb-3">
                        <label class="form-label">
                            Departamento <span class="text-danger">*</span>
                        </label>
                        <select class="form-select" id="idDepartamento" name="idDepartamento" required>
                            <option value="">Seleccione un departamento</option>
                            <c:forEach var="depto" items="${departamentos}" varStatus="status">
                                <option value="${status.index + 1}"
                                        <c:if test="${not empty contratacion and contratacion.idDepartamento == status.index + 1}">selected</c:if>>
                                    ${depto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- PUESTOS (antes era Empleado) -->
                    <div class="mb-3">
                        <label class="form-label">
                            Puesto <span class="text-danger">*</span>
                        </label>
                        <select id="idEmpleado" name="idEmpleado" class="form-select" required>
                            <option value="">Seleccione un puesto</option>
                            <c:forEach var="puesto" items="${puestos}" varStatus="status">
                                <option value="${status.index + 1}"
                                        <c:if test="${not empty contratacion and contratacion.idEmpleado == status.index + 1}">selected</c:if>>
                                    ${puesto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- MODALIDADES (antes era Cargo) -->
                    <div class="mb-3">
                        <label class="form-label">
                            Modalidad <span class="text-danger">*</span>
                        </label>
                        <select id="idCargo" name="idCargo" class="form-select" required>
                            <option value="">Seleccione modalidad</option>
                            <c:forEach var="modalidad" items="${modalidades}" varStatus="status">
                                <option value="${status.index + 1}"
                                        <c:if test="${not empty contratacion and contratacion.idCargo == status.index + 1}">selected</c:if>>
                                    ${modalidad}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- TIPO DE CONTRATACIÓN (NUEVO) -->
                    <div class="mb-3">
                        <label class="form-label">
                            Tipo de Contratación <span class="text-danger">*</span>
                        </label>
                        <select id="idTipoContratacion" name="idTipoContratacion" class="form-select" required>
                            <option value="">Seleccione un tipo de contratación</option>
                            <c:forEach var="tipo" items="${tipos}" varStatus="status">
                                <option value="${status.index + 1}">${tipo}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- FECHA DE CONTRATACIÓN -->
                    <div class="mb-3">
                        <label class="form-label">
                            Fecha de Contratación <span class="text-danger">*</span>
                        </label>
                        <input type="date"
                               id="fechaContratacion"
                               name="fechaContratacion"
                               class="form-control"
                               value="${not empty contratacion ? contratacion.fechaContratacion : ''}"
                               required>
                    </div>

                    <!-- SALARIO -->
                    <div class="mb-3">
                        <label class="form-label">
                            Salario <span class="text-danger">*</span>
                        </label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number"
                                   step="0.01"
                                   min="0"
                                   id="salario"
                                   name="salario"
                                   class="form-control"
                                   value="${not empty contratacion ? contratacion.salario : ''}"
                                   placeholder="0.00"
                                   required>
                        </div>
                    </div>

                    <!-- ESTADO DEL PROCESO -->
                    <div class="mb-4">
                        <label class="form-label">
                            Estado del Proceso
                        </label>
                        <select name="estado" class="form-select">
                            <option value="">Seleccione estado</option>
                            <c:forEach var="estadoItem" items="${estados}">
                                <option value="${estadoItem}"
                                        <c:if test="${not empty contratacion and contratacion.estado == estadoItem}">selected</c:if>>
                                    ${estadoItem}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- BOTONES -->
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Guardar Contratación
                        </button>
                        <a href="${pageContext.request.contextPath}/contrataciones/lista"
                           class="btn btn-outline-secondary">
                            <i class="fas fa-times me-2"></i>Cancelar
                        </a>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<script src="${pageContext.request.contextPath}/assets/js/validaciones.js"></script>