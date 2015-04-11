/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.servlets;

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
public class AccountProfileServlet extends HttpServlet {

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
            String status = "";
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            User user = (User)session.getAttribute("user");
            
            request.getRequestDispatcher("/header.jsp").include(request, response);            
            out.println("<div id='profilewrapper'><form method='post' name='profileform'>");
        out.println("<h2>Update Your Profile</h2>");
        if (!status.equalsIgnoreCase("")) out.println("<p class='errorMessage'>" + status + "</p>");
        out.println("<form method='post' name='signupform'><table>");
        out.println("<tr><td>Name: </td><td><input type='text' size='20' name='name' value='" + user.getName() + "'  /></td></tr>");
        out.println("<tr><td>Email: </td><td><input type='text' size='20' name='email' value='" + user.getEmail() + "'  /></td></tr>");
        out.println("<tr><td>New Password: </td><td><input type='password' size='20' name='pass1' /></td></tr>");
        out.println("<tr><td>Re-enter New Password: </td><td><input type='password' size='20' name='pass2' /></td></tr>");
        out.println("<tr><td colspan='2'><p><input type='submit' name='submit' value='UPDATE PROFILE' /></p></td></tr>");

            out.println("</table></form></div>");
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
