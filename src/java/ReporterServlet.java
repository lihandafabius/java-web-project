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
public class ReporterServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         try {
             response.setContentType("text/html;charset=UTF-8");
             
             String name = request.getParameter("name");
             String contactNumber = request.getParameter("contact_number");
             String email = request.getParameter("email");
             String locationLost = request.getParameter("location_lost");
             String itemLost = request.getParameter("item_lost");
             String dateLost = request.getParameter("date_lost");
             
             PrintWriter out = response.getWriter();
             Class.forName("com.mysql.cj.jdbc.Driver");// Establish database connection
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lost_and_founddb4","root", "fabius001");
             
                        // Check if the report already exists
            String checkSql = "SELECT * FROM reporters WHERE name = ? AND contact_number = ? AND email = ? AND location_lost = ? AND item_lost = ? AND date_lost = ?";
            try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
                checkStatement.setString(1, name);
                checkStatement.setString(2, contactNumber);
                checkStatement.setString(3, email);
                checkStatement.setString(4, locationLost);
                checkStatement.setString(5, itemLost);
                checkStatement.setString(6, dateLost);

                ResultSet rs = checkStatement.executeQuery();
                if (rs.next()) {
                    // Inform the reporter that the report already exists
                    out.println("<h2>Report already exists. Please do not repeat the same report.</h2>");
                    return; // Exit the method to prevent duplicate insertion
                }
            }
             
             // Prepare SQL statement
             String sql = "INSERT INTO reporters (name, contact_number, email, location_lost, item_lost, date_lost) VALUES (?, ?, ?, ?, ?, ?)";
             
             // Set parameters and execute SQL statement
             try (PreparedStatement statement = conn.prepareStatement(sql)) {
                 statement.setString(1, name);
                 statement.setString(2, contactNumber);
                 statement.setString(3, email);
                 statement.setString(4, locationLost);
                 statement.setString(5, itemLost);
                 statement.setString(6, dateLost);
                 
                 // Execute SQL statement
                 int rowsInserted = statement.executeUpdate();
                 if (rowsInserted > 0) {
                     // Display success message
                 
                     out.println("<h1>Reporter details inserted successfully!</h1>");
                     
                 } else {
                     // Display failure message
                     out.println("<!DOCTYPE html>");
                     out.println("<html>");
                     out.println("<head>");
                     out.println("<title>Error</title>");
                     out.println("</head>");
                     out.println("<body>");
                     out.println("<h2>Failed to insert reporter details. Please try again.</h2>");
                     out.println("</body>");
                     out.println("</html>");
                 }
             }
             catch (SQLException ex) {
                 // Handle any SQL or class loading exceptions
                 Logger.getLogger(ReporterServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     catch (ClassNotFoundException | SQLException ex) {
         Logger.getLogger(ReporterServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
 }

}
