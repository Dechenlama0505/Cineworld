package com.cineworld.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;

/**
 * cookiesUtil.java
 *
 * A utility class that simplifies working with HTTP cookies in the Cineworld web application.
 *
 * Responsibilities:
 * - addCookie(HttpServletResponse, String, String, int): Adds a cookie with a specified max‑age.
 * - getCookie(HttpServletRequest, String): Retrieves a cookie by name, or null if it doesn’t exist.
 * - deleteCookie(HttpServletResponse, String): Deletes a cookie immediately by setting its max‑age to 0.
 *
 * Author: Dechen Lama
 */

public class cookiesUtil {

 
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/"); 
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies()).filter(cookie -> name.equals(cookie.getName())).findFirst()
                    .orElse(null);
        }
        return null;
    }


    public static void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/"); 
        response.addCookie(cookie);
    }
}