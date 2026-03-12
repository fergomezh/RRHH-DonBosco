package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.ContratacionDAO;
import com.udb.rrhhdonbosco.model.Contratacion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ContratacionServlet", urlPatterns = {"/contrataciones/*"})
public class ContratacionServlet extends HttpServlet {

    private List<String> puestos;
    private List<String> departamentos;
    private List<String> modalidades;
    private List<String> estados;
    private List<String> tipos;
    private ContratacionDAO contratacionDAO;

    @Override
    public void init() throws ServletException {
        puestos = Arrays.asList("Desarrollador Java", "QA Analyst", "Soporte Técnico", "DBA", "UX/UI");
        departamentos = Arrays.asList("TI", "Finanzas", "RRHH", "Operaciones", "Marketing");
        modalidades = Arrays.asList("Tiempo completo", "Medio tiempo", "Temporal", "Pasantía");
        estados = Arrays.asList("En proceso", "Entrevista", "Aprobado", "Rechazado");
        tipos = Arrays.asList("Contrato indefinido", "Contrato temporal", "Prácticas", "Consultoría");
        contratacionDAO = new ContratacionDAO();
    }

    private void exponer(HttpServletRequest req) {
        req.setAttribute("puestos", puestos);
        req.setAttribute("departamentos", departamentos);
        req.setAttribute("modalidades", modalidades);
        req.setAttribute("estados", estados);
        req.setAttribute("tipos", tipos);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo(); // /formulario, /lista, etc.

        if (path == null || "/".equals(path)) {
            resp.sendRedirect(req.getContextPath() + "/contrataciones/formulario");
            return;
        }

        switch (path) {
            case "/formulario":
                req.setAttribute("titulo", "Nueva Contratación");
                exponer(req);
                req.getRequestDispatcher("/views/contrataciones/formulario.jsp").forward(req, resp);
                break;
            case "/lista":
                req.setAttribute("titulo", "Listado de Contrataciones");
                req.getRequestDispatcher("/views/contrataciones/lista.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada: " + path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo();
        if ("/guardar".equals(path)) {
            try {
                // Obtener parámetros del formulario
                int idDepartamento = Integer.parseInt(req.getParameter("idDepartamento"));
                int idEmpleado = Integer.parseInt(req.getParameter("idEmpleado"));
                int idCargo = Integer.parseInt(req.getParameter("idCargo"));
                int idTipoContratacion = Integer.parseInt(req.getParameter("idTipoContratacion"));
                LocalDate fechaContratacion = LocalDate.parse(req.getParameter("fechaContratacion"));
                BigDecimal salario = new BigDecimal(req.getParameter("salario"));
                boolean estado = Boolean.parseBoolean(req.getParameter("estado"));

                // Crear objeto Contratacion
                Contratacion contratacion = new Contratacion(
                        0, idDepartamento, idEmpleado, idCargo, idTipoContratacion,
                        fechaContratacion, salario, estado
                );

                // Si viene un ID, es actualización
                String idParam = req.getParameter("idContratacion");
                if (idParam != null && !idParam.isEmpty()) {
                    contratacion.setIdContratacion(Integer.parseInt(idParam));
                    contratacionDAO.actualizar(contratacion);
                    resp.sendRedirect(req.getContextPath() + "/contrataciones/lista?msg=Contratación actualizada");
                } else {
                    // Es nueva contratación
                    contratacionDAO.guardar(contratacion);
                    resp.sendRedirect(req.getContextPath() + "/contrataciones/lista?msg=Contratación registrada");
                }

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Error al guardar: " + e.getMessage());
                exponer(req);
                req.getRequestDispatcher("/views/contrataciones/formulario.jsp").forward(req, resp);
            }
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta POST no soportada: " + path);
    }
}
