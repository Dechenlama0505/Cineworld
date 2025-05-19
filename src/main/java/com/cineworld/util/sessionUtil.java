package com.cineworld.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
/**
 * sessionUtil.java
 * 
 * A utility class to simplify session management operations in the web application.
 * 
 * Responsibilities:
 * - Set, retrieve, and remove session attributes.
 * - Invalidate user sessions when necessary.
 * 
 * Author: Dechen Lama
 */

public class sessionUtil {
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true); 
        session.setAttribute(key, value);
    }

    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false); 
        return (session != null) ? session.getAttribute(key) : null;
    }

    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
