package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;


/**
 * RegisterService handles the registration of new users. It manages database
 * interactions for user registration.
 */
public class registerService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public registerService() {
        try {
            this.dbConn = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

   
    public boolean registerUser(userModel user) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return false; // Or null, depending on desired error handling
        }

        String insertQuery = "INSERT INTO user (firstName, lastName, email, role, phoneNumber, username, password) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, user.getFirstName());
            // Consider hashing the password here instead of storing plain text
            insertStmt.setString(2, user.getLastName());
            insertStmt.setString(3, user.getEmail());
            insertStmt.setString(4, user.getRole());
            insertStmt.setString(5, user.getPhoneNumber());
            insertStmt.setString(6, user.getUsername());
            insertStmt.setString(7,user.getPassword());

            return insertStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Check for unique constraint violations (e.g., username or email already exists)
            if (e.getSQLState().equals("23505")) { // Standard SQL state for unique violation
                System.err.println("Error during user registration: Username or email already exists.");
                return false;
            } else {
                System.err.println("Error during user registration: " + e.getMessage());
                e.printStackTrace();
                return false; // Or null
            }
        }
    }
}