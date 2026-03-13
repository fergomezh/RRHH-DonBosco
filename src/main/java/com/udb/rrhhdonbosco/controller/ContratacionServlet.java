package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.CargoDAO;
import com.udb.rrhhdonbosco.dao.ContratacionDAO;
import com.udb.rrhhdonbosco.dao.DepartamentoDAO;
import com.udb.rrhhdonbosco.dao.EmpleadoDAO;
import com.udb.rrhhdonbosco.dao.TipoContratacionDAO;
import com.udb.rrhhdonbosco.model.Contratacion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/contrataciones")
public class ContratacionServlet extends HttpServlet {

    private final ContratacionDAO    contratacionDAO    = new ContratacionDAO();
    private final EmpleadoDAO        empleadoDAO        = new EmpleadoDAO();
    private final DepartamentoDAO    departamentoDAO    = new DepartamentoDAO();
    private final CargoDAO           cargoDAO           = new CargoDAO();
    private final TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();

    private String accion(HttpServletRequest req) {
        String a = req.getParameter("accion");
        return (a == null) ? "listar" : a;
    }

    private void exponerCatalogos(HttpServletRequest req) {
        req.setAttribute("empleados", empleadoDAO.findAll());
        req.setAttribute("departamentos", departamentoDAO.listarDepartamentos());
        req.setAttribute("cargos", cargoDAO.listaDeCargos());
        req.setAttribute("tipos", tipoContratacionDAO.listarTipos());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        switch (accion(req)) {
            case "listar":  listar(req, resp);        break;
            case "nuevo":   mostrarFormulario(req, resp, null); break;
            case "editar":  mostrarEdicion(req, resp); break;
            case "eliminar": eliminar(req, resp);      break;
            default: resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        switch (accion(req)) {
            case "guardar": guardar(req, resp); break;
            default: resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setAttribute("contrataciones", contratacionDAO.listarConDetalles());
        } catch (Exception e) {
            req.setAttribute("error", "Error al cargar contrataciones: " + e.getMessage());
        }

        req.setAttribute("titulo", "Contrataciones");
        req.getRequestDispatcher("/views/contrataciones/lista.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp, Contratacion contratacion)
            throws ServletException, IOException {

        exponerCatalogos(req);
        req.setAttribute("contratacion", contratacion == null ? new Contratacion() : contratacion);
        req.setAttribute("titulo", contratacion == null ? "Nueva Contratación" : "Editar Contratación");
        req.getRequestDispatcher("/views/contrataciones/formulario.jsp").forward(req, resp);
    }

    private void mostrarEdicion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        try {
            Contratacion contratacion = contratacionDAO.buscarPorId(Integer.parseInt(idParam));

            if (contratacion == null) {
                req.setAttribute("error", "Contratación no encontrada.");
                listar(req, resp);
                return;
            }

            mostrarFormulario(req, resp, contratacion);

        } catch (Exception e) {
            req.setAttribute("error", "ID inválido.");
            listar(req, resp);
        }
    }

    private void guardar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {
            String idParam = req.getParameter("idContratacion");
            int idDepartamento = Integer.parseInt(req.getParameter("idDepartamento"));
            int idEmpleado = Integer.parseInt(req.getParameter("idEmpleado"));
            int idCargo = Integer.parseInt(req.getParameter("idCargo"));
            int idTipoContratacion = Integer.parseInt(req.getParameter("idTipoContratacion"));
            LocalDate fecha = LocalDate.parse(req.getParameter("fechaContratacion"));
            BigDecimal salario = new BigDecimal(req.getParameter("salario"));
            boolean estado = req.getParameter("estado") != null;

            Contratacion c = new Contratacion(0, idDepartamento, idEmpleado, idCargo,
                    idTipoContratacion, fecha, salario, estado);

            if (idParam != null && !idParam.isEmpty()) {
                c.setIdContratacion(Integer.parseInt(idParam));
                contratacionDAO.actualizar(c);
            } else {
                contratacionDAO.guardar(c);
            }

            resp.sendRedirect(req.getContextPath() + "/contrataciones?accion=listar");

        } catch (Exception e) {
            req.setAttribute("error", "Error al guardar: " + e.getMessage());
            exponerCatalogos(req);
            req.getRequestDispatcher("/views/contrataciones/formulario.jsp").forward(req, resp);
        }
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idParam = req.getParameter("id");

        try {
            contratacionDAO.eliminar(Integer.parseInt(idParam));
        } catch (Exception e) {
            // continúa al redirect aunque falle
        }

        resp.sendRedirect(req.getContextPath() + "/contrataciones?accion=listar");
    }
}
