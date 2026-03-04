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

@WebServlet("/EmpleadoServlet")


public class EmpleadoServlet  extends HttpServlet {

        private EmpleadoDAO dao = new EmpleadoDAO();

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String accion = request.getParameter("accion");
            if (accion == null) accion = "Listar";

            switch (accion) {
                case "Listar":
                    List<Empleado> lista = dao.findAll();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("views/empleados/lista.jsp").forward(request, response);
                    break;

                case "Nuevo":
                    request.getRequestDispatcher("views/empleados/formulario.jsp").forward(request, response);
                    break;

                case "Insertar":
                    dao.insert(registrarDatos(request));
                    response.sendRedirect("EmpleadoServlet?accion=Listar");
                    break;

                case "Editar":
                    // 1. Obtener el ID que viene de la tabla
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    request.getRequestDispatcher("views/empleados/formulario.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    Empleado editado = registrarDatos(request);
                    editado.setIdEmpleado(Integer.parseInt(request.getParameter("id")));
                    dao.update(editado);
                    response.sendRedirect("EmpleadoServlet?accion=Listar");
                    break;

                case "Eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.delete(idEliminar);
                    response.sendRedirect("EmpleadoServlet?accion=Listar");
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


