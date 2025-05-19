<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CINE WORLD - User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userProfile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        body {
            background-color: #000;
            color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding-top: 20px;
        }
        .profile-picture {
            margin: 30px auto 10px;
        }
        .profile-picture img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #007bff;
        }
        .profile-card {
            background-color: #fff;
            color: #000;
            max-width: 500px;
            margin: 20px auto;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.3);
        }
        .profile-info .info-row {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
            padding-bottom: 5px;
            border-bottom: 1px solid #eee;
        }
        .profile-info label {
            font-weight: bold;
        }
        .error-message, .info-message {
            margin-top: 20px;
            font-weight: bold;
        }
        .error-message {
            color: red;
        }
        .info-message {
            color: limegreen;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container">
    <!-- Error and Info Messages -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty info}">
        <div class="info-message">${info}</div>
    </c:if>

    <!-- If user is not logged in -->
    <c:if test="${empty userModel}">
        <div class="error-message">
            Unable to load profile. Please <a href="${pageContext.request.contextPath}/login" style="color: #00f;">log in</a> again.
        </div>
    </c:if>

    <!-- Profile Section -->
    <c:if test="${not empty userModel}">
        <div class="profile-picture">
            <img src="${pageContext.request.contextPath}/images/${userModel.image}" alt="Profile Picture">
        </div>

        <div class="profile-card">
            <div class="profile-info">
                <div class="info-row">
                    <label>User ID:</label>
                    <span>${userModel.userId}</span>
                </div>
                <div class="info-row">
                    <label>First Name:</label>
                    <span>${userModel.firstName}</span>
                </div>
                <div class="info-row">
                    <label>Last Name:</label>
                    <span>${userModel.lastName}</span>
                </div>
                <div class="info-row">
                    <label>Username:</label>
                    <span>${userModel.username}</span>
                </div>
                <div class="info-row">
                    <label>Email:</label>
                    <span>${userModel.email}</span>
                </div>
                <div class="info-row">
                    <label>Phone Number:</label>
                    <span>${userModel.phoneNumber}</span>
                </div>
                <div class="info-row">
                    <label>Role:</label>
                    <span>${userModel.role}</span>
                </div>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>
