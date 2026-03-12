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

<div class="d-flex justify-content-between align-items-center mb-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Departamentos</li>
        </ol>
    </nav>


<a href="${pageContext.request.contextPath}/departamento?accion=nuevo"
   class="btn btn-primary">
    <i class="fas fa-plus me-2"></i>Nuevo Departamento
</a>


</div>

<div class="card table-card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h6 class="mb-0">
            <i class="fas fa-building me-2"></i>Departamentos
        </h6>


    <span class="badge bg-light text-dark">
        ${departamentos.size()} registros
    </span>
</div>

<div class="card-body p-0">

    <c:choose>

        <c:when test="${empty departamentos}">
            <div class="text-center py-5 text-muted">
                <i class="fas fa-building fa-3x mb-3 opacity-25"></i>
                <p>No hay departamentos registrados.</p>
            </div>
        </c:when>

        <c:otherwise>

            <table class="table table-hover mb-0">

                <thead>
                    <tr>
                        <th>#</th>
                        <th>Departamento</th>
                        <th>Descripción</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="d" items="${departamentos}" varStatus="st">

                        <tr>

                            <td class="text-muted small">
                                ${st.count}
                            </td>

                            <td>
                                <i class="fas fa-building me-2 text-primary"></i>
                                ${d.nombreDepartamento}
                            </td>

                            <td>
                                ${d.descripcionDepartamento}
                            </td>

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/departamento?accion=editar&id=${d.idDepartamento}"
                                   class="btn btn-sm btn-warning btn-action me-1">
                                    <i class="fas fa-edit"></i>
                                </a>

                                <a href="${pageContext.request.contextPath}/departamento?accion=eliminar&id=${d.idDepartamento}"
                                   class="btn btn-sm btn-danger btn-action confirmar-eliminar"
                                   data-nombre="${d.nombreDepartamento}">
                                    <i class="fas fa-trash"></i>
                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>


</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
