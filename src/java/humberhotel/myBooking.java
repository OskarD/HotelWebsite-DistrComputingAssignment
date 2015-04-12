/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel;

import humberhotel.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adik
 */
@WebServlet(name = "myBooking", urlPatterns = {"/myBooking"})
public class myBooking extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection connection = DBConnection.getConnection();           
            request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div id='myBookings'>");
            out.println("<head>List of bookings</head>");
            try {
                Statement stmt = connection.createStatement();
                String query = "Select a.bookedby, a.roomnumber, b.bdate from n00770693.hotelbookings a join n00770693.hotelbookeddays b on a.id = b.bookingid";
                ResultSet rs = stmt.executeQuery(query);
                out.println("<table><tr><td>Booked name</td><td>Room Number</td>"
                        + "<td>Date booked</td><td>Cancelation</td></tr>");
                while (rs.next()) {
                    String name = rs.getString("a.bookedby");
                    int number = rs.getInt("a.roomnumber");
                    Date date = rs.getDate("b.bdate");
                    out.println("<tr><td>" + name + "</td><td>" + 
                            number + "</td><td>" + date + "</td><td>"
                            + "<button type='submit' id='cancel'>Cancel</button>"
                            + "</td></tr>");
                } 
                out.println("</table>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("</div>");
            request.getRequestDispatcher("/footer.jsp").include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
