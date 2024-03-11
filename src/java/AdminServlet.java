
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                response.setContentType("text/html;charset=UTF-8");
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                PrintWriter out = response.getWriter();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lost_and_founddb4", "root", "fabius001");
                
                String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    
                    if (rs.next()) {
                        // Authentication successful, redirect to records.html
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);
                        
                        
                        Cookie ck=new Cookie("user",username);  
                        response.addCookie(ck);
                        
                        response.sendRedirect("records.html");
                       
 
                    } else {
                        // Authentication failed, display error message
                        out.println("<h2>Authentication failed. Please check your username and password.</h2>");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }


