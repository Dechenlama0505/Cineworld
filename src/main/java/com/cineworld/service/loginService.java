package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns the logged-in User object.
 */
public class loginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection. Sets the connection error
     * flag if the connection fails.
     */
    public loginService() {
        try {
            dbConn = Dbconfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public userModel login(userModel user) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT user_id, username, password, email, first_name, last_name, phone_number FROM users WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return validatePassword(result, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private userModel validatePassword(ResultSet result, userModel user) throws SQLException {
        String dbUsername = result.getString("username");
        String dbPassword = result.getString("password");

        // Assuming you are storing plain text passwords for now.
        // If you are using encryption, uncomment and use PasswordUtil.decrypt()
        if (dbUsername.equals(user.getUsername()) && dbPassword.equals(user.getPassword())) {
        	userModel loggedInUser = new userModel();
            loggedInUser.setUserId(result.getInt("user_id"));
            loggedInUser.setUsername(dbUsername);
            loggedInUser.setEmail(result.getString("email"));
            loggedInUser.setFirstName(result.getString("first_name"));
            loggedInUser.setLastName(result.getString("last_name"));
            loggedInUser.setPhoneNumber(result.getString("phone_number"));
            return loggedInUser;
        }
        return null;
    }
}