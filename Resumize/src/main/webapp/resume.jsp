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
            <h2>Create Your Resume</h2>
            <form action="resume" method="post" enctype="multipart/form-data">
                <div>
                    <label for="resumeTitle">Resume Title:</label>
                    <input type="text" id="resumeTitle" name="resumeTitle" required>
                </div>
                <div>
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div>
                    <label for="profilePicture">Profile Picture:</label>
                    <input type="file" id="profilePicture" name="profilePicture" accept="image/*">
                </div>

                <div>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div>
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>

                <div>
                    <label for="linkedin">LinkedIn:</label>
                    <input type="url" id="linkedin" name="linkedin" required>
                </div>

                <div>
                    <label for="location">Location:</label>
                    <input type="text" id="location" name="location" required>
                </div>

                <div>
                    <label for="bio">Bio:</label>
                    <textarea id="bio" name="bio" rows="5" required></textarea>
                </div>

                <div>
                    <label for="skills">Skills:</label>
                    <textarea id="skills" name="skills" rows="5" required></textarea>
                </div>

                <div>
                    <label for="experience">Experience:</label>
                    <textarea id="experience" name="experience" rows="5" required></textarea>
                </div>

                <div>
                    <label for="education">Education:</label>
                    <textarea id="education" name="education" rows="5" required></textarea>
                </div>

                <div>
                    <label for="interests">Interests:</label>
                    <textarea id="interests" name="interests" rows="5" required></textarea>
                </div>
                <div>
                    <label for="languages">Languages Known:</label>
                    <textarea id="languages" name="languages" rows="5" required></textarea>
                </div>

                <div>
                    <label for="resumeTemplate">Resume Template:</label>
                    <select id="resumeTemplate" name="resumeTemplate">
                        <option value="minimalTemplate">Minimal Template</option>
                        <option value="modernTemplate">Modern Template</option>
                        <option value="creativeTemplate">Creative Template</option>
                    </select>
                </div>

                <input type="submit" value="Generate Resume">
            </form>
        <% } else { %>
            <p>You are not logged in. Please <a href="login.jsp">login</a> to create a resume.</p>
        <% } %>
    </div>

    <div>
        &copy; 2024 Resumize - Explore the best templates and start your journey! All Rights Reserved by Aditi-Aashika-Snehals.
    </div>
</body>
</html>