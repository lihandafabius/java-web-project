import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.PrintWriter;

public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Delete the cookie by setting its maximum age to 0
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie); 
                    break;
                }
            }
        }
        
        // Redirect the user to the login page
        response.setContentType("text/html");
        
        // Get the PrintWriter to write out HTML
        PrintWriter out = response.getWriter();
        
        // Output HTML content
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Logout</title></head>");
        out.println("<body>");
        out.println("<script type='text/javascript'>");
        out.println("alert('You are successfully logged out!');");
        out.println("window.location = 'admin-login.html';"); // Use window.location for redirection
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        
    }
}
