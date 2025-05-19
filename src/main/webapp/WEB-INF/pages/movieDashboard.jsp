<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CINE WORLD - Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/movieDashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="container">
   <%@ include file="adminheader.jsp" %>
   
        
        <div class="dashboard-content">
            <div class="stat-box">
                <h2>Total Users</h2>
                <div class="stat-value">${totalUsers}</div>
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
            </div>
            
            <div class="stat-box">
                <h2>Total Movie</h2>
                <div class="stat-value">${totalMovies}</div>
                <div class="stat-icon">
                    <i class="fas fa-film"></i>
                </div>
            </div>
            
            <div class="stat-box">
                <h2>Total Seat Booked</h2>
                <div class="stat-value">${totalSeatsBooked}</div>
                <div class="stat-icon">
                    <i class="fas fa-chair"></i>
                </div>
            </div>
        </div>
    
    <script>
        // You can add JavaScript for any client-side functionality here
    </script>
</body>
</html>