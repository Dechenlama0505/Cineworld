package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.cineworld.model.userModel;
import com.cineworld.service.registerService;

@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
public class registerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display register page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Register get");
        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    }

    // Handle register form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("Reached here");
        // Fetch form fields
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");

    	// Create and populate user model
    	userModel user = new userModel();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setPhoneNumber(phoneNumber);
    	user.setPassword(password); // Consider hashing before setting
    	user.setRole(role);

    	// Call the register service
    	registerService service = new registerService();
    	boolean isRegistered = service.registerUser(user);

    	if (isRegistered) { 
    	    response.sendRedirect(request.getContextPath() + "/login");
    	} else { 
    	    request.setAttribute("errorMessage", "Registration failed. Username or email may already be in use.");
    	    request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    	}
    	
    	
    	
    }
}

