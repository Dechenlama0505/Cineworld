package com.cineworld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cineworld.config.Dbconfig;
import com.cineworld.model.contactrequestModel;

/**
 * Service class for handling Contact Request operations.
 */
public class contactrequestService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public contactrequestService() {
        dbConn = Dbconfig.getDbConnection();
        if (dbConn == null) {
            isConnectionError = true;
        }
    }

    /**
     * Retrieves all contact messages from the database.
     */
    public List<contactrequestModel> getAllContactMessages() {
        List<contactrequestModel> messages = new ArrayList<>();
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return messages;
        }

        String query = "SELECT * FROM contact_requests";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contactrequestModel contact = new contactrequestModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("content")
                );
                messages.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    /**
     * Inserts a new contact request into the database.
     */
    public boolean saveContactRequest(contactrequestModel contact) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return false;
        }

        String query = "INSERT INTO contact_requests (name, email, content) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getContent());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a contact request by ID.
     */
    public boolean deleteContactById(int id) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return false;
        }

        String query = "DELETE FROM contact_requests WHERE id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
