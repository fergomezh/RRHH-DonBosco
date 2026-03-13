package com.udb.rrhhdonbosco.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.udb.rrhhdonbosco.dao.CargoDAO;
import com.udb.rrhhdonbosco.model.Cargo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cargos")
public class CargoServlet extends HttpServlet {

    private final CargoDAO dao = new CargoDAO();

    private String accion(HttpServletRequest request) {
        String a = request.getParameter("accion");
        return (a == null) ? "listar" : a;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (accion(request)) {
            case "listar":
                listarCargos(request, response);
                break;
            case "nuevo":
                mostrarFormulario(request, response, null);
                break;
            case "editar":
                mostrarEdicion(request, response);
                break;
            case "eliminar":
                eliminarCargo(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (accion(request)) {
            case "guardar":
                guardarCargo(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                break;
        }
    }

    private void listarCargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Cargo> cargos = dao.listaDeCargos();
        request.setAttribute("cargos", cargos);
        request.setAttribute("titulo", "Cargos");

        request.getRequestDispatcher("/views/cargos/lista.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Cargo cargo)
            throws ServletException, IOException {

        request.setAttribute("cargo", cargo == null ? new Cargo() : cargo);
        request.setAttribute("titulo", cargo == null ? "Nuevo Cargo" : "Editar Cargo");
        request.getRequestDispatcher("/views/cargos/formulario.jsp").forward(request, response);
    }

    private void mostrarEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        try {
            Cargo cargo = dao.obtenerPorId(Integer.parseInt(idParam));

            if (cargo == null) {
                request.setAttribute("error", "Cargo no encontrado.");
                listarCargos(request, response);
                return;
            }

            mostrarFormulario(request, response, cargo);

        } catch (Exception e) {
            request.setAttribute("error", "ID inválido.");
            listarCargos(request, response);
        }
    }

    private void guardarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String nombre = request.getParameter("cargoNombre");
        String descripcion = request.getParameter("cargoDescripcion");
        boolean jefatura = request.getParameter("cargoJefatura") != null;

        Cargo cargo = new Cargo(nombre, descripcion, jefatura);

        try {
            if (idParam != null && !idParam.isEmpty() && !idParam.equals("0")) {
                cargo.setIdCargo(Integer.parseInt(idParam));
                dao.editarCargo(cargo);
            } else {
                dao.agregarCargo(cargo);
            }
        } catch (Exception e) {
            request.setAttribute("error", "No se pudo guardar el cargo.");
            mostrarFormulario(request, response, cargo);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/cargos?accion=listar");
    }

    private void eliminarCargo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");

        try {
            dao.eliminarCargo(Integer.parseInt(id));
        } catch (Exception e) {
            // continúa al redirect aunque falle
        }
        response.sendRedirect(request.getContextPath() + "/cargos?accion=listar");
    }
}
