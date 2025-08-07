<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="resumize.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resumize</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div>
        <% 
           User user = (User) session.getAttribute("user");
           if (user != null) { 
        %>
            <h2>Welcome, <%= user.getUsername() %>!</h2>
            <h3>This is your Resumize dashboard. Here you can create and manage your resumes.</h3>
            <div>
                <a href="resume.jsp">Create New Resume</a>
            </div>
            <div>
                <a href="resumeManager.jsp">Manage your Resumes</a>
            </div>
            <% if (request.getAttribute("message") != null) { %>
                <div>
                    <strong>Success!</strong> <%= request.getAttribute("message") %>
                </div>
            <% } %>
            <% if (request.getAttribute("error") != null) { %>
                <div>
                    <strong>Error:</strong> <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <% if (request.getAttribute("downloadLink") != null) { %>
                <div>
                    <br/>
                    <a href="<%= request.getAttribute("downloadLink") %>">Download PDF</a>
                </div>
            <% } %>
        <% } else { %>
            <p>You are not logged in. Please <a href="login.jsp">login</a> to access the dashboard.</p>
        <% } %>
    </div>
    
    <div>
        &copy; 2024 Resumize - Explore the best templates and start your journey! All Rights Reserved by Aditi-Aashika-Snehals.
    </div>
</body>
</html>