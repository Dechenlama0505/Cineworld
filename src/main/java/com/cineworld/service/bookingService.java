//package com.cineworld.service;
//
//import com.cineworld.model.bookingModel;
//import com.cineworld.model.userModel; // Assuming you have a User model
//import com.cineworld.userDAO.userDAO; // Assuming you have a UserDAO
//import java.util.ArrayList;
//import java.util.List;
//
//public class bookingService {
//    private static final List<bookingModel> bookings = new ArrayList<>();
//    private static int bookingIdCounter = 1; // Simple ID counter
//    private final userDAO userDAO = new userDAO(); // Instantiate your User DAO
//
//    public boolean addBooking(bookingModel booking) {
//        try {
//            booking.setBookingId(bookingIdCounter++);
//            bookings.add(booking);
//            System.out.println("Booking added: " + booking.getBookingId() + " - " + booking.getBookingName() + " - " + booking.getMovie() + " - " + booking.getBookingDate() + " - " + booking.getBookingSeatNumber() + " - " + booking.getBookingTime());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace(); // Simple error output
//            return false;
//        }
//    }
//
//    public List<bookingModel> getAllBookings() {
//        return new ArrayList<>(bookings); // Return a copy
//    }
//
//    public boolean deleteBooking(int bookingId) {
//        try {
//            boolean removed = bookings.removeIf(booking -> booking.getBookingId() == bookingId);
//            if (removed) {
//                System.out.println("Booking deleted with ID: " + bookingId);
//                return true;
//            } else {
//                System.out.println("Booking not found with ID: " + bookingId);
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Simple error output
//            return false;
//        }
//    }
//
//    public boolean updateBooking(bookingModel updatedBooking) {
//        try {
//            for (int i = 0; i < bookings.size(); i++) {
//                if (bookings.get(i).getBookingId() == updatedBooking.getBookingId()) {
//                    bookings.set(i, updatedBooking);
//                    System.out.println("Booking updated with ID: " + updatedBooking.getBookingId());
//                    return true;
//                }
//            }
//            System.out.println("Booking not found with ID: " + updatedBooking.getBookingId() + " for update");
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace(); // Simple error output
//            return false;
//        }
//    }
//
//    public bookingModel getBookingById(int bookingId) {
//        return bookings.stream()
//                .filter(booking -> booking.getBookingId() == bookingId)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<userModel> getAllUsersInfo() {
//        try {
//            return userDAO.getAllUsers(); // Call the method in your UserDAO, which returns List<userModel>
//        } catch (Exception e) {
//            e.printStackTrace(); // Simple error output
//            return new ArrayList<>(); // Return an empty list in case of an error
//        }
//    }
//}