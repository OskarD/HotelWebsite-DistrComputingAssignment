/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.servlets;

import humberhotel.beans.Booking;
import humberhotel.beans.User;
import humberhotel.db.DBConnection;
import humberhotel.exception.HotelException;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adik
 */
@WebServlet(name = "myBooking", urlPatterns = {"/myBooking"})
public class MyBookingServlet extends HttpServlet {

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
                String value = request.getParameter("cancel");
                try {
                    Booking.delete(Integer.parseInt(value));
                } catch (HotelException ex) {
                    out.println("Could not remove booking from the list. " + ex.getMessage());
                }
                
                    
                    
                Statement stmt = connection.createStatement();
                HttpSession session = request.getSession();
                User user = (User)session.getAttribute("user");
                
                String query = "Select hb.id, hb.roomnumber, hbd.bdate from hotelbookings hb "
                        + "join hotelbookeddays hbd on hb.id = hbd.bookingid "
                        + "join hotelusers hu on hb.bookedby = hu.email"
                        + "where hu.name = '" + user.getName() + "'";
                ResultSet rs = stmt.executeQuery(query);
                out.println("<table><tr><td>Room Number</td>"
                        + "<td>Date booked</td><td>Cancelation</td></tr>");
                
                while (rs.next()) {
                    int id = rs.getInt("a.id");
                    int number = rs.getInt("a.roomnumber");
                    Date date = rs.getDate("b.bdate");
                    out.println("<tr><td>" + number + "</td><td>" + date + "</td><td>"
                            + "<form action='myBooking.jsp' method='get'" + "'><button type='submit' name='cancel' value='" + id + "'>Cancel</button></form>"
                            + "</td></tr>");
                } 
                out.println("</table>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("<form action='myBooking.jsp' method='get'>");
            out.println("<style> #services {resize: none;} </style>");
            out.println("<label>Add special service instructions</label><br/>");
            out.println("<textarea rows='5' cols='50' id='services'></textarea><br/>");
            out.println("button type='submit' name='submit' value='submit'>Submit</button>");
            out.println("</form></div>");
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
