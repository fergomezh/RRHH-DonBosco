<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<nav aria-label="breadcrumb" class="mb-3">
  <ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
    </li>

    <li class="breadcrumb-item">
        <a href="${pageContext.request.contextPath}/cargos?accion=listar">
            Cargos
        </a>
    </li>

    <li class="breadcrumb-item active">
        ${titulo}
    </li>

  </ol>
</nav>

<div class="row justify-content-center">

  <div class="col-md-6">

    <div class="card form-card">

      <div class="card-header">
        <h6 class="mb-0">
            <i class="fas fa-briefcase me-2"></i>${titulo}
        </h6>
      </div>

      <div class="card-body p-4">

        <form method="post"
              action="${pageContext.request.contextPath}/cargos"
              novalidate>

          <input type="hidden" name="accion" value="guardar">

          <c:if test="${not empty cargo.idCargo and cargo.idCargo > 0}">
            <input type="hidden"
                   name="id"
                   value="${cargo.idCargo}">
          </c:if>

          <div class="mb-3">

            <label for="cargoNombre" class="form-label">
              Nombre del Cargo
              <span class="text-danger">*</span>
            </label>

            <input type="text"
                   class="form-control"
                   id="cargoNombre"
                   name="cargoNombre"
                   maxlength="100"
                   value="${cargo.cargoNombre}"
                   required>

            <div class="invalid-feedback">
                Este campo es obligatorio.
            </div>

          </div>

          <div class="mb-3">

            <label for="cargoDescripcion" class="form-label">
                Descripción
            </label>

            <textarea class="form-control"
                      id="cargoDescripcion"
                      name="cargoDescripcion"
                      rows="3">${cargo.cargoDescripcion}</textarea>

          </div>

          <div class="mb-4">

            <div class="form-check">
              <input class="form-check-input"
                     type="checkbox"
                     id="cargoJefatura"
                     name="cargoJefatura"
                     ${cargo.cargoJefatura ? 'checked' : ''}>
              <label class="form-check-label" for="cargoJefatura">
                  Es cargo de jefatura
              </label>
            </div>

          </div>

          <div class="d-flex gap-2">

            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save me-2"></i>Guardar
            </button>

            <a href="${pageContext.request.contextPath}/cargos?accion=listar"
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
