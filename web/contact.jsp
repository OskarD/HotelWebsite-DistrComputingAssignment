<%-- 
    Document   : contact
    Created on : 7-Apr-2015, 8:00:50 PM
    Author     : Serio
--%>



<%@include file="header.jsp" %>
<div id="contactwrapper">
    
    <div id="contactleft">
        <h1>CONTACT FORM</h1>
        <hr />
        <%
            if (request.getParameter("submit") != null) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String message = request.getParameter("message");
        %>
        <h2>Thank you for Submitting Your Form!</h2>
        <h3>You Entered The Following Information</h3>
        <p><b>Name: </b> <%= name %></p>
        <p><b>Email: </b> <%= email %></p>
        <p><b>Phone: </b> <%= phone %></p>
        <p><b>Your Message: </b> <%= message %></p>
        
        <%
            } else {
        %>
        <form method="post" name="contactform">
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" size="25" /></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><input type="text" name="email" size="25" /></td>
            </tr>
            <tr>
                <td>Phone: </td>
                <td><input type="text" name="phone" size="25" /></td>
            </tr>
            <tr>
                <td>Message: </td>
                <td><textarea cols="27" rows="8" name="message"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" name="submit" value="CONTACT US TODAY" /></td>
            </tr>
        </table>
        </form>
        <%
            }
        %>
    </div>
    
    <div id="contactright">
        <h1>ABOUT OUR STAFF</h1>
        <hr />
        <h2>Ademir Gotov</h2>
        <p><b>Job Title:</b> Hotel President</p>
        <p><b>Email:</b> ademir@thehumber.ca</p>
        <h2>Kevin Acevedo</h2>
        <p><b>Job Title:</b> Hotel Manager</p>
        <p><b>Email:</b> kevin@thehumber.ca</p>
        <h2>Oskar Danielsson</h2>
        <p><b>Job Title:</b> Hotel Accountant</p>
        <p><b>Email:</b> oskar@thehumber.ca</p>
        <h2>Tyler Serio</h2>
        <p><b>Job Title:</b> Hotel Busboy</p>
        <p><b>Email:</b> tyler@thehumber.ca</p>
                
        
    </div>
    
</div>
<%@include file="footer.jsp" %>