/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel;

import humberhotel.beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Serio
 */
public class AccountServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            User user = (User)session.getAttribute("user");
            request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div id='accountwrapper'>");
            out.println("<h2>Your Account</h2>");
            out.println("<h3>Information</h3>");
            out.println("<ul>");
            out.println("<li><a href='bookedrooms.jsp'>View Booked Rooms</a></li>");
            out.println("<li><a href='accountprofile.jsp'>View Profile</a></li>");
            out.println("</ul>");
            
            if (user.getAuthority()) {
                out.println("<h3>Admin Menu</h3>");
                out.println("<ul>");
                out.println("<li><a href='roomsreport.jsp'>View All Booked Rooms</a></li>");
                out.println("<li><a href='addrooms.jsp'>Add Rooms</a></li>");
                out.println("<li><a href='editrooms.jsp'>Edit Rooms</a></li>");
                out.println("<li><a href='viewusers.jsp'>View Users</a></li>");
                out.println("</ul>");
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
