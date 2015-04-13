<%-- 
    Document   : map
    Created on : 10-Apr-2015, 2:39:57 PM
    Author     : Serio
--%>

<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang ="en">
    <head>       
        <style type="text/css"> 
            //#map-canvas {
             // width: 800px;
              //height: 600px;
              //background: #CCC;
            //}
            #map-canvas.img {
              //margin: 0;
              position: absolute;
              //top: 50%;
              left: 50%;
              margin-right: -50%;
              bottom: 50%;
              transform: translate(-50%, 50%);
              width: 800px;
              height: 600px;
            }
        </style>
        <title>Map</title>
        <script type="text/javascript" scr="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
        <script type="text/javascript" src="gmaps.js"></script>
        
        <script>
            //function initialize() {
            //var mapCanvas = document.getElementById('map-canvas');
            //var mapOptions = {
               // center: new google.maps.LatLng(43.728544, -79.607913),
               // zoom: 8,
               // mapTypeId: google.maps.MapTypeId.ROADMAP
            //};
           // var map = new google.maps.Map(mapCanvas, mapOptions);
          // }
           //google.maps.event.addDomListener(window, 'load', initialize);
        //</script>
    </head>
    <body>
        <div id="map-canvas">
            <a href="https://www.google.ca/maps/place/Humber+College/"><img id="img" src="images/map.png"/></a>
        </div>
    </body>
    
</html>
<%@include file="footer.jsp" %>