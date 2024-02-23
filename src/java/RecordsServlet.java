/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FABIUS
 */


public class RecordsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                response.setContentType("text/html;charset=UTF-8");
                
                // Retrieve data from request parameters
                String itemName = request.getParameter("item_name");
                String description = request.getParameter("description");
                String locationFound = request.getParameter("location_found");
                String dateFound = request.getParameter("date_found");
                
                PrintWriter out = response.getWriter(); // Establish database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lost_and_founddb4", "root", "fabius001");
                
                // Prepare SQL statement
                String sql = "INSERT INTO records (item_name, description, location_found, date_found) VALUES (?, ?, ?, ?)";
                // Set parameters
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    // Set parameters
                    statement.setString(1, itemName);
                    statement.setString(2, description);
                    statement.setString(3, locationFound);
                    statement.setString(4, dateFound);
                    // Execute SQL statement
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        out.println("<h2>Record inserted successfully!</h2>");
                    } else {
                        out.println("<h2>Failed to insert record. Please try again.</h2>");
                    }
                    // Close resources
                }
                catch (SQLException ex) {
// Handle any SQL or class loading exceptions
response.getWriter().println("<h2>Database Error. Please try again later.</h2>");
                }
            }
        catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(RecordsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

