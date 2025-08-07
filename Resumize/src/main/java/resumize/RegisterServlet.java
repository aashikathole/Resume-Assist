package resumize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(username, password, email);

        UserDatabase userDatabase = new UserDatabase();

        try {
            userDatabase.addUser(newUser);
            
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            
            request.setAttribute("error", "Registration failed. Username might be already taken.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}