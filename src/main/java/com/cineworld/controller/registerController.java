package com.cineworld.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.cineworld.DAO.userDAO;
import com.cineworld.model.userModel;
import com.cineworld.service.registerService;
import com.cineworld.util.passwordUtil;

@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 5,   // 5 MB
    maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class registerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Show register form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register get");
        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    }

    // Handle form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached here");

        // Form fields
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");
        Part imagePart = request.getPart("image");
        System.out.println(imagePart);
        if (imagePart == null || imagePart.getSize() == 0) {
            System.out.println("No image uploaded");
            // Handle error or return
        } else {
            String fileName = getFileName(imagePart);  // use your method to get the filename
            System.out.println("Uploaded image file name: " + fileName);
            // Save the image to server folder
            String uploadPath = getServletContext().getRealPath("/images");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);
        }       
        // Handle image upload
        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);

        // Path to save the image
        String uploadDir = getServletContext().getRealPath("/images");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // Full path where file will be stored
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);



        // Create and populate user model
        userModel user = new userModel();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordUtil.encrypt(password, username));
        user.setRole(role);
        System.out.println("Image saved at: " + filePath);

        user.setImage(fileName); 

        // Register user
        userDAO service = new userDAO();
        boolean isRegistered;
		try {
			isRegistered = service.registerUser(user);
		

	        if (isRegistered) {
	        	System.out.println("Registered");
	            response.sendRedirect(request.getContextPath() + "/login?registered=true");
	        } else {
	            request.setAttribute("errorMessage", "Registration failed. Username or email may already be in use.");
	            request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Utility to extract filename from Part header
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1).replace("\\", "/");
            }
        }
        return null;
    }
}
