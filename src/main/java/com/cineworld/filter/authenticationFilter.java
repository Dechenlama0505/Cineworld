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

    private static final String[] PUBLIC_URIS = {
        "/login", "/", "/register", "/contactus", "/aboutus", "/schedule"
    };

    private static final String[] STATIC_RESOURCES = {
        "/css", "/images"
    };

    private static final String[] ADMIN_URIS = {
        "/adminhome", "/adminwelcome", "/book", "/contactrequest", "/aboutus"
    };

    private static final String[] USER_URIS = {
        "/booknow", "/home", "/schedule", "/contactus"
    };

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
        String path = uri.substring(contextPath.length()); // Remove context path for comparison

        Object userObj = sessionUtil.getAttribute(req, "user");
        boolean isLoggedIn = userObj != null;
        String role = sessionUtil.getAttribute(req, "role") != null
                ? sessionUtil.getAttribute(req, "role").toString()
                : "";

        boolean isPublic = false;
        for (String publicPath : PUBLIC_URIS) {
            if (path.startsWith(publicPath)) {
                isPublic = true;
                break;
            }
        }

        boolean isStatic = false;
        for (String staticPath : STATIC_RESOURCES) {
            if (path.startsWith(staticPath)) {
                isStatic = true;
                break;
            }
        }

        boolean isAdminPage = false;
        for (String adminPath : ADMIN_URIS) {
            if (path.startsWith(adminPath)) {
                isAdminPage = true;
                break;
            }
        }

        boolean isUserPage = false;
        for (String userPath : USER_URIS) {
            if (path.startsWith(userPath)) {
                isUserPage = true;
                break;
            }
        }

        if (isPublic || isStatic) {
            chain.doFilter(request, response);
        } else if (isLoggedIn) {
            if ("admin".equals(role) && isAdminPage) {
                chain.doFilter(request, response);
            } else if ("user".equals(role) && isUserPage) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + (role.equals("admin") ? "/adminhome" : "/home"));
            }
        } else {
            res.sendRedirect(contextPath + "/login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
