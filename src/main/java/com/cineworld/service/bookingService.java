package com.cineworld.service;

import com.cineworld.model.bookingModel;

import com.cineworld.model.userModel; 
import com.cineworld.DAO.bookingDAO;
import com.cineworld.DAO.userDAO; 
import java.util.ArrayList;
import java.util.List;

/**
 * bookingService.java
 *
 * Service layer class responsible for handling booking-related operations in the Cineworld application.
 * It acts as an intermediary between the controller and DAO layers, and manages both in-memory
 * and database interactions for bookings.
 *
 * Responsibilities:
 * - addBooking(bookingModel): Adds a new booking to an in-memory list with a generated ID.
 * - getAllBookings(): Retrieves all in-memory bookings.
 * - deleteBooking(int): Deletes a booking by ID from the in-memory list.
 * - updateBooking(bookingModel): Updates booking information in the in-memory list.
 * - getBookingById(int): Retrieves a specific booking by its ID.
 * - getAllBookingsInfo(): Retrieves all bookings from the database using bookingDAO.
 *
 * Author: Dechen Lama
 */


public class bookingService {
    private static final List<bookingModel> bookings = new ArrayList<>();
    private static int bookingIdCounter = 1; 
    private final userDAO userDAO = new userDAO(); 

    public boolean addBooking(bookingModel booking) {
        try {
            booking.setBookingId(bookingIdCounter++);
            bookings.add(booking);
            System.out.println("Booking added: " + booking.getBookingId() + " - " + booking.getBookingName() + " - " + booking.getMovie() + " - " + booking.getBookingDate() + " - " + booking.getSeatNumber() + " - " + booking.getTime());
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public List<bookingModel> getAllBookings() {
        return new ArrayList<>(bookings); 
    }

    public boolean deleteBooking(int bookingId) {
        try {
            boolean removed = bookings.removeIf(booking -> booking.getBookingId() == bookingId);
            if (removed) {
                System.out.println("Booking deleted with ID: " + bookingId);
                return true;
            } else {
                System.out.println("Booking not found with ID: " + bookingId);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public boolean updateBooking(bookingModel updatedBooking) {
        try {
            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).getBookingId() == updatedBooking.getBookingId()) {
                    bookings.set(i, updatedBooking);
                    System.out.println("Booking updated with ID: " + updatedBooking.getBookingId());
                    return true;
                }
            }
            System.out.println("Booking not found with ID: " + updatedBooking.getBookingId() + " for update");
            return false;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public bookingModel getBookingById(int bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    public List<bookingModel> getAllBookingsInfo() {
        try {
            bookingDAO dao = new bookingDAO(); 
            return dao.getAllBookings();       
        } catch (Exception e) {
            e.printStackTrace();               
            return new ArrayList<>();         
        }
    }

}