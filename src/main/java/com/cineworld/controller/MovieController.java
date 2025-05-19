package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cineworld.DAO.userDAO;

/**
 * MovieController.java
 * 
 * Servlet controller for the movie dashboard page.
 * 
 * Handles GET requests to "/moviecontroller". Retrieves and sets
 * statistics such as total users, total movies, and total seats booked,
 * then forwards the request to the movieDashboard JSP page.
 * 
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/moviecontroller" })
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MovieController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDAO user = new userDAO();
		int totalUsers = user.getTotalUsers();
		int totalMovies = 4; 
	    int totalSeatsBooked = user.getTotalSeatsBooked(); 

		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalMovies", totalMovies);
	    request.setAttribute("totalSeatsBooked", totalSeatsBooked);
        request.getRequestDispatcher("/WEB-INF/pages/movieDashboard.jsp").forward(request, response);
        
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
