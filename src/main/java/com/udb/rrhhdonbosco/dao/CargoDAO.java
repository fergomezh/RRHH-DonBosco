package com.udb.rrhhdonbosco.dao;

import com.udb.rrhhdonbosco.model.Cargo;
import com.udb.rrhhdonbosco.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;

public class CargoDAO {

    public int agregarCargo(Cargo cargo) {
        String sql = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cargo.getCargoNombre());
            ps.setString(2, cargo.getCargoDescripcion());
            ps.setBoolean(3, cargo.getCargoJefatura());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int editarCargo(Cargo cargo) {
    String sql = "UPDATE Cargos SET cargo = ?, descripcionCargo = ?, jefatura = ? WHERE idCargo = ?";

    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, cargo.getCargoNombre());
        ps.setString(2, cargo.getCargoDescripcion());
        ps.setBoolean(3, cargo.getCargoJefatura());
        ps.setInt(4, cargo.getIdCargo());

        return ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error editando cargo", e);
    }
}

    public ArrayList<Cargo> listaDeCargos() {
        String sql = "SELECT * FROM Cargos ORDER BY idCargo DESC";
        ArrayList<Cargo> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("idCargo"));
                c.setCargoNombre(rs.getString("cargo"));
                c.setCargoDescripcion(rs.getString("descripcionCargo"));
                c.setCargoJefatura(rs.getBoolean("jefatura"));

                lista.add(c);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Error listando cargos", e);
        }
    }
    
    public Cargo obtenerPorId(int idCargo) {
        String sql = "SELECT * FROM Cargos WHERE idCargo = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCargo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo c = new Cargo();
                    c.setIdCargo(rs.getInt("idCargo"));
                    c.setCargoNombre(rs.getString("cargo"));
                    c.setCargoDescripcion(rs.getString("descripcionCargo"));
                    c.setCargoJefatura(rs.getBoolean("jefatura"));
                    return c;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo cargo por id", e);
        }

        return null;
    }

    public int eliminarCargo(int idCargo) {
    String sql = "DELETE FROM Cargos WHERE idCargo = ?";

    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idCargo);

        return ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error eliminando cargo", e);
    }
}
}
