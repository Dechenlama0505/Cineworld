package com.cineworld.controller;

import com.cineworld.model.contactrequestModel;
import com.cineworld.service.contactrequestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// ✅ Fixed URL pattern to match the link in adminheader.jsp
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/contactrequests" })
public class contactrequestController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private contactrequestService contactService;

    @Override
    public void init() throws ServletException {
        contactService = new contactrequestService(); // initialize service once
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<contactrequestModel> messages = contactService.getAllContactMessages();
        request.setAttribute("messages", messages);

        // ✅ Fixed JSP file name and location
        request.getRequestDispatcher("/WEB-INF/pages/contact-requests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        if (name != null && email != null && message != null) {
            contactrequestModel contact = new contactrequestModel(name, email, message);
            contactService.saveContactRequest(contact);
        }

        response.sendRedirect(request.getContextPath() + "/admin/contactrequests");
    }
}
