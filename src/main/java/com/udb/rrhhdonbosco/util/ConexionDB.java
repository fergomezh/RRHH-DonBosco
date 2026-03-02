package com.udb.rrhhdonbosco.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL =  "jdbc:mysql://localhost:3306/db_rrhh_donbosco";
    private static final String USER = "root"; // Tu User
    private static final String PASSWORD = "verystrongpassword12"; // Tu Password

    private ConexionDB() {}

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
