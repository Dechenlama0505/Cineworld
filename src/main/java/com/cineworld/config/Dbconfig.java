package com.cineworld.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Dbconfig {

    private static final Logger logger = Logger.getLogger(Dbconfig.class.getName());

    // Database configuration information
    private static final String DB_NAME = "Cineworld";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Establishes a connection to the database.
     *
     * @return Connection object for the database, or null if connection fails
     */
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.severe("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            logger.severe("Database connection failed: " + e.getMessage());
        }
        return connection;
    }
}
