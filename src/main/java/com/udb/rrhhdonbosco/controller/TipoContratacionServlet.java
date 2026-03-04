package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.TipoContratacionDAO;
import com.udb.rrhhdonbosco.model.TipoContratacion;
import com.udb.rrhhdonbosco.util.MensajeRespuesta;
import com.udb.rrhhdonbosco.util.Validador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para el módulo de Tipos de Contratación.
 */
@WebServlet("/tipocontratacion")
public class TipoContratacionServlet extends HttpServlet {

    private final TipoContratacionDAO tipoDAO = new TipoContratacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":   listar(request, response);                break;
            case "nuevo":    mostrarFormulario(request, response, null); break;
            case "editar":   mostrarEdicion(request, response);        break;
            case "guardar":  guardar(request, response);               break;
            case "eliminar": eliminar(request, response);              break;
            default:         listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TipoContratacion> lista = tipoDAO.listarTipos();
        request.setAttribute("tipos", lista);
        request.setAttribute("titulo", "Tipos de Contratación");
        request.getRequestDispatcher("/views/tipocontratacion/lista.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, TipoContratacion tc)
            throws ServletException, IOException {
        request.setAttribute("tipo", tc);
        request.setAttribute("titulo", tc == null ? "Nuevo Tipo de Contratación" : "Editar Tipo de Contratación");
        request.getRequestDispatcher("/views/tipocontratacion/formulario.jsp").forward(request, response);
    }

    private void mostrarEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (!Validador.esIdValido(idParam)) {
            request.setAttribute("error", "ID inválido.");
            listar(request, response);
            return;
        }
        TipoContratacion tc = tipoDAO.obtenerPorId(Integer.parseInt(idParam));
        if (tc == null) {
            request.setAttribute("error", "Tipo de contratación no encontrado.");
            listar(request, response);
            return;
        }
        mostrarFormulario(request, response, tc);
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idTipoContratacion");
        String tipo    = request.getParameter("tipoContratacion");

        if (!Validador.esTextoValido(tipo)) {
            request.setAttribute("error", "El tipo de contratación es obligatorio.");
            mostrarFormulario(request, response, null);
            return;
        }

        TipoContratacion tc = new TipoContratacion();
        tc.setTipoContratacion(tipo.trim());

        boolean resultado;
        if (Validador.esIdValido(idParam)) {
            tc.setIdTipoContratacion(Integer.parseInt(idParam));
            resultado = tipoDAO.actualizarTipo(tc);
        } else {
            resultado = tipoDAO.insertarTipo(tc);
        }

        MensajeRespuesta msg = resultado
                ? MensajeRespuesta.exito("Tipo de contratación guardado correctamente.")
                : MensajeRespuesta.error("No se pudo guardar el tipo de contratación.");

        request.getSession().setAttribute("mensaje", msg);
        response.sendRedirect(request.getContextPath() + "/tipocontratacion?accion=listar");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        MensajeRespuesta msg;

        if (Validador.esIdValido(idParam)) {
            boolean ok = tipoDAO.eliminarTipo(Integer.parseInt(idParam));
            msg = ok
                    ? MensajeRespuesta.exito("Tipo eliminado correctamente.")
                    : MensajeRespuesta.error("No se pudo eliminar. Puede estar en uso.");
        } else {
            msg = MensajeRespuesta.error("ID inválido.");
        }

        request.getSession().setAttribute("mensaje", msg);
        response.sendRedirect(request.getContextPath() + "/tipocontratacion?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }
}
