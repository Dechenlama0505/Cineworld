package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/booknow")
public class booknowController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to prepare data (e.g., seat layout) for the JSP page
        String[] rows = {"A", "B", "C", "D"};
        
        // Set the seat rows as a request attribute to pass it to the JSP
        request.setAttribute("rows", rows);

        // Forward the request to the booknow.jsp
        request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user input from the booking form
        String bookingId = request.getParameter("bookingId");
        String bookingName = request.getParameter("bookingName");
        String bookingDate = request.getParameter("bookingDate");
        String bookingSeat = request.getParameter("bookingSeat");
        String bookingMovie = request.getParameter("bookingMovie");
        String bookingTime = request.getParameter("bookingTime");

        // Store booking details in the session
        HttpSession session = request.getSession();
        session.setAttribute("bookingId", bookingId);
        session.setAttribute("bookingName", bookingName);
        session.setAttribute("bookingDate", bookingDate);
        session.setAttribute("bookingSeat", bookingSeat);
        session.setAttribute("bookingMovie", bookingMovie);
        session.setAttribute("bookingTime", bookingTime);

        // Redirect to the confirmation page (or any other page)
        response.sendRedirect(request.getContextPath() + "/confirmation");
    }
}
