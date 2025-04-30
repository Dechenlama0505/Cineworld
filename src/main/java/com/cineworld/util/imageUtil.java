package com.cineworld.util;

import jakarta.servlet.http.HttpServletRequest;

public class imageUtil {

    /**
     * Returns the complete image path relative to the context.
     * 
     * @param request HttpServletRequest for context path
     * @param imageName name of the image file
     * @return full image path as String
     */
    public static String getImagePath(HttpServletRequest request, String imageName) {
        return request.getContextPath() + "/images/" + imageName;
    }

    /**
     * Verifies if the image name is valid (optional basic check).
     * 
     * @param imageName file name
     * @return true if it's a valid image format
     */
    public static boolean isValidImage(String imageName) {
        return imageName != null && imageName.matches(".*\\.(jpg|jpeg|png|gif|bmp)$");
    }
}

