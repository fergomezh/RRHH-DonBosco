package com.udb.rrhhdonbosco.controller;

import com.udb.rrhhdonbosco.dao.EmpleadoDAO;
import com.udb.rrhhdonbosco.model.Empleado;
import com.udb.rrhhdonbosco.util.Validador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO dao = new EmpleadoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {

            case "listar":
                List<Empleado> lista = dao.findAll();
                request.setAttribute("lista", lista);
                request.setAttribute("titulo", "Empleados");
                request.getRequestDispatcher("/views/empleados/lista.jsp").forward(request, response);
                break;

            case "nuevo":
                request.setAttribute("empleado", new Empleado());
                request.setAttribute("titulo", "Nuevo Empleado");
                request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                break;

            case "insertar":

                Empleado nuevo = registrarDatos(request);

                if (!Validador.esDuiValido(nuevo.getNumeroDui())) {
                    request.setAttribute("error", "DUI inválido. Formato: ########-#");
                    request.setAttribute("empleado", nuevo);
                    request.setAttribute("titulo", "Nuevo Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                if (!Validador.esTextoValido(nuevo.getNombrePersona())) {
                    request.setAttribute("error", "El nombre es obligatorio.");
                    request.setAttribute("empleado", nuevo);
                    request.setAttribute("titulo", "Nuevo Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                if (!Validador.esTextoValido(nuevo.getUsuario())) {
                    request.setAttribute("error", "El usuario es obligatorio.");
                    request.setAttribute("empleado", nuevo);
                    request.setAttribute("titulo", "Nuevo Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                boolean insertado = dao.insert(nuevo);

                if (insertado) {

                    response.sendRedirect(request.getContextPath() + "/empleados?accion=listar");

                } else {

                    request.setAttribute("error", "El DUI ya está registrado.");
                    request.setAttribute("empleado", nuevo);
                    request.setAttribute("titulo", "Nuevo Empleado");

                    request.getRequestDispatcher("/views/empleados/formulario.jsp")
                            .forward(request, response);
                }
                break;

            case "editar":

                int idEdit = Integer.parseInt(request.getParameter("id"));
                Empleado emp = dao.findById(idEdit);

                request.setAttribute("empleado", emp);
                request.setAttribute("titulo", "Editar Empleado");
                request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                break;

            case "actualizar":

                Empleado editado = registrarDatos(request);
                editado.setIdEmpleado(Integer.parseInt(request.getParameter("id")));

                if (!Validador.esDuiValido(editado.getNumeroDui())) {
                    request.setAttribute("error", "DUI inválido. Formato: ########-#");
                    request.setAttribute("empleado", editado);
                    request.setAttribute("titulo", "Editar Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                if (!Validador.esTextoValido(editado.getNombrePersona())) {
                    request.setAttribute("error", "El nombre es obligatorio.");
                    request.setAttribute("empleado", editado);
                    request.setAttribute("titulo", "Editar Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                if (!Validador.esTextoValido(editado.getUsuario())) {
                    request.setAttribute("error", "El usuario es obligatorio.");
                    request.setAttribute("empleado", editado);
                    request.setAttribute("titulo", "Editar Empleado");
                    request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
                    return;
                }

                try {

                    dao.update(editado);
                    response.sendRedirect(request.getContextPath() + "/empleados?accion=listar");

                } catch (Exception e) {

                    request.setAttribute("error", "El DUI ya está registrado.");
                    request.setAttribute("empleado", editado);
                    request.setAttribute("titulo", "Editar Empleado");

                    request.getRequestDispatcher("/views/empleados/formulario.jsp")
                            .forward(request, response);
                }
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