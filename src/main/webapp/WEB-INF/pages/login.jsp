<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEWORLD - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

    <div class="container">
        <div class="login-container">
            <%
                String registered = request.getParameter("registered");
                if ("true".equals(registered)) {
            %>
                <div class="success-message" style="color: green; background-color: #e0ffe0; padding: 10px; border-radius: 5px; margin: 0 auto 15px auto; text-align: center; max-width: 350px;">
                    Registration successful! Please log in.
                </div>
            <%
                }
            %>

            <div class="logo">
                <span class="logo-cine">CINE</span><span class="logo-world">WORLD</span>
            </div>
            
            <div class="form-container">
                <h2>Log in <span>with</span></h2>
                <h3>your email address</h3>
                
                <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" required>
                        <div id="loginError" class="error-message">
                            <% if(request.getAttribute("errorMessage") != null) { %>
                                <%= request.getAttribute("errorMessage") %>
                            <% } %>
                        </div>
                    </div>
                    
                    <button type="submit" class="submit-btn">Log in</button>
                </form>
                <form id="register" action="${pageContext.request.contextPath}/register" method="get">
                    <input type="submit" value="Register"/>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            
            if (!username || !password) {
                e.preventDefault();
                document.getElementById('loginError').textContent = "Please fill in all fields";
            }
        });
    </script>
</body>
</html>
