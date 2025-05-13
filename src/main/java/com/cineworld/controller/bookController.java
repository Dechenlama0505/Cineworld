package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/admin/bookcontrol" }) // changed from /book
public class bookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Only accessible by admin
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if admin is logged in
        if (!"admin".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Forward to book.jsp
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
    }

    // Keeps your existing booking logic intact
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String bookingId = request.getParameter("bookingId");
        String bookingName = request.getParameter("bookingName");
        String bookingMovie = request.getParameter("bookingMovie");
        String bookingDate = request.getParameter("bookingDate");
        String bookingTime = request.getParameter("bookingTime");
        String bookingSeat = request.getParameter("bookingSeat");

        // Your business logic (still placeholder for now)
        if (bookingId != null && !bookingId.isEmpty()) {
            // Update booking logic
        } else {
            // Create new booking logic
        }

        // Forward again after processing
        doGet(request, response);
    }
}
