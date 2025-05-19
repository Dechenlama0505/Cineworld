package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;

/**
 * RegisterService handles the registration of new users.
 * It manages database interactions for user registration.
 */
public class registerService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection.
     */
    public registerService() {
        dbConn = Dbconfig.getDbConnection();
        if (dbConn == null) {
            isConnectionError = true;
        }
    }

    /**
     * Registers a new user into the database.
     */
    public boolean registerUser(userModel user) {
        if (isConnectionError) {
            System.err.println("Database connection is not available.");
            return false;
        }

        String insertQuery = "INSERT INTO user (firstName, lastName, email, role, phoneNumber, username, password) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, user.getFirstName());
            insertStmt.setString(2, user.getLastName());
            insertStmt.setString(3, user.getEmail());
            insertStmt.setString(4, user.getRole());
            insertStmt.setString(5, user.getPhoneNumber());
            insertStmt.setString(6, user.getUsername());
            insertStmt.setString(7, user.getPassword()); // Consider hashing passwords here

            return insertStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // SQLState "23000" is commonly used for integrity constraint violation (like duplicate keys)
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) {
                System.err.println("Registration error: Username or email may already exist.");
            } else {
                System.err.println("Error during user registration: " + e.getMessage());
            }
            e.printStackTrace();
            return false;
        }
    }
}
