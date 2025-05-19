package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * logoutController.java
 * 
 * Servlet responsible for handling user logout.
 * 
 * Functionality:
 * - Invalidates the current user session if it exists.
 * - Clears the "Role" cookie by setting its max age to zero.
 * - Redirects the user to the login page after logout.
 * - Supports both GET and POST HTTP methods.
 * 
 * Author: Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class logoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        if (req.getSession(false) != null) {
            req.getSession().invalidate();
        }


        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Role".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); 
                    resp.addCookie(cookie);
                }
            }
        }


        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

