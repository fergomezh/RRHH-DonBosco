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

                    <div class="mb-3">

                        <label class="form-label">
                            Departamento <span class="text-danger">*</span>
                        </label>

                        <select class="form-select" id="idDepartamento" name="idDepartamento" required>

                            <option value="">Seleccione un departamento</option>

                            <option value="1">Recursos Humanos</option>
                            <option value="2">Contabilidad</option>
                            <option value="3">Ventas</option>
                            <option value="4">Informática</option>

                        </select>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">
                            Empleado <span class="text-danger">*</span>
                        </label>

                        <select id="idEmpleado" name="idEmpleado" class="form-select" required>

                            <option value="">Seleccione...</option>

                            <c:forEach var="e" items="${empleados}">
                                <option value="${e.idEmpleado}"
                                        <c:if test="${not empty contratacion and e.idEmpleado == contratacion.idEmpleado}">
                                            selected
                                        </c:if>>
                                    ${e.nombre}
                                </option>
                            </c:forEach>

                        </select>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">
                            Cargo <span class="text-danger">*</span>
                        </label>

                        <select id="idCargo" name="idCargo" class="form-select" required>

                            <option value="">Seleccione...</option>

                            <c:forEach var="c" items="${cargos}">
                                <option value="${c.idCargo}"
                                        <c:if test="${not empty contratacion and c.idCargo == contratacion.idCargo}">
                                            selected
                                        </c:if>>
                                    ${c.nombre}
                                </option>
                            </c:forEach>

                        </select>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">
                            Tipo de Contratación <span class="text-danger">*</span>
                        </label>

                        <select id="idTipoContratacion" name="idTipoContratacion" class="form-select" required>

                            <option value="">Seleccione...</option>

                            <c:forEach var="t" items="${tipos}">
                                <option value="${t.idTipoContratacion}"
                                        <c:if test="${not empty contratacion and t.idTipoContratacion == contratacion.idTipoContratacion}">
                                            selected
                                        </c:if>>
                                    ${t.tipoContratacion}
                                </option>
                            </c:forEach>

                        </select>

                    </div>

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

                    <div class="mb-3">

                        <label class="form-label">
                            Salario <span class="text-danger">*</span>
                        </label>

                        <input type="number"
                               step="0.01"
                               id="salario"
                               name="salario"
                               class="form-control"
                               value="${not empty contratacion ? contratacion.salario : ''}"
                               required>

                    </div>

                    <div class="mb-4">

                        <label class="form-label">
                            Estado
                        </label>

                        <select name="estado" class="form-select">

                            <option value="true"
                                    <c:if test="${not empty contratacion and contratacion.estado}">
                                        selected
                                    </c:if>>
                                Activo
                            </option>

                            <option value="false"
                                    <c:if test="${not empty contratacion and not contratacion.estado}">
                                        selected
                                    </c:if>>
                                Inactivo
                            </option>

                        </select>

                    </div>

                    <div class="d-flex gap-2">

                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Guardar
                        </button>

                        <a href="${pageContext.request.contextPath}/contrataciones/lista"
                           class="btn btn-outline-secondary">
                            <i class="fas fa-arrow-left me-2"></i>Cancelar
                        </a>

                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<script src="${pageContext.request.contextPath}/assets/js/validaciones.js"></script>