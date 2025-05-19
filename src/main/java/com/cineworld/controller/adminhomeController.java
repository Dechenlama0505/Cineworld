package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * adminhomeController.java
 * 
 * Servlet that handles requests to the admin home page.
 * 
 * Functionality:
 * - Forwards GET requests to the admin home JSP page.
 * - Supports POST requests by delegating them to the GET handler.
 * 
 * Author: Dechen Lama
 */

@WebServlet(urlPatterns = { "/adminhome" })
public class adminhomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/adminhome.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
