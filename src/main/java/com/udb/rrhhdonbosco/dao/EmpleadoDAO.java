package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.Empleado;
import com.udb.rrhhdonbosco.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public boolean insert(Empleado empleado) {
        String sql = "INSERT INTO empleados (numeroDui, nombrePersona, usuario, numeroTelefono, " +
                "correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNumeroDui());
            ps.setString(2, empleado.getNombrePersona());
            ps.setString(3, empleado.getUsuario());
            ps.setString(4, empleado.getNumeroTelefono());
            ps.setString(5, empleado.getCorreoInstitucional());

            if (empleado.getFechaNacimiento() != null) {
                ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Empleado empleado) {
        String sql = "UPDATE empleados SET numeroDui=?, nombrePersona=?, usuario=?, numeroTelefono=?, " +
                "correoInstitucional=?, fechaNacimiento=? WHERE idEmpleado=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNumeroDui());
            ps.setString(2, empleado.getNombrePersona());
            ps.setString(3, empleado.getUsuario());
            ps.setString(4, empleado.getNumeroTelefono());
            ps.setString(5, empleado.getCorreoInstitucional());

            if (empleado.getFechaNacimiento() != null) {
                ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.setInt(7, empleado.getIdEmpleado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idEmpleado) {
        String sql = "DELETE FROM empleados WHERE idEmpleado=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setNumeroDui(rs.getString("numeroDui"));
                empleado.setNombrePersona(rs.getString("nombrePersona"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setNumeroTelefono(rs.getString("numeroTelefono"));
                empleado.setCorreoInstitucional(rs.getString("correoInstitucional"));

                Date fechaSql = rs.getDate("fechaNacimiento");
                if (fechaSql != null) {
                    empleado.setFechaNacimiento(fechaSql.toLocalDate());
                }
                lista.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Empleado findById(int id) {
        String sql = "SELECT * FROM empleados WHERE idEmpleado = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado e = new Empleado();
                    e.setIdEmpleado(rs.getInt("idEmpleado"));
                    e.setNumeroDui(rs.getString("numeroDui"));
                    e.setNombrePersona(rs.getString("nombrePersona"));
                    e.setUsuario(rs.getString("usuario"));
                    e.setNumeroTelefono(rs.getString("numeroTelefono"));
                    e.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    Date fechaSql = rs.getDate("fechaNacimiento");
                    if (fechaSql != null) e.setFechaNacimiento(fechaSql.toLocalDate());
                    return e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}