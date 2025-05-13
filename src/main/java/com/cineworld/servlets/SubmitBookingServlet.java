//package com.cineworld.controller;
//
//import com.cineworld.model.bookingModel;
//import com.cineworld.service.bookingService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@WebServlet("/submitBooking")
//public class SubmitBookingServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private bookingService bookingService;
//    private static final Logger logger = Logger.getLogger(SubmitBookingServlet.class.getName());
//
//    @Override
//    public void init() throws ServletException {
//        bookingService = new bookingService();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String movie = request.getParameter("movie");
//        String time = request.getParameter("time");
//        String bookingDate = request.getParameter("bookingDate");
//        String seatNumber = request.getParameter("seatNumber");
//
//        // In a real application, you'd typically get the userId from the session
//        int userId = 1; // For now, let's hardcode a user ID
//
//        try {
//            BookingModel booking = new BookingModel();
//            booking.setUserId(userId);
//            booking.setBookingDate(bookingDate);
//            booking.setSeatNumber(seatNumber);
//            booking.setMovie(movie);
//            booking.setTime(time);
//
//            boolean success = bookingService.addBooking(booking);
//            if (success) {
//                request.setAttribute("bookingSuccess", true);
//                logger.info("Booking created successfully.");
//            } else {
//                request.setAttribute("bookingError", "Failed to create booking. Please try again.");
//                logger.warning("Failed to create booking.");
//            }
//
//        } catch (Exception e) {
//            request.setAttribute("bookingError", "An unexpected error occurred. Please try again.");
//            logger.log(Level.SEVERE, "Exception in doPost", e);
//        } finally {
//            request.getRequestDispatcher("/booknow.jsp").forward(request, response);
//        }
//    }
//}