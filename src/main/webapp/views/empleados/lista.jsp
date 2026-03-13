<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Empleados</li>
        </ol>
    </nav>

    <a href="${pageContext.request.contextPath}/empleados?accion=nuevo"
       class="btn btn-primary">
        <i class="fas fa-plus me-2"></i>Nuevo Empleado
    </a>

</div>

<div class="card table-card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h6 class="mb-0">
            <i class="fas fa-users me-2"></i>Empleados
        </h6>

    <span class="badge bg-light text-dark">
        ${lista.size()} registros
    </span>
</div>

<div class="card-body p-0">

    <c:choose>

        <c:when test="${empty lista}">
            <div class="text-center py-5 text-muted">
                <i class="fas fa-users fa-3x mb-3 opacity-25"></i>
                <p>No hay empleados registrados.</p>
            </div>
        </c:when>

        <c:otherwise>

            <table class="table table-hover mb-0">

                <thead>
                    <tr>
                        <th>#</th>
                        <th>DUI</th>
                        <th>Nombre</th>
                        <th>Usuario</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="emp" items="${lista}" varStatus="st">

                        <tr>

                            <td class="text-muted small">
                                ${st.count}
                            </td>

                            <td>
                                <i class="fas fa-id-card me-2 text-primary"></i>
                                ${emp.numeroDui}
                            </td>

                            <td>
                                ${emp.nombrePersona}
                            </td>

                            <td>
                                <span class="badge bg-info text-dark">@${emp.usuario}</span>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty emp.numeroTelefono}">
                                        <i class="fas fa-phone me-1 text-muted"></i>${emp.numeroTelefono}
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">—</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty emp.correoInstitucional}">
                                        <i class="fas fa-envelope me-1 text-muted"></i>${emp.correoInstitucional}
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">—</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/empleados?accion=editar&id=${emp.idEmpleado}"
                                   class="btn btn-sm btn-warning btn-action me-1">
                                    <i class="fas fa-edit"></i>
                                </a>

                                <a href="${pageContext.request.contextPath}/empleados?accion=eliminar&id=${emp.idEmpleado}"
                                   class="btn btn-sm btn-danger btn-action confirmar-eliminar"
                                   data-nombre="${emp.nombrePersona}">
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
