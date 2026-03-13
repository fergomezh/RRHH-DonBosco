<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<nav aria-label="breadcrumb" class="mb-3">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/contrataciones?accion=listar">
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
                      action="${pageContext.request.contextPath}/contrataciones?accion=guardar"
                      novalidate>

                    <c:if test="${not empty contratacion and contratacion.idContratacion > 0}">
                        <input type="hidden" name="idContratacion" value="${contratacion.idContratacion}">
                    </c:if>

                    <%-- EMPLEADO --%>
                    <div class="mb-3">
                        <label for="idEmpleado" class="form-label">
                            Empleado <span class="text-danger">*</span>
                        </label>
                        <select class="form-select" id="idEmpleado" name="idEmpleado" required>
                            <option value="">Seleccione un empleado</option>
                            <c:forEach var="emp" items="${empleados}">
                                <option value="${emp.idEmpleado}"
                                    ${contratacion.idEmpleado == emp.idEmpleado ? 'selected' : ''}>
                                    ${emp.nombrePersona}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- DEPARTAMENTO --%>
                    <div class="mb-3">
                        <label for="idDepartamento" class="form-label">
                            Departamento <span class="text-danger">*</span>
                        </label>
                        <select class="form-select" id="idDepartamento" name="idDepartamento" required>
                            <option value="">Seleccione un departamento</option>
                            <c:forEach var="depto" items="${departamentos}">
                                <option value="${depto.idDepartamento}"
                                    ${contratacion.idDepartamento == depto.idDepartamento ? 'selected' : ''}>
                                    ${depto.nombreDepartamento}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- CARGO --%>
                    <div class="mb-3">
                        <label for="idCargo" class="form-label">
                            Cargo <span class="text-danger">*</span>
                        </label>
                        <select class="form-select" id="idCargo" name="idCargo" required>
                            <option value="">Seleccione un cargo</option>
                            <c:forEach var="cargo" items="${cargos}">
                                <option value="${cargo.idCargo}"
                                    ${contratacion.idCargo == cargo.idCargo ? 'selected' : ''}>
                                    ${cargo.cargoNombre}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- TIPO DE CONTRATACIÓN --%>
                    <div class="mb-3">
                        <label for="idTipoContratacion" class="form-label">
                            Tipo de Contratación <span class="text-danger">*</span>
                        </label>
                        <select class="form-select" id="idTipoContratacion" name="idTipoContratacion" required>
                            <option value="">Seleccione un tipo</option>
                            <c:forEach var="tipo" items="${tipos}">
                                <option value="${tipo.idTipoContratacion}"
                                    ${contratacion.idTipoContratacion == tipo.idTipoContratacion ? 'selected' : ''}>
                                    ${tipo.tipoContratacion}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- FECHA DE CONTRATACIÓN --%>
                    <div class="mb-3">
                        <label for="fechaContratacion" class="form-label">
                            Fecha de Contratación <span class="text-danger">*</span>
                        </label>
                        <input type="date"
                               class="form-control"
                               id="fechaContratacion"
                               name="fechaContratacion"
                               value="${not empty contratacion ? contratacion.fechaContratacion : ''}"
                               required>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- SALARIO --%>
                    <div class="mb-3">
                        <label for="salario" class="form-label">
                            Salario <span class="text-danger">*</span>
                        </label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number"
                                   step="0.01"
                                   min="0"
                                   class="form-control"
                                   id="salario"
                                   name="salario"
                                   value="${not empty contratacion ? contratacion.salario : ''}"
                                   placeholder="0.00"
                                   required>
                        </div>
                        <div class="invalid-feedback">Este campo es obligatorio.</div>
                    </div>

                    <%-- ESTADO --%>
                    <div class="mb-4">
                        <div class="form-check">
                            <input class="form-check-input"
                                   type="checkbox"
                                   id="estado"
                                   name="estado"
                                   ${contratacion.estado ? 'checked' : ''}>
                            <label class="form-check-label" for="estado">
                                Contratación activa
                            </label>
                        </div>
                    </div>

                    <%-- BOTONES --%>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/contrataciones?accion=listar"
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
