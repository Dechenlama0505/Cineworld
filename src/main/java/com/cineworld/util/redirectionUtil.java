package com.cineworld.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * redirectionUtil.java
 * 
 * A utility class to centralize and simplify redirection logic across the web application.
 * 
 * Responsibilities:
 * - Handle relative path-based redirection using the request context.
 * - Provide common redirection methods (e.g., to login or home pages).
 * 
 * Author: Dechen Lama
 */

public class redirectionUtil {


    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }


    public static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirect(request, response, "/login.jsp");
    }

    public static void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirect(request, response, "/home");
    }
}

