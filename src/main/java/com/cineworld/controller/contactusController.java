package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * contactusController handles the display and processing of the Contact Us form.
 * It forwards GET requests to the JSP page and processes POST submissions.
 *
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/contactus" })
public class contactusController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/contactus.jsp").forward(request, response);
    }


    @SuppressWarnings("unused")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        
        // In a real application, you would save this data to a database
        doGet(request, response);
    }
}