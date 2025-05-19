package com.cineworld.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBconnection.java
 * 
 * A utility class responsible for managing database connectivity to the Cineworld MySQL database.
 * 
 * Responsibilities:
 * - Load the MySQL JDBC driver.
 * - Provide methods for retrieving existing or new database connections.
 * 
 * Author: Dechen Lama
 */

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cineworld_db"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public static Connection newConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
