package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.cineworld.model.userModel;
import com.cineworld.service.loginService;
import com.cineworld.util.validationUtil;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/login", "/" })
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    if (!validationUtil.isNullOrEmpty(username) && !validationUtil.isNullOrEmpty(password)) {
	        HttpSession session = request.getSession();
	        userModel user = new userModel();
	        user.setUsername(username);

	        if ("admin".equals(username) && "admin123".equals(password)) {
	            user.setRole("admin");
	            session.setAttribute("user", user);
	            session.setAttribute("role", "admin"); // Optional
	            System.out.println("Admin logged in: " + user.getUsername());
	            response.sendRedirect(request.getContextPath() + "/adminwelcome");
	            return;
	        }

	        loginService loginService = new loginService();
	        user.setPassword(password);
	        Boolean loggedInUser = loginService.login(user);
	        
	        if (loggedInUser) {
	            user.setRole("user");
	            session.setAttribute("user", user); // ✅ Store the correct user object
	            session.setAttribute("role", "user"); // Optional
	            System.out.println("User logged in: " + user.getUsername());
	            response.sendRedirect(request.getContextPath() + "/home");
	        } else {
	            request.setAttribute("errorMessage", "Invalid username or password.");
	            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	        }
	    } else {
	        request.setAttribute("errorMessage", "Username and password are required.");
	        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	    }
	}
}
