package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.CargoDAO;
import com.udb.rrhhdonbosco.dao.ContratacionDAO;
import com.udb.rrhhdonbosco.dao.DepartamentoDAO;
import com.udb.rrhhdonbosco.dao.EmpleadoDAO;
import com.udb.rrhhdonbosco.dao.TipoContratacionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    private final EmpleadoDAO         empleadoDAO         = new EmpleadoDAO();
    private final DepartamentoDAO      departamentoDAO     = new DepartamentoDAO();
    private final CargoDAO             cargoDAO            = new CargoDAO();
    private final TipoContratacionDAO  tipoContratacionDAO = new TipoContratacionDAO();
    private final ContratacionDAO      contratacionDAO     = new ContratacionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setAttribute("totalEmpleados",         empleadoDAO.findAll().size());
            req.setAttribute("totalDepartamentos",     departamentoDAO.listarDepartamentos().size());
            req.setAttribute("totalCargos",            cargoDAO.listaDeCargos().size());
            req.setAttribute("totalTiposContratacion", tipoContratacionDAO.listarTipos().size());
            req.setAttribute("totalContrataciones",    contratacionDAO.listarTodos().size());
        } catch (Exception e) {
            req.setAttribute("error", "Error al cargar estadísticas: " + e.getMessage());
        }

        req.setAttribute("titulo", "Dashboard");
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}
