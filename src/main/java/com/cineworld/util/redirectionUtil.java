package com.cineworld.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class redirectionUtil {

    /**
     * Redirects to a specific page within the application context.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param path path to redirect to (e.g., "/home", "/login.jsp")
     * @throws IOException
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

    /**
     * Redirects to the login page.
     */
    public static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirect(request, response, "/login.jsp");
    }

    /**
     * Redirects to the home page.
     */
    public static void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirect(request, response, "/home");
    }
}

