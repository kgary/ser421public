// Example from Mixu's NodeJS eBook Ch. 10
// http://book.mixu.net/node/ch10.html
var http = require('http');
// create the secure echo server
http.createServer(function(req, res) {
    if (req.method == "GET") {
	//res.writeHead(200);
	res.writeHead(200, {
	    'Content-Type': 'text/html',
            'Set-Cookie': 'now='+Date.now()
	});
        res.end("<html><head></head><body><p><em>Cookie: " + req.headers.cookie + "</em></p><form action=\".\" method=\"POST\"><input type=\"text\" name=\"name\"/><input type=\"hidden\" name=\"hiddenparam\" value=\"Do you see me\"><input type=\"submit\" value=\"OK\"></form></body></html>");
    } else {
        var reqContent = ""+req.headers.cookie+"     ";
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
