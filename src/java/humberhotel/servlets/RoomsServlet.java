package humberhotel.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import humberhotel.beans.Booking;
import humberhotel.beans.Room;
import humberhotel.beans.User;
import humberhotel.exception.HotelException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Serio
 */
@WebServlet(urlPatterns = {"/RoomsServlet", "/rooms.jsp"})
public class RoomsServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div id='roomswrapper'>");
            out.println("<h2>Rooms</h2>");
            
            out.println(""
                    + " <div>"
                    + "     <form>"
                    + "         <p>Check room availability</p>"
                    + "         Check-In Date<br />"
                    + "         <input type='date' name='txtStartDate' /><br />"
                    + "         Check-Out Date<br />"
                    + "         <input type='date' name='txtEndDate' /><br />"
                    + "         <button type='submit' name='btnSetDates'>Find Available Rooms</button>"
                    + "     </form>"
                    + " </div>");
            
            User user = null;
            
            if (session.getAttribute("user") != null)
                user = (User) session.getAttribute("user");
            
            if(request.getParameter("btnSetDates") == null && request.getParameter("btnBookRoom") == null 
                    || user != null && user.getAuthority() > 0){ // Always show if user is admin
                // Original viewing state
                out.println(""
                        + " <div>"
                        + "     <h2>Our Rooms</h2>"
                        + "     <p>Here is a list of all our rooms. Click on a particular room for more information.</p>"
                        + "     <ul>");
                
                if(user!= null && user.getAuthority() > 0)
                    out.println("<form>");
                
                try {
                    for(Room room : Room.getAll()) {
                        out.println(""
                                + "     <li><a href='room?number=" + room.getRoomNumber() + "'>" + room.getRoomNumber() + "</a> - Type: " + room.getType() + ", price: $" + room.getPrice());

                        if(user!= null && user.getAuthority() > 0)
                            out.println("<button type='submit' name='btnRemoveRoom' value='" + room.getRoomNumber() + "'>Remove</button>");

                        out.println("</li>");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("<li> Error: " + ex.getMessage() + "</li>");
                }
                
                if(user!= null && user.getAuthority() > 0)
                    out.println("</form>");
                
                out.println(""
                        + "     </ul>"
                        + " </div>");
                
                out.println(""
                        + " <div>"
                        + "     <form>"
                        + "         <input type='number' name='txtRoomNumber' min='100' max='999' required />"
                        + "         <input type='number' name='txtPrice' step='any' min='0' required />"
                        + "         <input type='text' name='txtType' required />"
                        + "         <button type='submit' name='btnAddRoom'>Add Room</button>"
                        + "     </form>"
                        + " </div>");
            }
            
            // Buttons
            if(request.getParameter("btnSetDates") != null){
                // Dates have been set   
                try {
                    DateFormat df = new SimpleDateFormat("YYYY-MM-DD", Locale.CANADA);
                    
                    Date startDate = new Date(df.parse(request.getParameter("txtStartDate")).getTime());
                    Date endDate = new Date(df.parse(request.getParameter("txtEndDate")).getTime());
                    
                    out.println(""
                            + " <div>"
                            + "     <h2>Available Rooms</h2>"
                            + "     <form>"
                            + "         <input type='hidden' name='startDate' value='" + startDate + "' />"
                            + "         <input type='hidden' name='endDate' value='" + endDate + "' />"
                            + "         <ul>");
                    
                    int availableRooms = 0;
                    
                    for(Room room : Room.getAll()) {
                        if(Booking.checkAvailability(room.getRoomNumber(), startDate, endDate) == true) {
                            out.println(""
                                    + "     <li><a href='room?number=" + room.getRoomNumber() + "'>" + room.getRoomNumber() + "</a> - Type: " + room.getType() + ", price: $" + room.getPrice() + "<button type='submit' name='btnBookRoom' value='" + room.getRoomNumber() + "'>Book</button></li>");
                            
                            availableRooms++;
                        }
                    }
                    
                    out.println(""
                            + "         </ul>"
                            + "     </form>"
                            + " </div>");
                    
                    if(availableRooms == 0) {
                        out.println("Sorry, there are no available rooms for this time period.");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("Error: " + ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("Error: " + ex.getMessage());
                }
            } 
            else if(request.getParameter("btnBookRoom") != null) {
                // User wants to book a room
                if (session.getAttribute("user") == null) {
                    out.println("You need to log in before you can book a room.");
                }
                else {
                    int roomNumber = Integer.parseInt(request.getParameter("btnBookRoom"));
                    DateFormat df = new SimpleDateFormat("YYYY-MM-DD", Locale.CANADA);
                    
                    Date startDate = new Date(df.parse(request.getParameter("startDate")).getTime());
                    Date endDate = new Date(df.parse(request.getParameter("endDate")).getTime());
                    
                    Booking booking = new Booking();
                    booking.setRoomNumber(roomNumber);
                    booking.setDays(startDate, endDate);
                    try {
                        booking.create();
                        out.println("<p>Successfully booked:</p>" + booking.toHTML());
                    } catch (HotelException ex) {
                        Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("Could not complete booking. " + ex.getMessage());
                    } catch (SQLException ex) {
                        Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("Error: " + ex.getMessage());
                    }
                }
            } else if(request.getParameter("btnRemoveRoom") != null) {
                // User wants to remove a room
                if(user!= null && user.getAuthority() > 0) {
                    int roomNumber = Integer.parseInt(request.getParameter("btnRemoveRoom"));
                    try {
                        Room.delete(roomNumber);
                        out.println("Removed room " + roomNumber);
                    } catch (SQLException ex) {
                        Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("Couldn't remove room. " + ex.getMessage());
                    }
                }
            } else if(request.getParameter("btnAddRoom") != null) {
                // User wants to add a room
                if(user!= null && user.getAuthority() > 0) {
                    int roomNumber = Integer.parseInt(request.getParameter("txtRoomNumber"));
                    double price = Double.parseDouble(request.getParameter("txtPrice"));
                    String type = request.getParameter("txtType");

                    try {
                        Room.create(roomNumber, type, price);
                        out.println("Added room " + roomNumber);
                    } catch (SQLException ex) {
                        Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("Couldn't add room " + roomNumber + ". " + ex.getMessage());
                    }
                }
            }
            
            out.println("</div>");
            request.getRequestDispatcher("/footer.jsp").include(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RoomsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
