package com.cineworld.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.cineworld.model.userModel;
import com.cineworld.service.loginService;
import com.cineworld.util.sessionUtil;
import com.cineworld.util.validationUtil;

@WebServlet(asyncSupported = true, urlPatterns = {"/login", "/"})
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Login get");
        // Show login page
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle login form submission
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!validationUtil.isNullOrEmpty(username) && !validationUtil.isNullOrEmpty(password)) {

        	 if (username.equals(username) && password.equals(password)) {
                 // Set user info in session
                 sessionUtil.setAttribute(request, "user", username);

                 // Redirect to home.jsp
                 response.sendRedirect(request.getContextPath() + "/home");
             } else {
                 // Login failed, show error
                 request.setAttribute("errorMessage", "Invalid username or password.");
                 request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
             }
        }

       
    }
}

