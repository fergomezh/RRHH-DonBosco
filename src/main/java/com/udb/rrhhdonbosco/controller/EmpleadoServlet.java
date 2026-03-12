package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.EmpleadoDAO;
import com.udb.rrhhdonbosco.model.Empleado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/empleados")


public class EmpleadoServlet  extends HttpServlet {

        private EmpleadoDAO dao = new EmpleadoDAO();

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<Empleado> lista = dao.findAll();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("/views/empleados/lista.jsp").forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    break;

                case "insertar":
                    dao.insert(registrarDatos(request));
                    response.sendRedirect(request.getContextPath() + "/empleados?accion=listar");
                    break;

                case "editar":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("idEmpleado", idEdit);
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    break;

                case "actualizar":
                    Empleado editado = registrarDatos(request);
                    editado.setIdEmpleado(Integer.parseInt(request.getParameter("id")));
                    dao.update(editado);
                    response.sendRedirect(request.getContextPath() + "/empleados?accion=listar");
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.delete(idEliminar);
                    response.sendRedirect(request.getContextPath() + "/empleados?accion=listar");
                    break;
            }
        }

        private Empleado registrarDatos(HttpServletRequest request) {
            Empleado e = new Empleado();
            e.setNumeroDui(request.getParameter("txtDui"));
            e.setNombrePersona(request.getParameter("txtNombre"));
            e.setUsuario(request.getParameter("txtUsuario"));
            e.setNumeroTelefono(request.getParameter("txtTelefono"));
            e.setCorreoInstitucional(request.getParameter("txtCorreo"));

            String fecha = request.getParameter("txtFecha");
            if (fecha != null && !fecha.isEmpty()) {
                e.setFechaNacimiento(LocalDate.parse(fecha));
            }
            return e;
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }


    }


