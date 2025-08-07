<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Resumize</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Login</h2>
       <div>
            <h3>Create a resume you are proud of</h3>
            <div><span>🕒</span>Save time with hassle-free templates</div>
            <div><span>🏆</span>Beat the competition using actionable, contextual advice</div>
            <div><span>📈</span>Highlight key achievements with memorable visuals</div>
            <div ><span>📄</span>Get inspired by 200+ Free Resume Examples and Templates</div>
        </div>
    <div>
        <form method="post" action="login">
            <label>Username:</label><br>
            <input type="text" name="username" required/><br>
            <label>Password:</label><br>
            <input type="password" name="password" required/><br>
            <button type="submit">Login</button>
        </form>

        <% if (request.getAttribute("error") != null) { %>
            <div>
                <strong>Error:</strong> <%= request.getAttribute("error") %>
            </div>
        <% } %>
    </div>

    <div>
        &copy; 2024 Resumize - Explore the best templates and start your journey! All Rights Reserved by Aditi-Aashika-Snehals.
    </div>
</body>
</html>