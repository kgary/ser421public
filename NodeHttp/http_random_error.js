var http = require('http');
var messages = {400: "Bad Request",
                401: "Unauthorized",
                402: "Payment Required",
                403: "Forbidden",
                404: "Not Found",
                405: "Method Not Allowed",
                500: "Internal Server Error",
                501: "Not Implemented",
                502: "Bad Gateway",
                503: "Service Unavailable"
               };
http.createServer(function (req, res) {
    var rand = Math.round(Math.random() * 10);
    if (rand < 6) {
        rand += 400; 
    } else {
        rand += (500-6);
    }
    res.writeHead(401, {'Content-type':'text/plain','Location':'/foo'});
    console.log ("Response payload: " + rand + " " + messages[rand]);
    res.end(rand + " " + messages[rand]);
}).listen(8088);
