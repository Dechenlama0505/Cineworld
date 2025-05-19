package com.cineworld.controller;

import com.cineworld.DAO.bookingDAO;
import com.cineworld.model.bookingModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller for booking seats.
 * Handles display of booking form and processing booking submissions.
 *
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/booknow" })
public class booknowController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private bookingDAO bookingDAO;
    private static final Logger logger = Logger.getLogger(booknowController.class.getName());

    @Override
    public void init() throws ServletException {
        bookingDAO = new bookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] rows = {"A", "B", "C", "D"};
        request.setAttribute("rows", rows);
        request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingName = request.getParameter("bookingName");
        String bookingDate = request.getParameter("bookingDate");
        String bookingSeat = request.getParameter("bookingSeat");
        String bookingMovie = request.getParameter("bookingMovie");
        String bookingTime = request.getParameter("bookingTime");

        try {
            bookingModel booking = new bookingModel();
            booking.setBookingName(bookingName);
            booking.setBookingDate(bookingDate);
            booking.setSeatNumber(bookingSeat);
            booking.setMovie(bookingMovie);
            booking.setTime(bookingTime);

            boolean success = bookingDAO.createBooking(booking);
            if (success) {
                request.setAttribute("bookingSuccess", true);
                logger.info("Booking created successfully.");
                response.sendRedirect(request.getContextPath() + "/confirmation"); // Redirect
            } else {
                request.setAttribute("bookingError", "Failed to create booking. Please try again.");
                logger.warning("Failed to create booking.");
                request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response); // Forward
            }
        } catch (Exception e) {
            request.setAttribute("bookingError", "An unexpected error occurred. Please try again.");
            logger.log(Level.SEVERE, "Exception in doPost", e);
            request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response);
        }
    }
}

