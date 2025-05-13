<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminheader.jsp" %>

<%
    // Basic admin access protection
    Object userObj = session.getAttribute("user");
    if (userObj == null || !"admin".equals(((com.cineworld.model.userModel) userObj).getRole())) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminhome.css">

<div class="main-container">
    <div class="admin-home-section">
        
        <!-- Featured Movie -->
        <div class="featured-movie">
            <div class="movie-info">
                <h1>MONEY HEIST</h1>
                <p>When the national mint and a touring school group are held hostage by robbers, police believe that the thieves have no way out. Little do they know that the thieves have a bigger plan in store.</p>
                <button class="coming-soon-btn">COMING SOON</button>
            </div>
            <div class="movie-poster">
                <img src="${pageContext.request.contextPath}/images/money-heist.jpg" alt="Money Heist">
                <div class="watermark">WorthCreate</div>
            </div>
        </div>

        <!-- Movie Grid -->
        <div class="movie-grid">
            <div class="movie-card">
                <div class="movie-image">
                    <img src="${pageContext.request.contextPath}/images/dune.jpg" alt="Dune">
                </div>
                <div class="movie-details">
                    <h3>DUNE</h3>
                    <div class="genres"><span>Action | Drama</span></div>
                </div>
            </div>

            <div class="movie-card">
                <div class="movie-image">
                    <img src="${pageContext.request.contextPath}/images/eternals.jpg" alt="Eternals">
                </div>
                <div class="movie-details">
                    <h3>ETERNALS</h3>
                    <div class="genres"><span>Action | Sci-Fi</span></div>
                </div>
            </div>

            <div class="movie-card">
                <div class="movie-image">
                    <img src="${pageContext.request.contextPath}/images/crazy-rich-asians.jpg" alt="Crazy Rich Asians">
                </div>
                <div class="movie-details">
                    <h3>CRAZY RICH ASIANS</h3>
                    <div class="genres"><span>Romance | Comedy</span></div>
                </div>
            </div>

            <div class="movie-card">
                <div class="movie-image">
                    <img src="${pageContext.request.contextPath}/images/inception.jpg" alt="Inception">
                </div>
                <div class="movie-details">
                    <h3>INCEPTION</h3>
                    <div class="genres"><span>Action | Thriller | Sci-Fi</span></div>
                </div>
            </div>
        </div>

    </div>
</div>
