package com.cineworld.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.cineworld.model.userModel;
import com.cineworld.service.loginService; 
import com.cineworld.util.validationUtil;
import com.cineworld.util.sessionUtil;
import com.cineworld.DAO.userDAO;

import java.io.IOException;
import java.util.logging.Logger;
import java.sql.SQLException;

/**
 * loginController.java
 * 
 * Servlet handling user login for Cineworld application.
 * Supports both admin and regular user authentication.
 * 
 * Features:
 * - Processes GET requests to show login form.
 * - Processes POST requests to authenticate users.
 * - Sets session attributes upon successful login.
 * - Redirects or forwards appropriately based on authentication outcome.
 * - Tests database connection on servlet initialization.
 * 
 * @author Dechen Lama
 */


@WebServlet(asyncSupported = true, urlPatterns = { "/login", "/" })
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(loginController.class.getName());
    private userDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new userDAO();
        try {
            boolean dbConnected = userDao.testConnection(); 
            logger.info("Database connection test on init: " + (dbConnected ? "successful" : "failed"));
        } catch (SQLException e) {
            logger.severe("Failed to test database connection on init: " + e.getMessage());
        }
    }

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
            userModel loginUser = new userModel(username, password); 

            if ("admin".equals(username) && "admin123".equals(password)) {
                loginUser.setRole("admin");
                sessionUtil.setAttribute(request, "user", loginUser);
                sessionUtil.setAttribute(request, "role", "admin");
                sessionUtil.setAttribute(request, "username", username);
                logger.info("Admin logged in: " + loginUser.getUsername());
                response.sendRedirect(request.getContextPath() + "/adminwelcome");
                return;
            }

 
            userModel authenticatedUser = null;
            authenticatedUser = userDao.validateUser(username, password);

            if (authenticatedUser != null) {
                logger.info("User authenticated successfully: " + authenticatedUser.getUsername());
                authenticatedUser.setRole(authenticatedUser.getRole() != null ? authenticatedUser.getRole() : "user"); 
                sessionUtil.setAttribute(request, "user", authenticatedUser);
                sessionUtil.setAttribute(request, "role", authenticatedUser.getRole());
                sessionUtil.setAttribute(request, "username", authenticatedUser.getUsername());
                response.sendRedirect(request.getContextPath() + "/home"); 
            } else {
                logger.warning("Invalid login attempt for username: " + username);
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            }
        } else {
            logger.warning("Login attempt with empty username or password");
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}