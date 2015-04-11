package humberhotel.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class LoginServlet extends HttpServlet {

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
            String email = "";
            String password = "";
            if (session.getAttribute("user") != null) {
                response.sendRedirect("account.jsp");
                return;
            }
            if (request.getParameter("submit") != null) {
                email = request.getParameter("email");
                password = request.getParameter("password");
                if (email.equalsIgnoreCase("tylerserio@hotmail.com") && password.equalsIgnoreCase("hockey")) {
                    String name = "Tyler Serio";
                    int authority = 1;
                    User user = new User(email, name, authority);
                    session.setAttribute("user", user);
                    response.sendRedirect("index.jsp");
                    return;
                } else {
                    status = "Email/Password is Incorrect";
                }
            }
                request.getRequestDispatcher("/header.jsp").include(request, response);
                out.println("<div id='loginwrapper'>");
                out.println("<h2>Login Your Account</h2>");
                if (!status.equalsIgnoreCase("")) out.println("<p class='errorMessage'>" + status + "</p>");
                out.println("<form method='post' name='loginform'>");
                out.println("<table>");
                out.println("<tr><td>Email:</td> <td><input type='text' size='20' name='email' value='" + email + "' autofocus /></td></tr>");
                out.println("<tr><td>Password:</td> <td><input type='password' size='20' name='password' /></td></tr>");
                out.println("<tr><td colspan='2'><p><input type='submit' name='submit' value='Login' /></p></td></tr>");
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
