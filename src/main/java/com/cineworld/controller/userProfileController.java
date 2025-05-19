package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import com.cineworld.model.userModel;
import com.cineworld.DAO.userDAO;
import com.cineworld.util.sessionUtil;

/**
 * Handles user profile-related operations such as viewing, updating profile data,
 * registering new users, and logging out.
 * 
 * Author: Dechen Lama
 */

@WebServlet(urlPatterns = {
    "/userProfile",
    "/updateProfile",
    "/updateProfilePicture"
})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, 
    maxFileSize = 1024 * 1024 * 10,  
    maxRequestSize = 1024 * 1024 * 15 
)
public class userProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private userDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new userDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/userProfile":
                showUserProfile(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            case "/register":
                showRegisterPage(request, response);
                break;
            default:
                response.sendRedirect("login");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/updateProfile":
                updateProfile(request, response);
                break;
            case "/updateProfilePicture":
                response.sendRedirect("userProfile?error=profile_picture_not_supported");
                break;
            case "/register":
                processRegistration(request, response);
                break;
            default:
                response.sendRedirect("login");
                break;
        }
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("No session found for userProfile request");
            response.sendRedirect("login");
            return;
        }

        String username = (String) session.getAttribute("username");
        if (username == null) {
            userModel user = (userModel) sessionUtil.getAttribute(request, "user");
            if (user != null) {
                username = user.getUsername();
                System.out.println("Username retrieved from user attribute: " + username);
            }
        }

        if (username == null) {
            System.out.println("Username not found in session");
            response.sendRedirect("login");
            return;
        }

        userModel userProfileData = (userModel) sessionUtil.getAttribute(request, "userModel");
        if (userProfileData == null) {
            System.out.println("userModel not in session, fetching from database for username: " + username);
            try {
                userProfileData = userDao.getUserByUsername(username);
                if (userProfileData == null) {
                    System.err.println("User not found in database for username: " + username);
                    sessionUtil.invalidateSession(request);
                    response.sendRedirect("login");
                    return;
                }
                System.out.println("User fetched successfully: " + userProfileData.getUsername());
                sessionUtil.setAttribute(request, "userModel", userProfileData);
            } catch (SQLException e) {
                System.err.println("Error fetching user profile: " + e.getMessage());
                request.setAttribute("errorMessage", "Unable to fetch user profile. Please try again.");
                request.setAttribute("userModel", null);
                request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
                return;
            }
        } else {
            System.out.println("userModel found in session for username: " + userProfileData.getUsername());
        }

        request.setAttribute("userModel", userProfileData);
        request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Logging out user");
        sessionUtil.invalidateSession(request);
        response.sendRedirect("login");
    }

    private void showRegisterPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private void processRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        userModel newUser = new userModel();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);

        try {
            userDao.registerUser(newUser);
            System.out.println("User registered: " + username);
            request.setAttribute("successMessage", "Registration successful! Please login.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Registration error: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userModel user = (userModel) sessionUtil.getAttribute(request, "userModel");
        if (user == null) {
            System.out.println("No user model found for updateProfile");
            response.sendRedirect("login");
            return;
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        try {
            userDao.updateUserProfile(user);
            sessionUtil.setAttribute(request, "userModel", user);
            System.out.println("Profile updated for username: " + username);
            response.sendRedirect("userProfile");
        } catch (Exception e) {
            System.err.println("Profile update error: " + e.getMessage());
            request.setAttribute("errorMessage", "Failed to update profile. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}