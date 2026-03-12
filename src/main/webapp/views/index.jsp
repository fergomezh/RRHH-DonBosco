<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:if test="${not empty stats}">
  <div class="row g-3">
    <div class="col-md-3"><div class="card card-metric shadow-sm"><div class="card-body">
      <div class="fs-6 text-muted">Vacantes Totales</div>
      <div class="display-6 fw-bold">${stats.totalVacantes}</div>
    </div></div></div>
    <div class="col-md-3"><div class="card card-metric shadow-sm"><div class="card-body">
      <div class="fs-6 text-muted">Postulantes Hoy</div>
      <div class="display-6 fw-bold">${stats.postulantesHoy}</div>
    </div></div></div>
    <div class="col-md-3"><div class="card card-metric shadow-sm"><div class="card-body">
      <div class="fs-6 text-muted">Contrataciones (mes)</div>
      <div class="display-6 fw-bold">${stats.contratacionesMes}</div>
    </div></div></div>
    <div class="col-md-3"><div class="card card-metric shadow-sm"><div class="card-body">
      <div class="fs-6 text-muted">Pendientes</div>
      <div class="display-6 fw-bold">${stats.pendientes}</div>
    </div></div></div>
  </div>
  <p class="text-muted mt-3">Corte: ${stats.corte}</p>
</c:if>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
