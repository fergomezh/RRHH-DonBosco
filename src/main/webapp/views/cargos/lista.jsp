<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Cargos</li>
        </ol>
    </nav>

    <a href="${pageContext.request.contextPath}/cargos?accion=nuevo"
       class="btn btn-primary">
        <i class="fas fa-plus me-2"></i>Nuevo Cargo
    </a>

</div>

<div class="card table-card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h6 class="mb-0">
            <i class="fas fa-briefcase me-2"></i>Cargos
        </h6>

    <span class="badge bg-light text-dark">
        ${cargos.size()} registros
    </span>
</div>

<div class="card-body p-0">

    <c:choose>

        <c:when test="${empty cargos}">
            <div class="text-center py-5 text-muted">
                <i class="fas fa-briefcase fa-3x mb-3 opacity-25"></i>
                <p>No hay cargos registrados.</p>
            </div>
        </c:when>

        <c:otherwise>

            <table class="table table-hover mb-0">

                <thead>
                    <tr>
                        <th>#</th>
                        <th>Cargo</th>
                        <th>Descripción</th>
                        <th class="text-center">Jefatura</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="c" items="${cargos}" varStatus="st">

                        <tr>

                            <td class="text-muted small">
                                ${st.count}
                            </td>

                            <td>
                                <i class="fas fa-briefcase me-2 text-primary"></i>
                                ${c.cargoNombre}
                            </td>

                            <td>
                                ${c.cargoDescripcion}
                            </td>

                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${c.cargoJefatura}">
                                        <span class="badge bg-success">Sí</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">—</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/cargos?accion=editar&id=${c.idCargo}"
                                   class="btn btn-sm btn-warning btn-action me-1">
                                    <i class="fas fa-edit"></i>
                                </a>

                                <a href="${pageContext.request.contextPath}/cargos?accion=eliminar&id=${c.idCargo}"
                                   class="btn btn-sm btn-danger btn-action confirmar-eliminar"
                                   data-nombre="${c.cargoNombre}">
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
