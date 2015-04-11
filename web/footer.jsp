<%-- 
    Document   : footer
    Created on : 7-Apr-2015, 7:54:40 PM
    Author     : Serio
--%>

<div id="footer">
<h2>Site Navigation</h2>
<ul>
<li><a href="index.jsp">Home Page</a></li>
<li><a href="contact.jsp">Contact Our Staff Today</a></li>
<li><a href="photos.jsp">Photos Of Our Hotel</a></li>
<li><a href="maps.jsp">View A Our Hotel Map</a></li>
<li><a href="rooms.jsp">Book A Room Today</a></li>
<%
    if (session.getAttribute("user") != null) {
        out.println("<li><a href=\"logout.jsp\">Logout</a></li>");        
    } else {
        out.println("<li><a href=\"login.jsp\">Login To Your Account</a></li>");
    }
%>
</ul>
<p>The Humber&copy; is a Trademark of Humber College and the Students Who Built It.</p>
</div>

</body>
</html>