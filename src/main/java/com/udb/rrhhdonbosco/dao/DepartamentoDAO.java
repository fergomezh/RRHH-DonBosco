package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.Departamento;
import com.udb.rrhhdonbosco.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO para la entidad Departamento.
 */
public class DepartamentoDAO {

    /*
     * Retorna todos los departamentos.
     */
    public List<Departamento> listarDepartamentos() {

        List<Departamento> lista = new ArrayList<>();

        String sql = "SELECT idDepartamento, nombreDepartamento, descripcionDepartamento "
           + "FROM Departamento "
           + "ORDER BY nombreDepartamento ASC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
    System.out.println("Departamento encontrado: " + rs.getString("nombreDepartamento"));

                Departamento d = new Departamento(
                        rs.getInt("idDepartamento"),
                        rs.getString("nombreDepartamento"),
                        rs.getString("descripcionDepartamento")
                );

                lista.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar departamentos: " + e.getMessage());
        }

        return lista;
    }

    /*
     * Obtener departamento por ID
     */
    public Departamento obtenerPorId(int id) {

        String sql = "SELECT idDepartamento, nombreDepartamento, descripcionDepartamento "
           + "FROM Departamento "
           + "WHERE idDepartamento = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Departamento(
                            rs.getInt("idDepartamento"),
                            rs.getString("nombreDepartamento"),
                            rs.getString("descripcionDepartamento")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener departamento: " + e.getMessage());
        }

        return null;
    }

    /*
     * Insertar departamento
     */
    public boolean insertarDepartamento(Departamento d) {

        String sql = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) "
           + "VALUES (?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNombreDepartamento());
            ps.setString(2, d.getDescripcionDepartamento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar departamento: " + e.getMessage());
            return false;
        }
    }

    /*
     * Actualizar departamento
     */
    public boolean actualizarDepartamento(Departamento d) {

        String sql = "UPDATE Departamento "
           + "SET nombreDepartamento = ?, descripcionDepartamento = ? "
           + "WHERE idDepartamento = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNombreDepartamento());
            ps.setString(2, d.getDescripcionDepartamento());
            ps.setInt(3, d.getIdDepartamento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento: " + e.getMessage());
            return false;
        }
    }

    /*
     * Eliminar departamento
     */
    public boolean eliminarDepartamento(int id) {

        String sql = "DELETE FROM Departamento WHERE idDepartamento = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento: " + e.getMessage());
            return false;
        }
    }
}