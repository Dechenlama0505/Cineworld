// BookingDAO.java
package com.cineworld.DAO;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.bookingModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.cineworld.util.DBconnection;

public class bookingDAO {

    private static final Logger logger = Logger.getLogger(bookingDAO.class.getName());

    public bookingDAO() {
    }

    public List<bookingModel> getAllBookings() {
        List<bookingModel> bookings = new ArrayList<>();
        String sql = "SELECT bookingId, bookingName, bookingDate, seatNumber, movie, time FROM booking";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in getAllBookings");
                return new ArrayList<>();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                bookingModel booking = new bookingModel();
                booking.setBookingId(resultSet.getInt("bookingId"));
                booking.setBookingName(resultSet.getString("bookingName"));
                booking.setBookingDate(resultSet.getString("bookingDate"));
                booking.setSeatNumber(resultSet.getString("seatNumber"));
                booking.setMovie(resultSet.getString("movie"));
                booking.setTime(resultSet.getString("time"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in getAllBookings", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return bookings;
    }

//    public bookingModel getBookingById(int bookingId) {
//        bookingModel booking = null;
//        String sql = "SELECT bookingId, bookingName, bookingDate, seatNumber, movie, time FROM booking WHERE bookingId = ?";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = Dbconfig.getDbConnection();
//            if (connection == null) {
//                logger.severe("Failed to get database connection in getBookingById");
//                return null;
//            }
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, bookingId);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                booking = new bookingModel();
//                booking.setBookingId(resultSet.getInt("bookingId"));
//                booking.setBookingName(resultSet.getString("bookingName"));
//                booking.setBookingDate(resultSet.getString("bookingDate"));
//                booking.setSeatNumber(resultSet.getString("seatNumber"));
//                booking.setMovie(resultSet.getString("movie"));
//                booking.setTime(resultSet.getString("time"));
//            }
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "SQLException in getBookingById", e);
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement, resultSet);
//        }
//        return booking;
//    }

    public boolean createBooking(bookingModel booking) {
        String sql = "INSERT INTO booking (bookingName, bookingDate, seatNumber, movie, time) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in createBooking");
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, booking.getBookingName());
            preparedStatement.setString(2, booking.getBookingDate());
            preparedStatement.setString(3, booking.getSeatNumber());
            preparedStatement.setString(4, booking.getMovie());
            preparedStatement.setString(5, booking.getTime());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in createBooking", e);
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    public void updateBooking(bookingModel booking) {
        String sql = "UPDATE booking SET bookingName = ?, bookingDate = ?, seatNumber = ?, movie = ?, time = ? WHERE bookingId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in updateBooking");
                return;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, booking.getBookingName());
            preparedStatement.setString(2, booking.getBookingDate());
            preparedStatement.setString(3, booking.getSeatNumber());
            preparedStatement.setString(4, booking.getMovie());
            preparedStatement.setString(5, booking.getTime());
            preparedStatement.setInt(6, booking.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in updateBooking", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }
    


    public void deleteBooking(int bookingId) {
        String sql = "DELETE FROM booking WHERE bookingId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in deleteBooking");
                return;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in deleteBooking", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }
    



    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing ResultSet", e);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing Statement", e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing Connection", e);
        }
    }
}

