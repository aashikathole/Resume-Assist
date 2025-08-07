package resumize;
import resumize.Resume;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PDFGenerator {
    
    public static void generateResume(Resume resume, String saveLocation) throws Exception {
        // Validate the save location
        if (saveLocation == null || saveLocation.isEmpty()) {
            throw new IllegalArgumentException("Invalid save location provided.");
        }

        Document document = new Document();
        try {
            // Create a PdfWriter instance
            PdfWriter.getInstance(document, new FileOutputStream(saveLocation));
            document.open();

            // Add content based on the selected template
            switch (resume.getResumeTemplate()) {
                case "minimalTemplate":
                    addMinimalTemplate(document, resume);
                    break;
                case "modernTemplate":
                    addModernTemplate(document, resume);
                    break;
                case "creativeTemplate":
                	addCreativeTemplate(document, resume);
                    break;
                default:
                    addMinimalTemplate(document, resume); // Default template if not specified
            }
        } catch (DocumentException | IOException e) {
            throw new Exception("Error generating PDF: " + e.getMessage(), e);
        } finally {
            document.close();
        }
    }

    // Template for minimal resume
    private static void addMinimalTemplate(Document document, Resume resume) throws Exception, DocumentException {
    	// Add profile picture if available
        if (resume.getProfilePicture() != null && !resume.getProfilePicture().isEmpty()) {
            try {
                Image profilePic = Image.getInstance(resume.getProfilePicture());
                profilePic.scaleToFit(100, 100); // Scale the image
                profilePic.setAlignment(Element.ALIGN_LEFT); // Align image
                document.add(profilePic);
            } catch (IOException e) {
                throw new Exception("Error adding profile picture: " + e.getMessage(), e);
            }
        }
        document.add(new Paragraph("\nMinimal Resume for: " + resume.getName()));
        document.add(new Paragraph("\nEmail: " + resume.getEmail()));
        document.add(new Paragraph("\nPhone: " + resume.getPhone()));
        document.add(new Paragraph("\nLinkedIn: " + resume.getLinkedin()));
        document.add(new Paragraph("\nAddress: " + resume.getLocation()));
        document.add(new Paragraph("\nBio: " + resume.getBio()));
        document.add(new Paragraph("\nSkills: " + resume.getSkills()));
        document.add(new Paragraph("\nExperience: " + resume.getExperience()));
        document.add(new Paragraph("\nEducation: " + resume.getEducation()));
        document.add(new Paragraph("\nInterests: " + resume.getInterests()));
        document.add(new Paragraph("\nLanguages Known: " + resume.getLanguages()));
    } 
    
    
    // Template for modern resume
    private static void addModernTemplate(Document document, Resume resume) throws DocumentException {
        // Add a table for the profile picture and contact details
        PdfPTable profileTable = new PdfPTable(2);
        profileTable.setWidthPercentage(100);
        profileTable.setWidths(new float[]{1, 2}); // Profile picture column smaller than details column
        
        // Profile Picture on the left
        PdfPCell profilePicCell = new PdfPCell();
        profilePicCell.setBorder(Rectangle.NO_BORDER);
        if (resume.getProfilePicture() != null && !resume.getProfilePicture().isEmpty()) {
            try {
                Image profilePic = Image.getInstance(resume.getProfilePicture());
                profilePic.scaleToFit(100, 100);
                profilePic.setAlignment(Element.ALIGN_LEFT);
                profilePicCell.addElement(profilePic);
            } catch (Exception e) {
                // Handle the error if the profile picture cannot be added
                profilePicCell.addElement(new Paragraph("[No Image]", FontFactory.getFont(FontFactory.TIMES, 12)));
            }
        } else {
            profilePicCell.addElement(new Paragraph("[No Image]", FontFactory.getFont(FontFactory.TIMES, 12)));
        }
        profileTable.addCell(profilePicCell);
        
        // Name, Email, Phone, LinkedIn, Location on the right
        PdfPCell contactDetailsCell = new PdfPCell();
        contactDetailsCell.setBorder(Rectangle.NO_BORDER);
        contactDetailsCell.addElement(new Paragraph(resume.getName().toUpperCase(), FontFactory.getFont(FontFactory.TIMES_BOLD, 16)));
        contactDetailsCell.addElement(new Paragraph(resume.getEmail() + " | " + resume.getPhone(), FontFactory.getFont(FontFactory.TIMES, 12)));
        contactDetailsCell.addElement(new Paragraph(resume.getLinkedin(), FontFactory.getFont(FontFactory.TIMES, 12)));
        contactDetailsCell.addElement(new Paragraph(resume.getLocation(), FontFactory.getFont(FontFactory.TIMES, 12)));
        profileTable.addCell(contactDetailsCell);
        
        // Add the profile table to the document
        document.add(profileTable);
        document.add(new Paragraph("\n " + resume.getBio(), FontFactory.getFont(FontFactory.TIMES, 12)));
        document.add(new Paragraph("\n"));

        // Experience Section (Full width)
        document.add(new Paragraph("EXPERIENCE", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
        for (String experience : resume.getExperience().split("\\|")) {
            document.add(new Paragraph("• " + experience, FontFactory.getFont(FontFactory.TIMES, 12)));
        }
        document.add(new Paragraph("\n"));
        // Create a table with 2 columns for skills/education, and below it, interests/languages
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100); // Set table width to 100%
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        
        // First row: Skills and Education
        PdfPCell skillsCell = new PdfPCell();
        skillsCell.addElement(new Paragraph("\n"));
        skillsCell.setBorder(Rectangle.NO_BORDER);
        skillsCell.addElement(new Paragraph("SKILLS", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
        for (String skill : resume.getSkills().split("\\|")) {
            skillsCell.addElement(new Paragraph("• " + skill, FontFactory.getFont(FontFactory.TIMES, 12)));
        }
        PdfPCell educationCell = new PdfPCell();
        educationCell.addElement(new Paragraph("\n"));
        educationCell.setBorder(Rectangle.NO_BORDER);
        educationCell.addElement(new Paragraph("EDUCATION", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
        for (String education : resume.getEducation().split("\\|")) {
            educationCell.addElement(new Paragraph("• " + education, FontFactory.getFont(FontFactory.TIMES, 12)));
        }

        // Add cells for Skills and Education
        table.addCell(skillsCell);
        table.addCell(educationCell);

        // Second row: Interests and Languages
        PdfPCell interestsCell = new PdfPCell();
        interestsCell.addElement(new Paragraph("\n"));
        interestsCell.setBorder(Rectangle.NO_BORDER);
        interestsCell.addElement(new Paragraph("INTERESTS", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
        interestsCell.addElement(new Paragraph(resume.getInterests(), FontFactory.getFont(FontFactory.TIMES, 12)));

        PdfPCell languagesCell = new PdfPCell();
        languagesCell.addElement(new Paragraph("\n"));
        languagesCell.setBorder(Rectangle.NO_BORDER);
        languagesCell.addElement(new Paragraph("LANGUAGES KNOWN", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
        languagesCell.addElement(new Paragraph(resume.getLanguages(), FontFactory.getFont(FontFactory.TIMES, 12)));

        // Add cells for Interests and Languages
        table.addCell(interestsCell);
        table.addCell(languagesCell);

        // Add the table to the document
        document.add(table);
    }
    
    
    // Template for creative resume
    private static void addCreativeTemplate(Document document, Resume resume) throws DocumentException, IOException {
        // Load the Dancing Script font
        BaseFont dancingScriptFont = BaseFont.createFont("C:\\Users\\thole\\eclipse-workspace\\DancingScript-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        
        // Create a Font object for Dancing Script
        Font dancingScript = new Font(dancingScriptFont, 12, Font.NORMAL);

        // Add a table for the profile picture and contact details
        PdfPTable profileTable = new PdfPTable(2);
        profileTable.setWidthPercentage(100);
        profileTable.setWidths(new float[]{1, 2}); // Profile picture column smaller than details column

        // Profile Picture on the left
        PdfPCell profilePicCell = new PdfPCell();
        profilePicCell.setBorder(Rectangle.NO_BORDER);
        if (resume.getProfilePicture() != null && !resume.getProfilePicture().isEmpty()) {
            try {
                Image profilePic = Image.getInstance(resume.getProfilePicture());
                profilePic.scaleToFit(100, 100);
                profilePic.setAlignment(Element.ALIGN_LEFT);
                profilePicCell.addElement(profilePic);
            } catch (Exception e) {
                // Handle the error if the profile picture cannot be added
                profilePicCell.addElement(new Paragraph("[No Image]", dancingScript));
            }
        } else {
            profilePicCell.addElement(new Paragraph("[No Image]", dancingScript));
        }
        profileTable.addCell(profilePicCell);

        // Name, Email, Phone, LinkedIn, Location on the right
        PdfPCell contactDetailsCell = new PdfPCell();
        contactDetailsCell.setBorder(Rectangle.NO_BORDER);
        contactDetailsCell.addElement(new Paragraph(resume.getName().toUpperCase(), new Font(dancingScriptFont, 16, Font.BOLD)));
        contactDetailsCell.addElement(new Paragraph(resume.getEmail() + " | " + resume.getPhone(), dancingScript));
        contactDetailsCell.addElement(new Paragraph(resume.getLinkedin(), dancingScript));
        contactDetailsCell.addElement(new Paragraph(resume.getLocation(), dancingScript));
        profileTable.addCell(contactDetailsCell);

        // Add the profile table to the document
        document.add(profileTable);
        document.add(new Paragraph("\n " + resume.getBio(), dancingScript));
        document.add(new Paragraph("\n"));

        // Experience Section (Full width)
        document.add(new Paragraph("EXPERIENCE", new Font(dancingScriptFont, 14, Font.BOLD)));
        for (String experience : resume.getExperience().split("\\|")) {
            document.add(new Paragraph("• " + experience, dancingScript));
        }
        document.add(new Paragraph("\n"));

        // Create a table with 2 columns for skills/education, and below it, interests/languages
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100); // Set table width to 100%
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // First row: Skills and Education
        PdfPCell skillsCell = new PdfPCell();
        skillsCell.addElement(new Paragraph("\n"));
        skillsCell.setBorder(Rectangle.NO_BORDER);
        skillsCell.addElement(new Paragraph("SKILLS", new Font(dancingScriptFont, 14, Font.BOLD)));
        for (String skill : resume.getSkills().split("\\|")) {
            skillsCell.addElement(new Paragraph("• " + skill, dancingScript));
        }
        PdfPCell educationCell = new PdfPCell();
        educationCell.addElement(new Paragraph("\n"));
        educationCell.setBorder(Rectangle.NO_BORDER);
        educationCell.addElement(new Paragraph("EDUCATION", new Font(dancingScriptFont, 14, Font.BOLD)));
        for (String education : resume.getEducation().split("\\|")) {
            educationCell.addElement(new Paragraph("• " + education, dancingScript));
        }

        // Add cells for Skills and Education
        table.addCell(skillsCell);
        table.addCell(educationCell);

        // Second row: Interests and Languages
        PdfPCell interestsCell = new PdfPCell();
        interestsCell.addElement(new Paragraph("\n"));
        interestsCell.setBorder(Rectangle.NO_BORDER);
        interestsCell.addElement(new Paragraph("INTERESTS", new Font(dancingScriptFont, 14, Font.BOLD)));
        interestsCell.addElement(new Paragraph(resume.getInterests(), dancingScript));

        PdfPCell languagesCell = new PdfPCell();
        languagesCell.addElement(new Paragraph("\n"));
        languagesCell.setBorder(Rectangle.NO_BORDER);
        languagesCell.addElement(new Paragraph("LANGUAGES KNOWN", new Font(dancingScriptFont, 14, Font.BOLD)));
        languagesCell.addElement(new Paragraph(resume.getLanguages(), dancingScript));

        // Add cells for Interests and Languages
        table.addCell(interestsCell);
        table.addCell(languagesCell);

        // Add the table to the document
        document.add(table);
    }
}