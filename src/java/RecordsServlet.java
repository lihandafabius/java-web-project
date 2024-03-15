/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Response.ResponseBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.ServletUtils;

/**
 *
 * @author FABIUS
 */


public class RecordsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                PrintWriter out = ServletUtils.getPrintWriter(response);
                Connection conn = ServletUtils.getDBConnection();
                
                // Retrieve data from request parameters
                String itemName = request.getParameter("item_name");
                String description = request.getParameter("description");
                String locationFound = request.getParameter("location_found");
                String dateFound = request.getParameter("date_found");
                
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
                        ResponseBuilder.buildRedirectResponse(out, "Record inserted successfully!", "records.html");
                    } else {
                        ResponseBuilder.buildRedirectResponse(out, "Failed to insert record. Please try again.", "records.html");
                    }
                    // Close resources
                }
                catch (SQLException ex) {
// Handle any SQL or class loading exceptions
response.getWriter().println("<h2>Database Error. Please try again later.</h2>");
                }
            }
        catch (SQLException ex) {
                        Logger.getLogger(RecordsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

