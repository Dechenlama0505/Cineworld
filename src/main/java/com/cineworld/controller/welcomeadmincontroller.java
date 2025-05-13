package com.cineworld.controller;

import com.cineworld.model.userModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adminwelcome")
public class welcomeadmincontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            userModel user = (userModel) session.getAttribute("user");

            if ("admin".equals(user.getRole())) {               
                // Set username as session attribute to match JSP usage
                session.setAttribute("user", user);

                request.getRequestDispatcher("/WEB-INF/pages/welcomeadmin.jsp").forward(request, response);
            } else if ("user".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/adminhome");
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
