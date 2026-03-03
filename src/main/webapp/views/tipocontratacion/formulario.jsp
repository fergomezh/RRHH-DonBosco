<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<nav aria-label="breadcrumb" class="mb-3">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/dashboard">Inicio</a></li>
    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/tipocontratacion?accion=listar">Tipos Contratación</a></li>
    <li class="breadcrumb-item active">${titulo}</li>
  </ol>
</nav>

<div class="row justify-content-center">
  <div class="col-md-6">
    <div class="card form-card">
      <div class="card-header">
        <h6 class="mb-0"><i class="fas fa-file-contract me-2"></i>${titulo}</h6>
      </div>
      <div class="card-body p-4">
        <form method="post" action="${pageContext.request.contextPath}/tipocontratacion" novalidate>
          <input type="hidden" name="accion" value="guardar">
          <c:if test="${not empty tipo.idTipoContratacion and tipo.idTipoContratacion > 0}">
            <input type="hidden" name="idTipoContratacion" value="${tipo.idTipoContratacion}">
          </c:if>

          <div class="mb-4">
            <label for="tipoContratacion" class="form-label">
              Tipo de Contratación <span class="text-danger">*</span>
            </label>
            <input type="text" class="form-control" id="tipoContratacion"
                   name="tipoContratacion" maxlength="100"
                   value="${tipo.tipoContratacion}"
                   placeholder="Ej: Tiempo Completo" required>
            <div class="invalid-feedback">Este campo es obligatorio.</div>
          </div>

          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-save me-2"></i>Guardar
            </button>
            <a href="${pageContext.request.contextPath}/tipocontratacion?accion=listar"
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
