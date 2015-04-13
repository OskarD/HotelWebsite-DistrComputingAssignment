/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
wp_register_script('google-maps-api', 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDlmdDMC6l8yAhxRksNZHdBhQs0cMop_qQ&sensor=true', array(), '3.15', true);

add_action('wp_enqueue_scripts', 'enqueue_assets');

function enqueue_assets() {
    wp_enqueue_script('google-maps-api');
}

/*function initialize() {
            var mapCanvas = document.getElementById('map-canvas');
            var mapOptions = {
                center: new google.maps.LatLng(43.728544, -79.607913),
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            var map = new google.maps.Map(mapCanvas, mapOptions);
           }
           google.maps.event.addDomListener(window, 'load', initialize);*/
           
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-34.397, 150.644)
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
}

function loadScript() {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp' +
      '&signed_in=true&callback=initialize';
  document.body.appendChild(script);
}

window.onload = loadScript;