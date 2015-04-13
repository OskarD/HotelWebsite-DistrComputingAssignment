<%-- 
    Document   : header
    Created on : 7-Apr-2015, 7:53:51 PM
    Author     : Serio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="humberhotel.beans.*"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>The Humber: Hotel and Resort</title>
<link rel="stylesheet" href="style.css" type="text/css" />
<link rel="shortcut icon" href='images/icon.png' />
</head>

<body>

<div id="top">
    <img src="images/logo.jpg" id="logo" alt="Logo" />
<ul id="navi">
<li><a href="rooms.jsp">Rooms</a></li>
<li><a href="map.jsp">Map</a></li>
<li><a href="photos.jsp">Photos</a></li>
<li><a href="contact.jsp">Contact Us</a></li>
<li><a href="index.jsp">Home</a></li>
</ul>
</div>
<div id="login">
    <%
        if (session.getAttribute("user") == null) {
            out.println("<p><a href=\"login.jsp\">Login</a> - <a href=\"signup.jsp\">Sign-Up</a></p>");
        } else {
            User user = (User)session.getAttribute("user");
            out.println("<p>Welcome Back, <a href=\"account.jsp\"><b>" + user.getName() + "</b></a> - (<a href='logout.jsp'>Logout</a>)");
        }
    %>
</div>
