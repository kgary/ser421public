// Example from Mixu's NodeJS eBook Ch. 10
// http://book.mixu.net/node/ch10.html
var http = require('http');
// create the secure echo server
http.createServer(function(req, res) {
    if (req.method == "GET") {
	res.writeHead(200);
        res.end("<html><head></head><body><form action=\".\" method=\"POST\"><input type=\"text\" name=\"name\"/><input type=\"submit\" value=\"OK\"></form></body></html>");
    } else {
        var reqContent = "";
        req.on('data', function (chunk) {
            reqContent += chunk;
        });
        req.on('end', function (chunk) {
            reqContent += chunk;
            res.writeHead(200);
            res.end(reqContent);
        });
    }
}).listen(8001, '0.0.0.0');
