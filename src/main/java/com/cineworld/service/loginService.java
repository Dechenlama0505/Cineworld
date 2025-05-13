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
				return validatePassword(result, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	private Boolean validatePassword(ResultSet result, userModel user) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");

		// Assuming you are storing plain text passwords for now.
		// If you are using encryption, uncomment and use PasswordUtil.decrypt()
		if (dbUsername.equals(user.getUsername()) && dbPassword.equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}