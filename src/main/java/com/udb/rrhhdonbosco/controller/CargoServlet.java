package com.udb.rrhhdonbosco.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.udb.rrhhdonbosco.dao.CargoDAO;
import com.udb.rrhhdonbosco.model.Cargo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cargos")
@MultipartConfig
public class CargoServlet extends HttpServlet {

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
                mostrarFormulario(request, response);
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

            case "editar":
                editarCargo(request, response);
                break;

            case "eliminar":
                eliminarCargo(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                break;
        }
    }

    private void listarCargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CargoDAO dao = new CargoDAO();
        ArrayList<Cargo> cargos = dao.listaDeCargos();

        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("/views/cargos/lista.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/cargos/formulario.jsp").forward(request, response);
    }

    private void guardarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("cargoNombre");
        String descripcion = request.getParameter("cargoDescripcion");
        boolean jefatura = request.getParameter("cargoJefatura") != null;

        try {
            Cargo nuevoCargo = new Cargo(nombre, descripcion, jefatura);
            CargoDAO dao = new CargoDAO();
            dao.agregarCargo(nuevoCargo);

            response.sendRedirect(request.getContextPath() + "/cargos?accion=listar");
        } catch (Exception e) {
            request.setAttribute("ok", false);
        }
    }

    private void eliminarCargo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");

        try {
            CargoDAO dao = new CargoDAO();
            dao.eliminarCargo(Integer.parseInt(id));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error eliminando cargo: " + e.getMessage());
        }
    }

    private void editarCargo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String nombre = request.getParameter("cargoNombre");
        String descripcion = request.getParameter("cargoDescripcion");
        boolean jefatura = request.getParameter("cargoJefatura") != null;

        try {
            Cargo cargo = new Cargo();
            cargo.setIdCargo(Integer.parseInt(id));
            cargo.setCargoNombre(nombre);
            cargo.setCargoDescripcion(descripcion);
            cargo.setCargoJefatura(jefatura);

            CargoDAO dao = new CargoDAO();
            dao.editarCargo(cargo);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error editando cargo: " + e.getMessage());
        }
    }
}
