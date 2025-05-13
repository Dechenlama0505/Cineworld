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
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@WebServlet("/admin/bookings")
//public class AdminBookingServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private bookingService bookingService;
//    private static final Logger logger = Logger.getLogger(AdminBookingServlet.class.getName());
//
//    @Override
//    public void init() throws ServletException {
//    	bookingService = new bookingService();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            List<bookingModel> bookings = bookingService.getAllBookings();
//            request.setAttribute("bookings", bookings); // Store bookings in request attribute
//            request.getRequestDispatcher("/book.jsp").forward(request, response); // Forward to book.jsp
//            logger.info("Retrieved all bookings for admin and forwarded to book.jsp.");
//
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error retrieving bookings for admin", e);
//            request.setAttribute("errorMessage", "Failed to retrieve bookings.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response); // Or handle error in book.jsp
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("add".equals(action)) {
//            addBookingAdmin(request, response);
//        } else if ("delete".equals(action)) {
//            deleteBookingAdmin(request, response);
//        } else if ("update".equals(action)) {
//            updateBookingAdmin(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{\"error\": \"Invalid action.\"}");
//            logger.warning("Invalid action received in AdminBookingServlet doPost.");
//        }
//    }
//
//    private void addBookingAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String name = request.getParameter("bookingName");
//        String movie = request.getParameter("bookingMovie");
//        String bookingDate = request.getParameter("bookingDate");
//        String bookingTime = request.getParameter("bookingTime");
//        String bookingSeat = request.getParameter("bookingSeat");
//
//        try {
//        	bookingModel booking = new bookingModel();
//            booking.setUserId(0); // Or handle this differently for admin bookings
//            booking.setMovie(movie);
//            booking.setBookingDate(bookingDate);
//            booking.setTime(bookingTime);
//            booking.setSeatNumber(bookingSeat);
//            booking.setBookingName(name);
//
//            boolean success = bookingService.addBooking(booking);
//            if (success) {
//                response.sendRedirect(request.getContextPath() + "/admin/bookings"); // Redirect to refresh the booking list
//                logger.info("Admin added booking.");
//            } else {
//                request.setAttribute("errorMessage", "Failed to add booking.");
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
//                logger.warning("Admin failed to add booking.");
//            }
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error adding booking by admin", e);
//            request.setAttribute("errorMessage", "An error occurred while adding the booking.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//
//    private void deleteBookingAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String idStr = request.getParameter("bookingId");
//        try {
//            int bookingId = Integer.parseInt(idStr);
//            boolean success = bookingService.deleteBooking(bookingId);
//            if (success) {
//                response.sendRedirect(request.getContextPath() + "/admin/bookings"); // Redirect to refresh the booking list
//                logger.info("Admin deleted booking with ID: " + bookingId);
//            } else {
//                request.setAttribute("errorMessage", "Booking not found or could not be deleted.");
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
//                logger.warning("Admin failed to delete booking with ID: " + bookingId);
//            }
//        } catch (NumberFormatException e) {
//            request.setAttribute("errorMessage", "Invalid booking ID.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//            logger.warning("Invalid booking ID for deletion by admin: " + idStr);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error deleting booking by admin", e);
//            request.setAttribute("errorMessage", "An error occurred while deleting the booking.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//
//    private void updateBookingAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String idStr = request.getParameter("bookingId");
//        String name = request.getParameter("bookingName");
//        String movie = request.getParameter("bookingMovie");
//        String bookingDate = request.getParameter("bookingDate");
//        String bookingTime = request.getParameter("bookingTime");
//        String bookingSeat = request.getParameter("bookingSeat");
//
//        try {
//            int bookingId = Integer.parseInt(idStr);
//            bookingModel updatedBooking = new bookingModel();
//            updatedBooking.setBookingId(bookingId);
//            updatedBooking.setMovie(movie);
//            updatedBooking.setBookingDate(bookingDate);
//            updatedBooking.setTime(bookingTime);
//            updatedBooking.setSeatNumber(bookingSeat);
//
//
//            boolean success = bookingService.updateBooking(updatedBooking);
//            if (success) {
//                response.sendRedirect(request.getContextPath() + "/admin/bookings"); // Redirect to refresh the booking list
//                logger.info("Admin updated booking with ID: " + bookingId);
//            } else {
//                request.setAttribute("errorMessage", "Booking not found or could not be updated.");
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
//                logger.warning("Admin failed to update booking with ID: " + bookingId);
//            }
//        } catch (NumberFormatException e) {
//            request.setAttribute("errorMessage", "Invalid booking ID.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//            logger.warning("Invalid booking ID for update by admin: " + idStr);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error updating booking by admin", e);
//            request.setAttribute("errorMessage", "An error occurred while updating the booking.");
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//}