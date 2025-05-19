package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;
import com.cineworld.util.passwordUtil;

/**
 * loginService.java
 *
 * Service class for handling user authentication in the Cineworld application.
 * It interacts with the database to validate user credentials.
 *
 * Responsibilities:
 * - Establishes a database connection using Dbconfig.
 * - login(userModel): Validates the user's login by matching the username and
 *   decrypted password from the database.
 * - Uses passwordUtil to decrypt stored passwords for comparison.
 * - Handles SQL exceptions and connection issues.
 *
 * Author: Dechen Lama
 */

public class loginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public loginService() {
        dbConn = Dbconfig.getDbConnection();
        if (dbConn == null) {
            isConnectionError = true;
        }
    }

    public Boolean login(userModel user) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return false;
        }

        String query = "SELECT username, password FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
            	System.out.println("yay");
                return validatePassword(result, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }


    private boolean validatePassword(ResultSet result, userModel userModel) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");
		System.out.println(dbPassword);
		System.out.println(passwordUtil.decrypt(dbPassword, dbUsername));
		return dbUsername.equals(userModel.getUsername())
				&& passwordUtil.decrypt(dbPassword, dbUsername).equals(userModel.getPassword());
	}
}
