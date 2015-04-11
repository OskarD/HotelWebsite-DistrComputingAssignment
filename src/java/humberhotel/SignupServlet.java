/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serio
 */
public class SignupServlet extends HttpServlet {

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
        String status = "";
        String name = "";
        String email = "";
        String pass1 = "";
        String pass2 = "";
        if (request.getParameter("submit") != null) {
            name = request.getParameter("name");
            email = request.getParameter("email");
            pass1 = request.getParameter("pass1");
            pass2 = request.getParameter("pass2");
            boolean confirm = true;
            if (name.equalsIgnoreCase("") || email.equalsIgnoreCase("") || pass1.equalsIgnoreCase("") || pass2.equalsIgnoreCase("")) {
                status = "You have missed a field";
                confirm = false;
            } else if (!pass1.equals(pass2)) {
                status = "Passwords Do Not Match.";
                confirm = false;
            }
            if (confirm) {
                response.sendRedirect("login.jsp");
                return;
            }
            
        }
        request.getRequestDispatcher("/header.jsp").include(request, response);
        out.println("<div id='signupwrapper'>");
        out.println("<h2>Create An Account</h2>");
        if (!status.equalsIgnoreCase("")) out.println("<p class='errorMessage'>" + status + "</p>");
        out.println("<form method='post' name='signupform'><table>");
        out.println("<tr><td>Name: </td><td><input type='text' size='20' name='name' value='" + name + "'  /></td></tr>");
        out.println("<tr><td>Email: </td><td><input type='text' size='20' name='email' value='" + email + "'  /></td></tr>");
        out.println("<tr><td>Password: </td><td><input type='password' size='20' name='pass1' /></td></tr>");
        out.println("<tr><td>Re-enter Password: </td><td><input type='password' size='20' name='pass2' /></td></tr>");
        out.println("<tr><td colspan='2'><p><input type='submit' name='submit' value='SIGN UP TODAY' /></p></td></tr>");
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
