package com.udb.rrhhdonbosco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ContratacionServlet", urlPatterns = {"/contrataciones/*"})
public class ContratacionServlet extends HttpServlet {

  private List<String> puestos;
  private List<String> departamentos;
  private List<String> modalidades;
  private List<String> estados;

  @Override
  public void init() throws ServletException {
    puestos       = Arrays.asList("Desarrollador Java", "QA Analyst", "Soporte Técnico", "DBA", "UX/UI");
    departamentos = Arrays.asList("TI", "Finanzas", "RRHH", "Operaciones", "Marketing");
    modalidades   = Arrays.asList("Tiempo completo", "Medio tiempo", "Temporal", "Pasantía");
    estados       = Arrays.asList("En proceso", "Entrevista", "Aprobado", "Rechazado");
  }

  private void exponer(HttpServletRequest req) {
    req.setAttribute("puestos", puestos);
    req.setAttribute("departamentos", departamentos);
    req.setAttribute("modalidades", modalidades);
    req.setAttribute("estados", estados);
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
      String puesto = req.getParameter("puesto");
      String departamento = req.getParameter("departamento");
      String modalidad = req.getParameter("modalidad");
      String estado = req.getParameter("estado");

      if (puesto == null || puesto.isBlank()
          || departamento == null || departamento.isBlank()
          || modalidad == null || modalidad.isBlank()
          || estado == null || estado.isBlank()) {
        req.setAttribute("titulo", "Nueva Contratación");
        req.setAttribute("error", "Faltan campos obligatorios");
        exponer(req);
        req.getRequestDispatcher("/views/contrataciones/formulario.jsp").forward(req, resp);
        return;
      }

      // TODO: Guardar en BD con tu DAO
      resp.sendRedirect(req.getContextPath() + "/contrataciones/formulario?msg=Contratación registrada");
      return;
    }

    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta POST no soportada: " + path);
  }
}
