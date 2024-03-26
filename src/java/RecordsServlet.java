/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class RecordsServlet
 */
@MultipartConfig
public class RecordsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String itemName = request.getParameter("item_name");
        String description = request.getParameter("description");
        String locationFound = request.getParameter("location_found");
        String dateFound = request.getParameter("date_found");

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lost_and_founddb4", "root", "fabius001");

            String sql = "INSERT INTO records (item_name, description, location_found, date_found, image) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, itemName);
                statement.setString(2, description);
                statement.setString(3, locationFound);
                statement.setString(4, dateFound);
                if (filePart != null && filePart.getSize() > 0) {
                    // Save the uploaded file to a directory
                    String uploadsDir = "/path/to/your/uploads/directory"; // Change this to your directory
                    File uploads = new File(uploadsDir);
                    if (!uploads.exists()) {
                        uploads.mkdirs();
                    }
                    String filePath = uploadsDir + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    // Set the image path in the database
                    statement.setString(5, filePath);
                } else {
                    statement.setString(5, null); // If no file uploaded, set image path to null
                }
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    out.println("<h2>Record inserted successfully!</h2>");
                } else {
                    out.println("<h2>Failed to insert record. Please try again.</h2>");
                }
            } catch (SQLException ex) {
                out.println("<h2>Database Error. Please try again later.</h2>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RecordsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




