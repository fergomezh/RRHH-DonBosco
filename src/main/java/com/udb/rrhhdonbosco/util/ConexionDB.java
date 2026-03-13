package com.udb.rrhhdonbosco.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase utilitaria para gestionar la conexión a la base de datos.
 * Utiliza JDBC para conectarse a MySQL con los parámetros configurados.
 */
public class ConexionDB {

    /* URL de conexión a la base de datos MySQL */
    private static final String URL      = "jdbc:mysql://localhost:3306/db_rrhh_donbosco?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    /* Usuario de la base de datos */
    private static final String USUARIO  = "root";
    /* Contraseña de la base de datos */
    private static final String PASSWORD = "verystrongpassword12";

    private ConexionDB() {}

    /*
     * Obtiene una conexión activa a la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Carga el driver de MySQL (necesario en algunos entornos)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /*
     * Cierra una conexión de forma segura, evitando NullPointerException.
     */
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
