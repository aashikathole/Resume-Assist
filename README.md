# RESUMIZE: Generate-Manage-Test Resumes

## Overview

**RESUMIZE** is a web-based resume builder application designed to streamline the process of creating, managing, and testing professional resumes. Built using Java Server Pages (JSP) for the frontend and Core Java for backend logic, with MySQL as the database, RESUMIZE helps users craft standout resumes through customizable templates and intuitive user interfaces.

## Features

- **User Authentication**
  - Secure registration and login
  - Passwords are encrypted (Bcrypt)
- **Customizable Resume Templates**
  - Multiple industry-ready templates
  - Customization options: fonts, colors, layouts
  - ATS (Applicant Tracking System)-compatible formats
- **Multiple Resume Management**
  - Manage several versions of resumes
  - Version control for easy updating and reverting
- **Intuitive Dashboard**
  - Easy navigation and step-by-step workflow
  - Mobile-friendly responsive design
- **Efficient Backend**
  - Java Servlets for fast request handling
  - Optimized MySQL database for secure storage
- **Additional Support**
  - Resume writing tips
  - Customer support features

## Objectives

- Offer a *user-friendly* platform for online resume creation
- Enable users to focus on content—not formatting hassles
- Prioritize *security* and *privacy*:
  - Password encryption (Bcrypt)
  - Secure, accessible storage in MySQL
- Provide flexibility with a variety of templates and easy editing
- Facilitate career advancement for beginners and professionals

## System Requirements

### Hardware
- Processor: Intel i3 minimum (i5/i7 recommended)
- RAM: 4GB required (8GB or higher recommended)
- Storage: SSD preferred
- Internet: Stable broadband
- Display: 1366x768 minimum

### Software
- OS: Windows 10+, Ubuntu/CentOS, or macOS Mojave+
- JDK 8
- Apache Tomcat 10.1.31
- MySQL 8.0
- JSP, JDBC Connector JAR v9.1.0, itextpdf v5.9.9
- Supported browsers: Chrome, Firefox, Safari, Edge

## Installation

1. **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/resumize.git
    ```
2. **Backend Setup**
    - Import the project into your preferred Java IDE (Eclipse EE recommended)
    - Configure JDK 8 and add required JAR dependencies:
      - MySQL Connector JAR v9.1.0
      - itextpdf v5.9.9
3. **Database Setup**
    - Run the provided SQL scripts in MySQL:
      ```sql
      CREATE DATABASE resumize;
      USE resumize;
      -- Users Table
      CREATE TABLE users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL CHECK (CHAR_LENGTH(password) >= 4),
        email VARCHAR(255) NOT NULL UNIQUE CHECK (email LIKE '%@%.%')
      );
      -- Resumes Table
      CREATE TABLE resumes (
        id INT AUTO_INCREMENT PRIMARY KEY,
        resumeTitle VARCHAR(255),
        userId INT NOT NULL,
        name VARCHAR(255) NOT NULL,
        profilePicture VARCHAR(255) NULL,
        email VARCHAR(255) NOT NULL,
        phone VARCHAR(20),
        linkedin VARCHAR(255),
        location VARCHAR(255),
        bio TEXT,
        skills TEXT,
        experience TEXT,
        education TEXT,
        interests TEXT,
        languages TEXT,
        resumeTemplate VARCHAR(255),
        FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
      );
      ```
4. **Deploy on Tomcat**
    - Export the project as a WAR file and deploy on Tomcat 10
    - Ensure MySQL database connectivity via JDBC

## Usage

- **Register** for an account or **Sign In**
- **Create or edit** a resume using the dashboard
- **Select and customize** a template as per your requirement
- **Download or export** resumes in PDF format

## Screenshots

- Home, Dashboard, Sign Up/Sign In View
- Resume Creation Form and Modern Template Preview
- Database structure via MySQL

## Future Scope

- More industry-specific templates (e.g., tech, healthcare)
- Integration with job portals (e.g., LinkedIn, Indeed)
- Machine learning-driven, personalized resume feedback
- Mobile app or advanced responsive design
- Premium features: resume analytics, cover letter builder

## Project Structure

```
resumize/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       ├── jsp/
│   │       └── resources/
├── database/
│   └── resumize_schema.sql
├── README.md
├── .gitignore
```

## References

- Oracle: “Java for Web Applications”
- MySQL Documentation: “MySQL 8.0 Reference Manual”
- Java EE Official Guide: “JSP and Servlets”
- JavaTpoint: “Introduction to MVC Architecture”
- Spring Security Documentation: “Bcrypt for Secure Password Hashing”

## Author
- Aashika Thole

## License
This project is for academic use. Contact the authors for further usage or collaboration.

**Happy Resume Building!**
