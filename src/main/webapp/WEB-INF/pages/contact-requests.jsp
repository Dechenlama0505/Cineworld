<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminheader.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contactrequest.css">

<div class="main-container">
    <div class="booking-form-section">
        <div class="booking-form">
            <h2 class="messages-title">CONTACT REQUESTS</h2>

            <div class="messages-list">
                <!-- Iterate through the messages list passed from the controller -->
                <c:forEach items="${messages}" var="message">
                    <div class="message-item">
                        <div class="form-row">
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" value="${message.name}" readonly>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" value="${message.email}" readonly>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group full-width">
                                <label>Message</label>
                                <textarea readonly>${message.content}</textarea>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- If there are no messages, display a message -->
                <c:if test="${empty messages}">
                    <p>No messages to display.</p>
                </c:if>
            </div>
        </div>

        <!-- Form to send a response to a user -->
        <div class="response-form">
            <h2 class="messages-title">SEND RESPONSE</h2>
            <div class="booking-form">
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="user">To User</label>
                        <input type="text" id="user" name="user" form="responseForm" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group full-width">
                        <label for="response">Response</label>
                        <textarea id="response" name="response" form="responseForm" placeholder="Type your response here..." required></textarea>
                    </div>
                </div>
                <div class="form-actions">
                    <!-- Form that submits the response -->
                    <form id="responseForm" action="${pageContext.request.contextPath}/admin/send-response" method="post">
                        <button type="submit" class="action-btn">SEND</button>
                        <button type="reset" class="action-btn">CLEAR</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
