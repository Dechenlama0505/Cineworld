<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contactus.css">

<div class="main-container">
    <div class="contact-container">
        <div class="contact-info">
            <h1>Get In Touch</h1>
            
            <div class="info-item">
                <div class="icon">
                    <i class="fas fa-map-marker-alt"></i>
                </div>
                <div class="text">
                    <p>Baluwater, Naxal</p>
                </div>
            </div>
            
            <div class="info-item">
                <div class="icon">
                    <i class="fas fa-phone-alt"></i>
                </div>
                <div class="text">
                    <p>01-4839501</p>
                    <p>9860093849 | 9800948321</p>
                </div>
            </div>
            
            <div class="info-item">
                <div class="icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="text">
                    <p>Cineworld.nepal@gmail.com</p>
                </div>
            </div>
        </div>
        
        <div class="contact-form">
            <h2>Send a Message</h2>
            <form id="messageForm" action="${pageContext.request.contextPath}/contactus" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" maxlength="50" required>
                </div>
                
                <div class="form-group">
                    <label for="message">Message</label>
                    <textarea id="message" name="message" maxlength="200" required></textarea>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="submit-btn">Submit</button>
                </div>
            </form>
        </div>
    </div>
    
    <div class="map-container">
        <img src="${pageContext.request.contextPath}/images/map.jpg" alt="Map Location">
    </div>
</div>

<script>
document.getElementById('messageForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Get form values
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;
    
    // Simple validation
    if (name.trim() === '' || message.trim() === '') {
        alert('Please fill in all fields');
        return;
    }
    
    // Show success message
    alert('Message sent! Your opinion matters.');
    
    // Reset form
    this.reset();
});
</script>

<%@ include file="footer.jsp" %>