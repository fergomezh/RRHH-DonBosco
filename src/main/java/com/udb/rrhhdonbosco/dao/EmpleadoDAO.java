package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.Empleado;
import com.udb.rrhhdonbosco.util.ConexionDB;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public boolean insert (Empleado empleado){
        String sql="INSERT INTO empleados (numeroDui, nombrePersona,usuario, numeroTelefono," +
                "correoInstitucional, fechaNacimiento) values (?,?,?,?,?,?)";

        try(Connection con = ConexionDB.getConnection();
        PreparedStatement ps= con.prepareStatement(sql)){
            ps.setString(1,empleado.getNumeroDui());
            ps.setString(2,empleado.getNombrePersona());
            ps.setString(3,empleado.getUsuario());
            ps.setString(4,empleado.getNumeroTelefono());
            ps.setString(5,empleado.getCorreoInstitucional());

            //para manejar LocalDate
            if (empleado.getFechaNacimiento()!=null){
                ps.setDate(6,java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            }else {
                ps.setNull(6,java.sql.Types.DATE);
            }
            int filasAfectadas=ps.executeUpdate();
            return filasAfectadas>0;

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al insertar " +
                    "el empleado"+ e.getMessage());
            return false;

        }
    }

    public boolean update (Empleado empleado){
        String sql="UPDATE empleados SET numeroDui=?, nombrePersona=?,usuario=?, numeroTelefono=?," +
                "correoInstitucional=?, fechaNacimiento=? WHERE idEmpleado=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){

            ps.setString(1,empleado.getNumeroDui());
            ps.setString(2,empleado.getNombrePersona());
            ps.setString(3,empleado.getUsuario());
            ps.setString(4,empleado.getNumeroTelefono());
            ps.setString(5,empleado.getCorreoInstitucional());

            //para manejar LocalDate
            if (empleado.getFechaNacimiento()!=null){
                ps.setDate(6,java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            }else {
                ps.setNull(6,java.sql.Types.DATE);
            }

            ps.setInt(7, empleado.getIdEmpleado());

            int filasAfectadas=ps.executeUpdate();
            return filasAfectadas>0;

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al actualizar "
                    + e.getMessage());
            return false;

        }
    }

    public boolean delete (int idEmpleado){
        String sql="DELETE FROM empleados WHERE idEmpleado=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){

            ps.setInt(1, idEmpleado);

            int filasAfectadas=ps.executeUpdate();
            return filasAfectadas>0;

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al eliminar empleado "
                    + e.getMessage());
            return false;

        }

    }

    public List <Empleado> findAll(){
        List<Empleado> lista=new ArrayList<>();
        String sql="SELECT * FROM empleados";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps= con.prepareStatement(sql);

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
                JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
            }

            return lista;





    }




}
