<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEWORLD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container"></div>  
        <header>
            <div class="logo">
                <span class="logo-cine">CINE</span><span class="logo-world">WORLD</span>
            </div>
            
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/home" class="${pageContext.request.servletPath eq '/home.jsp' ? 'active' : ''}">HOME</a></li>
                    <li><a href="${pageContext.request.contextPath}/schedule" class="${pageContext.request.servletPath eq '/schedule.jsp' ? 'active' : ''}">SCHEDULE</a></li>
                    <li><a href="${pageContext.request.contextPath}/book" class="${pageContext.request.servletPath eq '/book.jsp' ? 'active' : ''}">BOOK</a></li>
					<li><a href="${pageContext.request.contextPath}/contactus" class="${pageContext.request.servletPath eq '/contactus' ? 'active' : ''}">CONTACT US</a></li>
                </ul>
            </nav>
        </header>
        <div class="separator"></div>