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

/**
 * Servlet implementation class registerController
 * 
 * Handles user registration requests including form display and form submission.
 * Supports multipart form data for uploading a user profile image.
 * 
 * <p>Functionality:
 * <ul>
 *   <li>doGet(): Forwards to the registration JSP page to display the registration form.</li>
 *   <li>doPost(): Processes registration form data, handles image upload, encrypts the password,
 *       and registers the user using userDAO. On success, redirects to login page with a success flag.
 *       On failure, forwards back to the registration form with an error message.</li>
 *   <li>getFileName(Part): Extracts the uploaded file's original filename from the multipart request part.</li>
 * </ul>
 * </p>
 * 
 * <p>File upload limits:
 * <ul>
 *   <li>Max file size: 5 MB</li>
 *   <li>Max request size: 10 MB</li>
 *   <li>File size threshold for memory buffering: 1 MB</li>
 * </ul>
 * </p>
 * 
 * @author Dechen Lama
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, 
    maxFileSize = 1024 * 1024 * 5,   
    maxRequestSize = 1024 * 1024 * 10 
)
public class registerController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register get");
        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    }

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached here");


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

        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);

 
        String uploadDir = getServletContext().getRealPath("/images");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);




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
			e.printStackTrace();
		}
    }


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
