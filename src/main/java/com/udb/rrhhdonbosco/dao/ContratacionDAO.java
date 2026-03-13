package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.Contratacion;
import com.udb.rrhhdonbosco.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContratacionDAO {

    // SQL Queries
    private static final String INSERT_SQL = 
        "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, " +
        "fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SELECT_BY_ID_SQL = 
        "SELECT * FROM Contrataciones WHERE idContratacion = ?";
    
    private static final String SELECT_ALL_SQL = 
        "SELECT * FROM Contrataciones ORDER BY fechaContratacion DESC";
    
    private static final String UPDATE_SQL = 
        "UPDATE Contrataciones SET idDepartamento = ?, idEmpleado = ?, idCargo = ?, " +
        "idTipoContratacion = ?, fechaContratacion = ?, salario = ?, estado = ? " +
        "WHERE idContratacion = ?";
    
    private static final String DELETE_SQL = 
        "DELETE FROM Contrataciones WHERE idContratacion = ?";

    /**
     * Guarda una nueva contratación en la base de datos
     */
    public void guardar(Contratacion contratacion) throws SQLException {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, contratacion.getIdDepartamento());
            ps.setInt(2, contratacion.getIdEmpleado());
            ps.setInt(3, contratacion.getIdCargo());
            ps.setInt(4, contratacion.getIdTipoContratacion());
            ps.setDate(5, Date.valueOf(contratacion.getFechaContratacion()));
            ps.setBigDecimal(6, contratacion.getSalario());
            ps.setBoolean(7, contratacion.getEstado());
            
            ps.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contratacion.setIdContratacion(rs.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza una contratación existente
     */
    public void actualizar(Contratacion contratacion) throws SQLException {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            
            ps.setInt(1, contratacion.getIdDepartamento());
            ps.setInt(2, contratacion.getIdEmpleado());
            ps.setInt(3, contratacion.getIdCargo());
            ps.setInt(4, contratacion.getIdTipoContratacion());
            ps.setDate(5, Date.valueOf(contratacion.getFechaContratacion()));
            ps.setBigDecimal(6, contratacion.getSalario());
            ps.setBoolean(7, contratacion.getEstado());
            ps.setInt(8, contratacion.getIdContratacion());
            
            ps.executeUpdate();
        }
    }

    /**
     * Elimina una contratación por su ID
     */
    public void eliminar(int idContratacion) throws SQLException {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idContratacion);
            ps.executeUpdate();
        }
    }

    /**
     * Busca una contratación por su ID
     */
    public Contratacion buscarPorId(int idContratacion) throws SQLException {
        Contratacion contratacion = null;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, idContratacion);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contratacion = mapearContratacion(rs);
                }
            }
        }
        
        return contratacion;
    }

    /**
     * Lista todas las contrataciones
     */
    public List<Contratacion> listarTodos() throws SQLException {
        List<Contratacion> lista = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapearContratacion(rs));
            }
        }
        
        return lista;
    }

    /*
     * Lista todas las contrataciones con nombres (JOIN)
     */
    public List<Map<String, Object>> listarConDetalles() throws SQLException {
        String sql =
            "SELECT c.idContratacion, " +
            "       e.nombrePersona, " +
            "       d.nombreDepartamento, " +
            "       ca.cargo AS nombreCargo, " +
            "       tc.tipoContratacion AS nombreTipoContratacion, " +
            "       c.fechaContratacion, c.salario, c.estado " +
            "FROM Contrataciones c " +
            "JOIN empleados        e  ON c.idEmpleado        = e.idEmpleado " +
            "JOIN Departamento     d  ON c.idDepartamento     = d.idDepartamento " +
            "JOIN Cargos           ca ON c.idCargo            = ca.idCargo " +
            "JOIN TipoContratacion tc ON c.idTipoContratacion = tc.idTipoContratacion " +
            "ORDER BY c.fechaContratacion DESC";

        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("idContratacion", rs.getInt("idContratacion"));
                fila.put("nombreEmpleado", rs.getString("nombrePersona"));
                fila.put("nombreDepartamento", rs.getString("nombreDepartamento"));
                fila.put("nombreCargo", rs.getString("nombreCargo"));
                fila.put("nombreTipoContratacion", rs.getString("nombreTipoContratacion"));
                fila.put("fechaContratacion", rs.getDate("fechaContratacion").toLocalDate());
                fila.put("salario", rs.getBigDecimal("salario"));
                fila.put("estado", rs.getBoolean("estado"));
                lista.add(fila);
            }
        }

        return lista;
    }

    /*
     * Método auxiliar para mapear ResultSet a objeto Contratacion
     */
    private Contratacion mapearContratacion(ResultSet rs) throws SQLException {
        Contratacion c = new Contratacion();
        c.setIdContratacion(rs.getInt("idContratacion"));
        c.setIdDepartamento(rs.getInt("idDepartamento"));
        c.setIdEmpleado(rs.getInt("idEmpleado"));
        c.setIdCargo(rs.getInt("idCargo"));
        c.setIdTipoContratacion(rs.getInt("idTipoContratacion"));
        c.setFechaContratacion(rs.getDate("fechaContratacion").toLocalDate());
        c.setSalario(rs.getBigDecimal("salario"));
        c.setEstado(rs.getBoolean("estado"));
        return c;
    }
}