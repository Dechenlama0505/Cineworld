package com.cineworld.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.userModel;
import com.cineworld.util.DBconnection;
import com.cineworld.util.passwordUtil;

public class userDAO {

    // Get total user count
    public int getTotalUsers() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM user";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error in getTotalUsers: " + e.getMessage());
        }

        return count;
    }
    
    public int getTotalSeatsBooked() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM booking";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error in getTotalSeatsBooked: " + e.getMessage());
        }

        return count;
    }

    public int getTotalMovies() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM user";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error in getTotalMovies: " + e.getMessage());
        }

        return count;
    }
    
    // Validate user login
    public userModel validateUser(String username, String password) {
        userModel user = null;
        String sql = "SELECT userid, username, password, role, email, image FROM user WHERE username = ?";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String encryptedPassword = rs.getString("password");
                    String decryptedPassword = passwordUtil.decrypt(encryptedPassword, username);

                    if (decryptedPassword.equals(password)) {
                        user = new userModel();
                        user.setUserId(rs.getInt("userid"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(encryptedPassword); // Or decryptedPassword if needed
                        user.setRole(rs.getString("role"));
                        user.setEmail(rs.getString("email"));
                        user.setImage(rs.getString("image"));

                    } else {
                        System.out.println("Invalid password for user: " + username);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error validating user: " + e.getMessage());
            throw new RuntimeException("User validation failed", e);
        }

        return user;
    }

    // Register a new user
    public boolean registerUser(userModel user) throws SQLException {
        String sql = "INSERT INTO user (firstName, lastName, username, password, email, phoneNumber, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhoneNumber());
            System.out.println(user.getImage());
            pstmt.setString(7, user.getImage());
            


            pstmt.executeUpdate();
            System.out.println("User registered: " + user.getUsername());
            return true;
        }
    }

    // Update user profile
    public void updateUserProfile(userModel user) throws SQLException {
        String sql = "UPDATE user SET firstName = ?, lastName = ?, username = ?, email = ?, phoneNumber = ? WHERE userid = ?";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setInt(6, user.getUserId());

            pstmt.executeUpdate();
            System.out.println("User profile updated for userId: " + user.getUserId());
        }
    }

    // Update profile picture
    public void updateProfilePicture(int userId, String fileName) throws SQLException {
        String sql = "UPDATE user SET profile_picture = ? WHERE userid = ?";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fileName);
            pstmt.setInt(2, userId);

            pstmt.executeUpdate();
            System.out.println("Profile picture updated for userId: " + userId);
        }
    }

    // Fetch user by username
    public userModel getUserByUsername(String username) throws SQLException {
        userModel user = null;
        String sql = "SELECT userid, firstName, lastName, username, email, phoneNumber, role, image FROM user WHERE username = ?";

        try (Connection conn = Dbconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new userModel();
                    user.setUserId(rs.getInt("userid"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phoneNumber"));
                    user.setRole(rs.getString("role"));
                    user.setImage(rs.getString("image"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user by username: " + username + " - " + e.getMessage());
            throw e;
        }

        return user;
    }

    // Optional: Test DB connection
    public boolean testConnection() throws SQLException {
        try (Connection conn = DBconnection.getConnection()) {
            return conn != null && !conn.isClosed();
        }
    }
}
