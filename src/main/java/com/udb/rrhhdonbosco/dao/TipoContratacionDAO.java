package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.TipoContratacion;
import com.udb.rrhhdonbosco.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO para la entidad TipoContratacion.
 * Consultas parametrizadas para evitar inyección SQL.
 */
public class TipoContratacionDAO {

    /*
     * Retorna todos los tipos de contratación disponibles.
     */
    public List<TipoContratacion> listarTipos() {
        List<TipoContratacion> lista = new ArrayList<>();
        String sql = "SELECT idTipoContratacion, tipoContratacion FROM TipoContratacion ORDER BY tipoContratacion ASC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new TipoContratacion(
                    rs.getInt("idTipoContratacion"),
                    rs.getString("tipoContratacion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tipos de contratación: " + e.getMessage());
        }
        return lista;
    }

    /*
     * Busca un tipo de contratación por ID.
     */
    public TipoContratacion obtenerPorId(int id) {
        String sql = "SELECT idTipoContratacion, tipoContratacion FROM TipoContratacion WHERE idTipoContratacion=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new TipoContratacion(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de contratación: " + e.getMessage());
        }
        return null;
    }

    /*
     * Inserta un nuevo tipo de contratación.
     */
    public boolean insertarTipo(TipoContratacion tc) {
        String sql = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tc.getTipoContratacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar tipo de contratación: " + e.getMessage());
            return false;
        }
    }

    /*
     * Actualiza un tipo de contratación existente.
     */
    public boolean actualizarTipo(TipoContratacion tc) {
        String sql = "UPDATE TipoContratacion SET tipoContratacion=? WHERE idTipoContratacion=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tc.getTipoContratacion());
            ps.setInt(2, tc.getIdTipoContratacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo de contratación: " + e.getMessage());
            return false;
        }
    }

    /*
     * Elimina un tipo de contratación por ID.
     */
    public boolean eliminarTipo(int id) {
        String sql = "DELETE FROM TipoContratacion WHERE idTipoContratacion=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo de contratación: " + e.getMessage());
            return false;
        }
    }
}
