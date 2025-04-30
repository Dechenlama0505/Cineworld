package com.cineworld.filter;

import java.io.IOException;

import com.cineworld.util.sessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class authenticationFilter implements Filter {

    private static final String LOGIN_CONTROLLER = "/LoginController";
    private static final String REGISTER_CONTROLLER = "/RegisterController";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String LOGIN_JSP = "/login";
    private static final String CONTACT = "/contactus";
    private static final String REGISTER_JSP = "/register";
    private static final String CSS_FOLDER = "/css";
    private static final String IMAGES_FOLDER = "/images";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        boolean isResource = uri.startsWith(contextPath + CSS_FOLDER) || uri.startsWith(contextPath + IMAGES_FOLDER);
        boolean isLoginPage = uri.endsWith(LOGIN_JSP) || uri.endsWith(LOGIN_CONTROLLER);
        boolean isRegisterPage = uri.endsWith(REGISTER_JSP) || uri.endsWith(REGISTER_CONTROLLER);
        boolean isHomePage = uri.endsWith(HOME) || uri.equals(contextPath + ROOT);
        boolean isContactUs =  uri.endsWith(CONTACT);
        boolean isLoggedIn = sessionUtil.getAttribute(req, "loggedInUser") != null;

        if (isResource || isLoginPage || isRegisterPage || isHomePage || isContactUs) {
            chain.doFilter(request, response);
            return;
        }

        if (!isLoggedIn) {
            res.sendRedirect(contextPath + LOGIN_JSP);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}