package com.cineworld.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cineworld.util.DBconnection;

public class SeatDAO {
    private Connection connection;
    
    public SeatDAO() {
        connection = DBconnection.getConnection();
    }
    
    public int getTotalSeatsBooked() {
        try {
            String sql = "SELECT SUM(seats_booked) FROM movies";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean bookSeat(int movieId, int numberOfSeats) {
        try {
            // First, update the movie's seats_booked count
            String sql = "UPDATE movies SET seats_booked = seats_booked + ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numberOfSeats);
            statement.setInt(2, movieId);
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean cancelBooking(int movieId, int numberOfSeats) {
        try {
            // First, update the movie's seats_booked count
            String sql = "UPDATE movies SET seats_booked = seats_booked - ? WHERE id = ? AND seats_booked >= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numberOfSeats);
            statement.setInt(2, movieId);
            statement.setInt(3, numberOfSeats); // Ensure we don't go below 0
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}