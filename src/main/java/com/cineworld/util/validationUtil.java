package com.cineworld.util;
/**
 * validationUtil.java
 * 
 * Utility class providing static methods for common input validations.
 * 
 * Includes methods to check for null or empty strings, validate email format,
 * phone number format, username constraints, and basic password length.
 * 
 * Author: Dechen Lama
 */

public class validationUtil {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{7,15}");
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9_]{4,20}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
