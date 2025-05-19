package com.cineworld.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * aboutusController.java
 * 
 * Servlet controller for the "About Us" page.
 * 
 * Handles GET and POST requests to "/aboutus". 
 * Allows access only to users with the "admin" role; otherwise redirects to login.
 * Forwards valid admin requests to the About Us JSP page.
 * 
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/aboutus" })
public class aboutusController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!"admin".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/pages/aboutus.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
