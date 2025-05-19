package com.cineworld.controller;

import com.cineworld.DAO.bookingDAO;
import com.cineworld.model.bookingModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


/**
 * bookController.java
 * 
 * Servlet controller for managing bookings in the admin panel.
 * 
 * Responsibilities:
 * - Handles CRUD operations (create, read, update, delete) for bookings.
 * - Allows searching bookings by ID, name, or date.
 * - Restricts access to users with admin role.
 * 
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/admin/bookcontrol" })
public class bookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private bookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new bookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("10");

        HttpSession session = request.getSession();
        if (!"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<bookingModel> bookings = bookingDAO.getAllBookings();
        System.out.println(bookings);
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   System.out.println("1");
    	   String action = request.getParameter("action");

    	    if (action == null) {
    	    	System.out.println("2");
    	        response.sendRedirect(request.getContextPath() + "/admin/bookcontrol");
    	        return;
    	    }

    	    String id = request.getParameter("bookingId");
    	    String name = request.getParameter("bookingName");
    	    String movie = request.getParameter("bookingMovie");
    	    String date = request.getParameter("bookingDate");
    	    String time = request.getParameter("bookingTime");
    	    String seat = request.getParameter("bookingSeat");

    	    bookingModel booking = new bookingModel();
    	    booking.setBookingId(id != null && !id.isEmpty() ? Integer.parseInt(id) : 0);
    	    booking.setBookingName(name);
    	    booking.setMovie(movie);
    	    booking.setBookingDate(date);
    	    booking.setTime(time);
    	    booking.setSeatNumber(seat);

    	    switch (action) {
    	        case "add":
    	        	System.out.println("add bhayo");
    	            bookingDAO.createBooking(booking);
    	            break;
    	        case "update":
    	        	System.out.println("update bayo");
    	            bookingDAO.updateBooking(booking);
    	            break;
    	        case "delete":
    	        	System.out.println("delete bhayo");
    	            bookingDAO.deleteBooking(booking.getBookingId());
    	            break;    
    	        case "search":
                  searchBooking(request, response);
                  break;
    	        default:
    	            response.sendRedirect(request.getContextPath() + "/admin/bookcontrol");
    	            break;
    	    }

    	    response.sendRedirect(request.getContextPath() + "/admin/bookcontrol");
    }

    private void searchBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm != null && !searchTerm.isEmpty()) {
            List<bookingModel> allBookings = bookingDAO.getAllBookings();
            List<bookingModel> filteredBookings = new ArrayList<>();
            try {
                int searchId = Integer.parseInt(searchTerm);
                for (bookingModel booking : allBookings) {
                    if (booking.getBookingId() == searchId) {
                        filteredBookings.add(booking);
                    }
                }
                request.setAttribute("bookings", filteredBookings);
            } catch (NumberFormatException e) {
                for (bookingModel booking : allBookings) {
                    if (booking.getBookingName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                            booking.getBookingDate().contains(searchTerm)) {
                        filteredBookings.add(booking);
                    }
                }
                request.setAttribute("bookings", filteredBookings);
            }

        } else {
            List<bookingModel> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
        }

        request.getRequestDispatcher("/WEB-INF/pages/bookcontrol.jsp").forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingName = request.getParameter("bookingName");
        String bookingDate = request.getParameter("bookingDate");
        String bookingSeat = request.getParameter("bookingSeat");
        String bookingMovie = request.getParameter("bookingMovie");
        String bookingTime = request.getParameter("bookingTime");


        bookingModel booking = new bookingModel();
        booking.setBookingName(bookingName);
        booking.setBookingDate(bookingDate);
        booking.setSeatNumber(bookingSeat);
        booking.setMovie(bookingMovie);
        booking.setTime(bookingTime);
        boolean success = bookingDAO.createBooking(booking);
        if (success) {
            request.setAttribute("bookingSuccess", true);
            response.sendRedirect(request.getContextPath() + "/confirmation"); // Redirect
        } else {
            request.setAttribute("bookingError", "Failed to create booking. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response); 
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/bookcontrol");
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String bookingName = request.getParameter("bookingName");
        String bookingDate = request.getParameter("bookingDate");
        String bookingSeat = request.getParameter("bookingSeat");
        String bookingMovie = request.getParameter("bookingMovie");
        String bookingTime = request.getParameter("bookingTime");

        bookingModel booking = new bookingModel();
        booking.setBookingId(bookingId);
        booking.setBookingName(bookingName);
        booking.setBookingDate(bookingDate);
        booking.setSeatNumber(bookingSeat);
        booking.setMovie(bookingMovie);
        booking.setTime(bookingTime);
        bookingDAO.updateBooking(booking);
        response.sendRedirect(request.getContextPath() + "/admin/bookcontrol");

    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdParam = request.getParameter("bookingId");
        if (bookingIdParam != null && !bookingIdParam.isEmpty()) {
            try {
                int bookingId = Integer.parseInt(bookingIdParam);
                bookingDAO.deleteBooking(bookingId);

                List<bookingModel> bookings = bookingDAO.getAllBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("/WEB-INF/pages/bookcontrol.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid booking ID format.");
                return;
            }

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Booking ID is missing.");
            return;
        }
    }
}

