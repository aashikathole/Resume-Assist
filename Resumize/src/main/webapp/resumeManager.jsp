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
            <h2>Manage Your Resumes</h2>
           
             <table>
               <thead>
                   <tr>
                       <th>Resume Title</th>
                       <th>Date Created</th>
                       <th>Actions</th>
                   </tr>
               </thead>
               <tbody>
                   <tr>
                       <td>Software Developer Resume</td>
                       <td>2024-01-15</td>
                       <td><a href="editResume.jsp?id=1">Edit</a></td>
                   </tr>
                   <tr>
                       <td>Project Manager Resume</td>
                       <td>2024-02-10</td>
                       <td><a href="editResume.jsp?id=2">Edit</a></td>
                   </tr>
               </tbody>
           </table>

        <% } else { %>
            <p>You are not logged in. Please <a href="login.jsp">login</a> to create a resume.</p>
        <% } %>
     </div>
</body>
</html>