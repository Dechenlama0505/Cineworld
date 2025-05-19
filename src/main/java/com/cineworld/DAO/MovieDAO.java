package com.cineworld.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cineworld.model.movieModel;
import com.cineworld.util.DBconnection;


/**
 * MovieDAO handles all database operations related to the "movies" table.
 * It allows fetching movie records, inserting new movies, updating existing ones,
 * and deleting movies. It also provides utility methods like counting "Now Showing" movies.
 *
 * Methods:
 * - getTotalNowShowingMovies(): Returns count of movies currently showing.
 * - getAllMovies(): Retrieves a list of all movies.
 * - addMovie(movieModel movie): Inserts a new movie into the database.
 * - updateMovie(movieModel movie): Updates details of an existing movie.
 * - deleteMovie(int id): Deletes a movie based on its ID.
 *
 * Author: Dechen
 */

public class MovieDAO {
    private Connection connection;
    
    public MovieDAO() {
        connection = DBconnection.getConnection(); 
    }
    
    public int getTotalNowShowingMovies() {
        
        try {
            String sql = "SELECT COUNT(*) FROM movies WHERE status = 'Now Showing'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       
        return 3;
    }
    
    public List<movieModel> getAllMovies() {
        List<movieModel> movies = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	movieModel movie = new movieModel();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setStatus(resultSet.getString("status"));
                movie.setSeatsBooked(resultSet.getInt("seats_booked"));
                // Set other properties as needed
                
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movies;
    }
    
 
    public boolean addMovie(movieModel  movie) {
        try {
            String sql = "INSERT INTO movies (title, status, seats_booked) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getStatus());
            statement.setInt(3, movie.getSeatsBooked());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateMovie(movieModel movie) {
        try {
            String sql = "UPDATE movies SET title = ?, status = ?, seats_booked = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getStatus());
            statement.setInt(3, movie.getSeatsBooked());
            statement.setInt(4, movie.getId());
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteMovie(int id) {
        try {
            String sql = "DELETE FROM movies WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}