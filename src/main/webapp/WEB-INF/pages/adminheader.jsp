<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <li><a href="${pageContext.request.contextPath}/admin/home" class="${pageContext.request.servletPath eq '/admin/home' ? 'active' : ''}">HOME</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/contactrequests" class="${pageContext.request.servletPath eq '/admin/contactrequests' ? 'active' : ''}">CONTACT REQUEST</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/bookcontrol" class="${pageContext.request.servletPath eq '/admin/book' ? 'active' : ''}">BOOK</a></li>
                <li><a href="${pageContext.request.contextPath}/aboutus" class="${pageContext.request.servletPath eq '/aboutus' ? 'active' : ''}">ABOUT US</a></li>
            </ul>
        </nav>
    </header>
    <div class="separator"></div>
