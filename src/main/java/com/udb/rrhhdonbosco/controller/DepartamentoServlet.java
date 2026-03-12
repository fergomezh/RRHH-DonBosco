package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.DepartamentoDAO;
import com.udb.rrhhdonbosco.model.Departamento;
import com.udb.rrhhdonbosco.util.MensajeRespuesta;
import com.udb.rrhhdonbosco.util.Validador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para el módulo de Departamentos.
 */
@WebServlet("/departamento")
public class DepartamentoServlet extends HttpServlet {

    private final DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar": listar(request, response); break;
            case "nuevo": mostrarFormulario(request, response, null); break;
            case "editar": mostrarEdicion(request, response); break;
            case "guardar": guardar(request, response); break;
            case "eliminar": eliminar(request, response); break;
            default: listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ENTRO AL METODO LISTAR DEL SERVLET");
        List<Departamento> lista = departamentoDAO.listarDepartamentos();

        request.setAttribute("departamentos", lista);
        request.setAttribute("titulo", "Departamentos");

        request.getRequestDispatcher("/views/departamentos/lista.jsp")
                .forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Departamento d)
            throws ServletException, IOException {

        request.setAttribute("departamento", d);

        request.setAttribute("titulo",
                d == null ? "Nuevo Departamento" : "Editar Departamento");

        request.getRequestDispatcher("/views/departamentos/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (!Validador.esIdValido(idParam)) {

            request.setAttribute("error", "ID inválido.");
            listar(request, response);
            return;
        }

        Departamento d = departamentoDAO.obtenerPorId(Integer.parseInt(idParam));

        if (d == null) {

            request.setAttribute("error", "Departamento no encontrado.");
            listar(request, response);
            return;
        }

        mostrarFormulario(request, response, d);
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idDepartamento");
        String nombre = request.getParameter("nombreDepartamento");
        String descripcion = request.getParameter("descripcionDepartamento");

        if (!Validador.esTextoValido(nombre)) {

            request.setAttribute("error", "El nombre del departamento es obligatorio.");
            mostrarFormulario(request, response, null);
            return;
        }

        Departamento d = new Departamento();
        d.setNombreDepartamento(nombre.trim());
        d.setDescripcionDepartamento(descripcion);

        boolean resultado;

        if (Validador.esIdValido(idParam)) {

            d.setIdDepartamento(Integer.parseInt(idParam));
            resultado = departamentoDAO.actualizarDepartamento(d);

        } else {

            resultado = departamentoDAO.insertarDepartamento(d);
        }

        MensajeRespuesta msg = resultado
                ? MensajeRespuesta.exito("Departamento guardado correctamente.")
                : MensajeRespuesta.error("No se pudo guardar el departamento.");

        request.getSession().setAttribute("mensaje", msg);

        response.sendRedirect(request.getContextPath() + "/departamento?accion=listar");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");
        MensajeRespuesta msg;

        if (Validador.esIdValido(idParam)) {

            boolean ok = departamentoDAO.eliminarDepartamento(Integer.parseInt(idParam));

            msg = ok
                    ? MensajeRespuesta.exito("Departamento eliminado correctamente.")
                    : MensajeRespuesta.error("No se pudo eliminar el departamento.");

        } else {

            msg = MensajeRespuesta.error("ID inválido.");
        }

        request.getSession().setAttribute("mensaje", msg);

        response.sendRedirect(request.getContextPath() + "/departamento?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        processRequest(req, res);
    }
}