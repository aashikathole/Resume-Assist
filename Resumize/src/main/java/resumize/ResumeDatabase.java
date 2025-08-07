package resumize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResumeDatabase {

    public void insertResume(Resume resume, int userId) {
        String sql = "INSERT INTO resumes (resumeTitle, userId, name, profilePicture, email, phone, linkedin, "
                   + "location, bio, skills, experience, education, interests, languages, resumeTemplate)"
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
    
            pstmt.setString(1, resume.getResumeTitle());
            pstmt.setInt(2, userId);
            pstmt.setString(3, resume.getName());
            pstmt.setString(4, null); // Insert null for profilePicture
            pstmt.setString(5, resume.getEmail());
            pstmt.setString(6, resume.getPhone());
            pstmt.setString(7, resume.getLinkedin());
            pstmt.setString(8, resume.getLocation());
            pstmt.setString(9, resume.getBio());
            pstmt.setString(10, resume.getSkills());
            pstmt.setString(11, resume.getExperience());
            pstmt.setString(12, resume.getEducation());
            pstmt.setString(13, resume.getInterests());
            pstmt.setString(14, resume.getLanguages());
            pstmt.setString(15, resume.getResumeTemplate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}