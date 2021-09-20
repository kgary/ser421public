// This example originally posted by newspaint at
// https://newspaint.wordpress.com/2015/09/06/how-to-get-cookies-from-node-js-http-response/
// and customized for ser421
var http = require( "http" );
var url = require( "url" ).URL;

var urlObj = new URL("hi.html", "http://localhost:8088");
var cookies = [
  "foo=bar",
  "UofA=lastplace",
];

var options = {
  hostname: urlObj.hostname,
  port: ( urlObj.port || 8088 ), 
  method: 'GET',
  path: urlObj.pathname,
  headers: { },
};
options.headers["Cookie"] = cookies.join( "; " );

// An example of how to create a request object and send it
// Note the callback to process the response
var request = http.request(
  options,
  function ( response ) {
    // display returned cookies in header
    var setcookie = response.headers["set-cookie"];
    if ( setcookie ) {
      setcookie.forEach(
        function ( cookiestr ) {
          console.log( "COOKIE:" + cookiestr );
        }
      );
    }

    var data = "";
    response.on(
      "data",
      function ( chunk ) { data += chunk; }
    );

    response.on(
      "end",
      function () {
        console.log( "STATUS:" + response.statusCode );
        //console.log( "  DATA:" + data );  // this gets too obnoxiously long
      }
    );
  }
);

request.on(
  "error",
  function( err ) {
    console.error( "ERROR:" + err );
  }
);

request.end(); // let request know it is finished sending
