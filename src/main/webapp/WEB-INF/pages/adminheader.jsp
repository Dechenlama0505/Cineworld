<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEWORLD ADMIN</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admincommon.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container"></div>  
    <header>
        <div class="logo">
            <span class="logo-cine">CINE</span><span class="logo-world">WORLD ADMIN</span>
        </div>

        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/adminhome" class="${pageContext.request.servletPath eq '/adminhome' ? 'active' : ''}">HOME</a></li>
                
                <!-- ✅ Fixed link below -->
                <li><a href="${pageContext.request.contextPath}/moviecontroller" class="${pageContext.request.servletPath eq '/moviecontroller' ? 'active' : ''}">MOVIE</a></li>
                
                <li><a href="${pageContext.request.contextPath}/admin/bookcontrol" class="${pageContext.request.servletPath eq '/admin/book' ? 'active' : ''}">BOOK</a></li>
                <li><a href="${pageContext.request.contextPath}/aboutus" class="${pageContext.request.servletPath eq '/aboutus' ? 'active' : ''}">ABOUT US</a></li>
                
                <c:choose>
                    <c:when test="${not empty sessionScope.admin}">
                        <li><a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/login">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </header>
    <div class="separator"></div>
