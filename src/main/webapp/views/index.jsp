<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="row g-3">

    <div class="col-md-4 col-sm-6">
        <div class="card stat-card h-100" style="background: linear-gradient(135deg, #1a3a5c, #2e86c1);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <div class="fs-6 opacity-75">Empleados</div>
                    <div class="number">${totalEmpleados}</div>
                </div>
                <i class="fas fa-users icon"></i>
            </div>
            <div class="card-footer bg-transparent border-0 pt-0">
                <a href="${pageContext.request.contextPath}/empleados?accion=listar"
                   class="text-white opacity-75 small text-decoration-none">
                    Ver todos <i class="fas fa-arrow-right ms-1"></i>
                </a>
            </div>
        </div>
    </div>

    <div class="col-md-4 col-sm-6">
        <div class="card stat-card h-100" style="background: linear-gradient(135deg, #117a65, #1abc9c);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <div class="fs-6 opacity-75">Departamentos</div>
                    <div class="number">${totalDepartamentos}</div>
                </div>
                <i class="fas fa-building icon"></i>
            </div>
            <div class="card-footer bg-transparent border-0 pt-0">
                <a href="${pageContext.request.contextPath}/departamento?accion=listar"
                   class="text-white opacity-75 small text-decoration-none">
                    Ver todos <i class="fas fa-arrow-right ms-1"></i>
                </a>
            </div>
        </div>
    </div>

    <div class="col-md-4 col-sm-6">
        <div class="card stat-card h-100" style="background: linear-gradient(135deg, #784212, #e67e22);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <div class="fs-6 opacity-75">Cargos</div>
                    <div class="number">${totalCargos}</div>
                </div>
                <i class="fas fa-briefcase icon"></i>
            </div>
            <div class="card-footer bg-transparent border-0 pt-0">
                <a href="${pageContext.request.contextPath}/cargos?accion=listar"
                   class="text-white opacity-75 small text-decoration-none">
                    Ver todos <i class="fas fa-arrow-right ms-1"></i>
                </a>
            </div>
        </div>
    </div>

    <div class="col-md-6 col-sm-6">
        <div class="card stat-card h-100" style="background: linear-gradient(135deg, #512e5f, #8e44ad);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <div class="fs-6 opacity-75">Tipos de Contratación</div>
                    <div class="number">${totalTiposContratacion}</div>
                </div>
                <i class="fas fa-file-alt icon"></i>
            </div>
            <div class="card-footer bg-transparent border-0 pt-0">
                <a href="${pageContext.request.contextPath}/tipocontratacion?accion=listar"
                   class="text-white opacity-75 small text-decoration-none">
                    Ver todos <i class="fas fa-arrow-right ms-1"></i>
                </a>
            </div>
        </div>
    </div>

    <div class="col-md-6 col-sm-6">
        <div class="card stat-card h-100" style="background: linear-gradient(135deg, #1a5276, #2980b9);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <div class="fs-6 opacity-75">Contrataciones</div>
                    <div class="number">${totalContrataciones}</div>
                </div>
                <i class="fas fa-handshake icon"></i>
            </div>
            <div class="card-footer bg-transparent border-0 pt-0">
                <a href="${pageContext.request.contextPath}/contrataciones?accion=listar"
                   class="text-white opacity-75 small text-decoration-none">
                    Ver todos <i class="fas fa-arrow-right ms-1"></i>
                </a>
            </div>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
