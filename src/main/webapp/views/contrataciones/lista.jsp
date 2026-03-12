<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Contrataciones</li>
        </ol>
    </nav>

    <a href="${pageContext.request.contextPath}/contrataciones/formulario" class="btn btn-primary">
        <i class="fas fa-plus me-2"></i>Ingresar Contratación
    </a>
</div>
        
<div class="card table-card">

    <div class="card-header d-flex justify-content-between align-items-center">

        <h6 class="mb-0">
            <i class="fas fa-file-contract me-2"></i>Lista de Contrataciones
        </h6>

        <span class="badge bg-light text-dark">
            ${empty contrataciones ? 0 : contrataciones.size()} registros
        </span>

    </div>

    <div class="card-body p-0">

        <c:choose>

            <c:when test="${empty contrataciones}">
                <div class="text-center py-5 text-muted">
                    <i class="fas fa-file-contract fa-3x mb-3 opacity-25"></i>
                    <p>No hay contrataciones registradas.</p>
                </div>
            </c:when>

            <c:otherwise>

                <table class="table table-hover mb-0">

                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Empleado</th>
                        <th>Departamento</th>
                        <th>Cargo</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Salario</th>
                        <th>Estado</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="c" items="${contrataciones}" varStatus="st">

                        <tr>

                            <td class="text-muted small">${st.count}</td>

                            <td>${c.empleado}</td>

                            <td>${c.departamento}</td>

                            <td>${c.cargo}</td>

                            <td>${c.tipoContratacion}</td>

                            <td>${c.fechaContratacion}</td>

                            <td>$ ${c.salario}</td>

                            <td>
                                <c:choose>

                                    <c:when test="${c.estado}">
                                        <span class="badge bg-success">Activo</span>
                                    </c:when>

                                    <c:otherwise>
                                        <span class="badge bg-danger">Inactivo</span>
                                    </c:otherwise>

                                </c:choose>
                            </td>

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/contrataciones/editar?id=${c.idContratacion}"
                                   class="btn btn-sm btn-warning me-1">
                                    <i class="fas fa-edit"></i>
                                </a>

                                <a href="${pageContext.request.contextPath}/contrataciones/eliminar?id=${c.idContratacion}"
                                   class="btn btn-sm btn-danger confirmar-eliminar">
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
