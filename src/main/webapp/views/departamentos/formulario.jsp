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
    <a href="${pageContext.request.contextPath}/departamento?accion=listar">
        Departamentos
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
        <i class="fas fa-building me-2"></i>${titulo}
    </h6>
  </div>

  <div class="card-body p-4">

    <form method="post"
          action="${pageContext.request.contextPath}/departamento"
          novalidate>

      <input type="hidden" name="accion" value="guardar">

      <c:if test="${not empty departamento.idDepartamento and departamento.idDepartamento > 0}">
        <input type="hidden"
               name="idDepartamento"
               value="${departamento.idDepartamento}">
      </c:if>

      <div class="mb-3">

        <label for="nombreDepartamento" class="form-label">
          Nombre del Departamento
          <span class="text-danger">*</span>
        </label>

        <input type="text"
               class="form-control"
               id="nombreDepartamento"
               name="nombreDepartamento"
               maxlength="50"
               value="${departamento.nombreDepartamento}"
               required>

        <div class="invalid-feedback">
            Este campo es obligatorio.
        </div>

      </div>

      <div class="mb-4">

        <label for="descripcionDepartamento" class="form-label">
            Descripción
        </label>

        <textarea class="form-control"
                  id="descripcionDepartamento"
                  name="descripcionDepartamento"
                  rows="3">${departamento.descripcionDepartamento}</textarea>

      </div>

      <div class="d-flex gap-2">

        <button type="submit" class="btn btn-primary">
            <i class="fas fa-save me-2"></i>Guardar
        </button>

        <a href="${pageContext.request.contextPath}/departamento?accion=listar"
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

