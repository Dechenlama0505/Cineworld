<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEWORLD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .user-logo {
            height: 40px;
            margin-left: 10px;
            margin-right: 10px;
            cursor: pointer;
            vertical-align: middle;
        }
        .logout-btn {
            color: #ffffff;
            background-color: #dc3545;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            vertical-align: middle;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
        .nav-link {
            vertical-align: middle;
        }
        .profile-icon {
            font-size: 16px;
            text-decoration: none;
            margin-left: 10px;
            margin-right: 10px;
            cursor: pointer;
            color: white;
            vertical-align: middle;
        }
        nav ul {
            list-style-type: none;
            display: flex;
            align-items: center;
        }
        nav ul li {
            margin: 0 10px;
        }
        .logo {
            font-size: 24px;
            font-weight: bold;
        }
        .logo-cine {
            color: red;
        }
        .logo-world {
            color: white;
        }
        header {
            background-color: #333;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>

<header>
    <div class="logo">
        <span class="logo-cine">CINE</span><span class="logo-world">WORLD</span>
    </div>

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home" class="${pageContext.request.servletPath eq '/home' ? 'active' : ''}">HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/schedule" class="${pageContext.request.servletPath eq '/schedule' ? 'active' : ''}">SCHEDULE</a></li>
            <li><a href="${pageContext.request.contextPath}/booknow" class="${pageContext.request.servletPath eq '/booknow' ? 'active' : ''}">BOOK NOW</a></li>
            <li><a href="${pageContext.request.contextPath}/contactus" class="${pageContext.request.servletPath eq '/contactus' ? 'active' : ''}">CONTACT US</a></li>
			<li><a href="${pageContext.request.contextPath}/userProfile" class="username-button">${sessionScope.user.username}</a></li>
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

</body>
</html>