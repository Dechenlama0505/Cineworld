package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;

/**
 * registerService.java
 *
 * Service class for handling user registration in the Cineworld application.
 * This class connects to the database and inserts new user records based on the provided user model.
 *
 * Responsibilities:
 * - Establish a database connection using Dbconfig.
 * - registerUser(userModel): Inserts a new user into the database with values such as first name,
 *   last name, email, role, phone number, username, and password.
 * - Handles SQL exceptions, including duplicate entries (e.g., existing usernames or emails).
 *
 * Author: Dechen Lama
 */


public class registerService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public registerService() {
        dbConn = Dbconfig.getDbConnection();
        if (dbConn == null) {
            isConnectionError = true;
        }
    }

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
            insertStmt.setString(7, user.getPassword()); 

            return insertStmt.executeUpdate() > 0;

        } catch (SQLException e) {
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
