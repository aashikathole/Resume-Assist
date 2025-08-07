package resumize;

import resumize.Resume;
import resumize.User;
import resumize.PDFGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("unused")
@WebServlet("/resume")
@MultipartConfig // Needed for handling file uploads
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "Resumize";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Resume resume = new Resume();
        resume.setResumeTitle(request.getParameter("resumeTitle"));
        resume.setUserId(user.getId());
        resume.setName(request.getParameter("name"));
        resume.setEmail(request.getParameter("email"));
        resume.setPhone(request.getParameter("phone"));
        resume.setLinkedin(request.getParameter("linkedin"));
        resume.setLocation(request.getParameter("location"));
        resume.setBio(request.getParameter("bio"));
        resume.setSkills(request.getParameter("skills"));
        resume.setExperience(request.getParameter("experience"));
        resume.setEducation(request.getParameter("education"));
        resume.setInterests(request.getParameter("interests"));
        resume.setLanguages(request.getParameter("languages"));
        resume.setResumeTemplate(request.getParameter("resumeTemplate"));
        
        // Validate required fields
        if (resume.getName() == null || resume.getName().isEmpty()) {
            request.setAttribute("error", "Name cannot be empty.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            return;
        }

        // Get the absolute path to the save directory
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        // Ensure save directory exists
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            if (!saveDir.mkdirs()) {
                request.setAttribute("error", "Failed to create directory for saving resumes.");
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }
        }

        String filename = resume.getName().replaceAll("\\s+", "_") + "_" + resume.getResumeTitle().replaceAll("\\s+", "_") + "_Resume.pdf";
        String saveLocation = savePath + File.separator + filename;

        // Handle profile picture upload
        Part part = request.getPart("profilePicture");
        String imagePath = null;
        if (part != null && part.getSize() > 0) {
            // Validate that the file is an image
            String contentType = part.getContentType();
            if (!contentType.startsWith("image/")) {
                request.setAttribute("error", "Uploaded file is not an image.");
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }

            // Define a unique image filename using timestamp to prevent overwriting
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String imageFilename = "profile_" + resume.getName().replaceAll("\\s+", "_") + "_" + timestamp + ".jpg";
            imagePath = savePath + File.separator + imageFilename;

            try (InputStream input = part.getInputStream()) {
                Files.copy(input, Paths.get(imagePath));
                resume.setProfilePicture(imagePath); // Save the path to the resume object
            } catch (IOException e) {
                // Log the stack trace for debugging
                e.printStackTrace();

                request.setAttribute("error", "Error uploading profile picture: " + e.getMessage());
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }
        }

        String templateName = request.getParameter("resumeTemplate");
        if (!Arrays.asList("minimalTemplate", "modernTemplate", "creativeTemplate").contains(templateName)) {
            templateName = "minimalTemplate"; // Default template
        }
        resume.setResumeTemplate(templateName);
        
        // Save resume details to the database, excluding the profile picture path
        try {
        	ResumeDatabase resumeDB = new ResumeDatabase();
            resumeDB.insertResume(resume, user.getId()); // Pass the resume and user ID
        }catch (Exception edb) {
            // Log the stack trace for debugging
            edb.printStackTrace();
            request.setAttribute("error", "Failed to generate resume. Please try again. Error: " + edb.getMessage());
        }
        
        // Generate the PDF resume
        try {
            PDFGenerator.generateResume(resume, saveLocation);
            // Construct the download link relative to the webapp
            String downloadLink = SAVE_DIR + "/" + filename;
            request.setAttribute("downloadLink", downloadLink); // Pass the download link to JSP
            request.setAttribute("message", "Resume generated successfully! You can download your resume below:");
        } catch (Exception e) {
            // Log the stack trace for debugging
            e.printStackTrace();
            request.setAttribute("error", "Failed to generate resume. Please try again. Error: " + e.getMessage());
        }

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
