<%@page import="com.cineworld.model.userModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// Basic admin access protection
userModel userObj = (com.cineworld.model.userModel) session.getAttribute("user");
if (userObj == null || !"admin".equals((userObj).getRole())) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/welcomeadmin.css">

<div class="main-container">
	<div class="welcome-section">
		<div class="welcome-message">
			<div class="form-row">
				<div class="form-group">
					<h1>
						Welcome back, <span class="admin-name"><%=userObj.getUsername()%></span>
					</h1>
					<p>We're glad to have you back. Dive in and manage your movie
						booking system with confidence. Everything is up to date and ready
						for your expertise.</p>
				</div>
			</div>

			<div class="form-actions">
				<!-- Ensure this path matches the actual admin home page -->
				<a href="${pageContext.request.contextPath}/adminhome"
					class="action-btn">NEXT</a>
			</div>
		</div>
	</div>
</div>
