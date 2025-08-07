<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Resumize</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Register</h2>
       <div>
            <h3>Create a resume you are proud of</h3>
            <div><span>🕒</span>Save time with hassle-free templates</div>
            <div><span>🏆</span>Beat the competition using actionable, contextual advice</div>
            <div><span>📈</span>Highlight key achievements with memorable visuals</div>
            <div ><span>📄</span>Get inspired by 200+ Free Resume Examples and Templates</div>
        </div>
    <div>
        <form method="post" action="register">
            <label>Username:</label><br>
            <input type="text" name="username" required/><br>
            
            <label>Email:</label><br>
            <input type="text" name="email" required/><br>
            
            <label>Password:</label><br>
            <input type="password" name="password" required/><br>
            
            <button type="submit">Register</button>
        </form>

        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div>
                <strong>Error:</strong> <%= error %>
            </div>
        <% } %>
    </div>

    <div>
        &copy; 2024 Resumize - Explore the best templates and start your journey! All Rights Reserved by Aditi-Aashika-Snehals.
    </div>
</body>
</html>