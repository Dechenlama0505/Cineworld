package com.cineworld.userDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cineworld.model.userModel;
import com.cineworld.util.DBconnection;

public class userDAO {
    
    public userModel validateUser(String username, String password) {
    	userModel user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBconnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // In a real app, use password hashing
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = new userModel();
                //user.setID(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return user;
    }
    
    // Add other methods for user management as needed
}