package com.cineworld.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for managing HTTP sessions in a web application. Provides
 * methods to set, get, remove session attributes and invalidate sessions.
 */
public class sessionUtil {

    // Set an attribute in the session
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    // Get an attribute from the session
    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    // Remove an attribute from the session
    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    // Invalidate the session
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
