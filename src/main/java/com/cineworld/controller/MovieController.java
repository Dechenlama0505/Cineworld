package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cineworld.DAO.userDAO;

/**
 * Servlet implementation class MovieController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/moviecontroller" })
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		userDAO user = new userDAO();
		int totalUsers = user.getTotalUsers();
		int totalMovies = 4; // assuming this method exists
	    int totalSeatsBooked = user.getTotalSeatsBooked(); // assuming this method exists

		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalMovies", totalMovies);
	    request.setAttribute("totalSeatsBooked", totalSeatsBooked);
        request.getRequestDispatcher("/WEB-INF/pages/movieDashboard.jsp").forward(request, response);
        
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
