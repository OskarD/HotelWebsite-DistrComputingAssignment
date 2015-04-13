/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.servlets;

import humberhotel.beans.Booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serio
 */
public class ReportServlet extends HttpServlet {

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
            
            request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div id='reportswrapper'>");
            out.println("<h2>Room Reports</h2>");
            out.println("<h3>Sort By:</h3>");
            out.println("<p><a href='reports.jsp?report=newestdate'>Latest Bookings</a> - <a href='reports.jsp?report=numerical'>Numerical Order</a> - <a href='reports.jsp?report=price'>Price</a></p>");
            if (request.getParameter("report") != null) {
                if (request.getParameter("report").equalsIgnoreCase("newestdate")) {
                    out.println("<p>Newest Dates</p>");
                } else if (request.getParameter("report").equalsIgnoreCase("numerical")) {
                    out.println("<p>Numerical Order</p>");

                } else if (request.getParameter("report").equalsIgnoreCase("price")) {
                    ArrayList<Booking> bookedRooms = new ArrayList<>();
                    ArrayList<Booking> bookedRoomsSorted = new ArrayList<>();
                    try {
                        bookedRooms = Booking.getAll();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (Booking b : bookedRooms) {
                        if (bookedRoomsSorted.isEmpty()) {
                            bookedRoomsSorted.add(b);
                        } else {
                            for (int i = 0; i < bookedRoomsSorted.size(); i++) {
                                if (b.getRoomNumber() > bookedRoomsSorted.get(i).getRoomNumber()) {
                                    Booking replaced = bookedRoomsSorted.get(i);
                                    bookedRoomsSorted.remove(i);
                                    bookedRoomsSorted.add(b);
                                    bookedRoomsSorted.add(replaced);
                                }
                            }
                        }
                    }
                    for (Booking b : bookedRooms) {
                        out.println("<p>Room Number: <b>" + b.getRoomNumber() + "</b> (booked by <b>" + b.getBookedBy() + "</b>)</p>");
                    }
                }
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
