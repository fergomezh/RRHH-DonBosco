package com.udb.rrhhdonbosco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalVacantes", 12);
        stats.put("postulantesHoy", 7);
        stats.put("contratacionesMes", 4);
        stats.put("pendientes", 9);
        stats.put("corte", LocalDate.now());

        req.setAttribute("titulo", "Dashboard");
        req.setAttribute("stats", stats);

        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}
