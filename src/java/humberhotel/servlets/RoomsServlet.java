package humberhotel.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
            out.println("<div id='roomswrapper'><form method='post' name='roomsform'>");
            out.println("<h2>Rooms</h2>");
            
            if()

            if (session.getAttribute("user") == null) {
                out.println("<p><a href='login.jsp'>Login To Book A Room..</a></p>");
                out.println("<p>or..</p>");
                out.println("<p><a href='signup.jsp'>Sign Up Today!</a></p>");
            } else {
                out.println("<table>");
                out.println("<tr><td>Date (MM/DD/YYYY): </td> <td><input type='text' size='2' maxlength='2' name='month' /> <input type='text' size='2' maxlength='2' name='day' /> <input type='text' size='4' maxlength='4' name='year' /> </td></tr>");
                out.println("<tr><td>Type of Room: </td> <td><select name='roomType'><option value='Luxury Room'>Luxury Room</option><option value='Suite Room'>Suite Room</option></select>");
                out.println("<tr><td colspan='2'><p><input type='submit' name='submit' value='Book Room' /></p></td></tr>");
                out.println("</table>");
            }
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
