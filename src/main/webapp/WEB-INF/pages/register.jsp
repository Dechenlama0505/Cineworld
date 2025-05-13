<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEWORLD - Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
    <div class="container">
        <div class="left-section">
            <div class="logo">
                <span class="logo-cine">CINE</span><span class="logo-world">WORLD</span>
            </div>
            <div class="greeting">
                <h1>HELLO</h1>
                <h2>Cinephile.</h2>
            </div>
        </div>
        
        <div class="right-section">
            <div class="form-container">
                <h2>Create <span>Account</span></h2>
                
                <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validateForm()">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                        <div id="emailError" class="error-message"></div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" id="firstName" name="firstName" maxlength="30" required>
                            <div id="firstNameError" class="error-message"></div>
                        </div>
                        
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" id="lastName" name="lastName" maxlength="30" required>
                            <div id="lastNameError" class="error-message"></div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" required>
                        <div id="usernameError" class="error-message"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="phoneNumber">Phone Number</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber" maxlength="10" required>
                        <div id="phoneError" class="error-message"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" maxlength="8" required>
                        <div id="passwordError" class="error-message"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" maxlength="8" required>
                        <div id="confirmPasswordError" class="error-message"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select id="role" name="role" required>
                            <option value="">Select a role</option>
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                        <div id="roleError" class="error-message"></div>
                    </div>
                    
                    <button type="submit" class="submit-btn">Sign Up</button>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        function validateForm() {
            let isValid = true;
            
            // Reset all error messages
            document.querySelectorAll('.error-message').forEach(el => {
                el.textContent = '';
            });
            
            // Email validation - must end with @gmail.com
            const email = document.getElementById('email').value;
            if (!email) {
                document.getElementById('emailError').textContent = "Please fill in";
                isValid = false;
            } else if (!email.endsWith('@gmail.com')) {
                document.getElementById('emailError').textContent = "Email must end with @gmail.com";
                isValid = false;
            }
            
            // First name validation - no numbers allowed
            const firstName = document.getElementById('firstName').value;
            if (!firstName) {
                document.getElementById('firstNameError').textContent = "Please fill in";
                isValid = false;
            } else if (/\d/.test(firstName)) {
                document.getElementById('firstNameError').textContent = "Numbers are not allowed";
                isValid = false;
            }
            
            // Last name validation - no numbers allowed
            const lastName = document.getElementById('lastName').value;
            if (!lastName) {
                document.getElementById('lastNameError').textContent = "Please fill in";
                isValid = false;
            } else if (/\d/.test(lastName)) {
                document.getElementById('lastNameError').textContent = "Numbers are not allowed";
                isValid = false;
            }
            
            // Username validation
            const username = document.getElementById('username').value;
            if (!username) {
                document.getElementById('usernameError').textContent = "Please fill in";
                isValid = false;
            }
            
            // Phone number validation - 10 digits, starting with 97 or 98
            const phone = document.getElementById('phoneNumber').value;
            if (!phone) {
                document.getElementById('phoneError').textContent = "Please fill in";
                isValid = false;
            } else if (!/^(97|98)\d{8}$/.test(phone)) {
                document.getElementById('phoneError').textContent = "Must be 10 digits starting with 97 or 98";
                isValid = false;
            }
            
            // Password validation - at least one uppercase, one number, one special character
            const password = document.getElementById('password').value;
            if (!password) {
                document.getElementById('passwordError').textContent = "Please fill in";
                isValid = false;
            } else if (!/^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).*$/.test(password)) {
                document.getElementById('passwordError').textContent = "Must contain at least one uppercase letter, one number, and one special character";
                isValid = false;
            }
            
            // Confirm password validation
            const confirmPassword = document.getElementById('confirmPassword').value;
            if (!confirmPassword) {
                document.getElementById('confirmPasswordError').textContent = "Please fill in";
                isValid = false;
            } else if (password !== confirmPassword) {
                document.getElementById('confirmPasswordError').textContent = "Password does not match";
                isValid = false;
            }

            // Role validation
            const role = document.getElementById('role').value;
            if (!role) {
                document.getElementById('roleError').textContent = "Please select a role";
                isValid = false;
            }

            return isValid;
        }
        
        // Real-time validation for specific fields
        document.getElementById('phoneNumber').addEventListener('input', function() {
            this.value = this.value.replace(/[^0-9]/g, '').substring(0, 10);
        });
        
        document.getElementById('firstName').addEventListener('input', function() {
            this.value = this.value.replace(/[0-9]/g, '');
        });
        
        document.getElementById('lastName').addEventListener('input', function() {
            this.value = this.value.replace(/[0-9]/g, '');
        });
        
        document.getElementById('confirmPassword').addEventListener('input', function() {
            const password = document.getElementById('password').value;
            if (this.value !== password) {
                document.getElementById('confirmPasswordError').textContent = "Password does not match";
            } else {
                document.getElementById('confirmPasswordError').textContent = "";
            }
        });
    </script>
</body>
</html>