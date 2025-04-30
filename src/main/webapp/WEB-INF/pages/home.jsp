<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">

<div class="main-container">
    <div class="featured-movie">
        <div class="movie-info">
            <h1>MONEY HEIST</h1>
            <p>When the national mint and a touring school group are held hostage by robbers, police believe that the thieves have no way out. Little do they know that the thieves have a bigger plan in store.</p>
            <button class="coming-soon-btn">COMING SOON</button>
        </div>
        <div class="movie-poster">
            <img src="${pageContext.request.contextPath}/images/money-heist.jpg" alt="Money Heist">
        </div>
    </div>
    
    <div class="movie-grid">
        <div class="movie-card">
            <div class="movie-image">
                <img src="${pageContext.request.contextPath}/images/dune.jpg" alt="Dune">
            </div>
            <div class="movie-details">
                <h3>Dune</h3>
                <p>Action | Drama</p>
                <a href="${pageContext.request.contextPath}/schedule" class="schedule-btn">Schedule</a>
            </div>
        </div>
        
        <div class="movie-card">
            <div class="movie-image">
                <img src="${pageContext.request.contextPath}/images/eternals.jpg" alt="Eternals">
            </div>
            <div class="movie-details">
                <h3>Eternals</h3>
                <p>Action | Sci-Fi</p>
                <a href="${pageContext.request.contextPath}/schedule" class="schedule-btn">Schedule</a>
            </div>
        </div>
        
        <div class="movie-card">
            <div class="movie-image">
                <img src="${pageContext.request.contextPath}/images/crazy-rich-asians.jpg" alt="Crazy Rich Asians">
            </div>
            <div class="movie-details">
                <h3>Crazy Rich Asians</h3>
                <p>Romantic | Comedy</p>
                <a href="${pageContext.request.contextPath}/schedule" class="schedule-btn">Schedule</a>
            </div>
        </div>
        
        <div class="movie-card">
            <div class="movie-image">
                <img src="${pageContext.request.contextPath}/images/inception.jpg" alt="Inception">
            </div>
            <div class="movie-details">
                <h3>Inception</h3>
                <p>Horror | Thriller | Action</p>
                <a href="${pageContext.request.contextPath}/schedule" class="schedule-btn">Schedule</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>