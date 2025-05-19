package com.cineworld.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * imageUtil.java
 *
 * Utility class for handling image-related operations in the Cineworld application.
 * Provides methods to build image paths and validate image file names/extensions.
 *
 * Methods:
 * - getImagePath(HttpServletRequest, String): Constructs the context-relative URL for an image.
 * - isValidImage(String): Validates if the image file name has an acceptable image extension.
 *
 * Author: Dechen Lama
 */

public class imageUtil {


    public static String getImagePath(HttpServletRequest request, String imageName) {
        return request.getContextPath() + "/images/" + imageName;
    }


    public static boolean isValidImage(String imageName) {
        return imageName != null && imageName.matches(".*\\.(jpg|jpeg|png|gif|bmp)$");
    }
}

