// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

// Access http://localhost:8080/hello.html
var fs = require('fs');
var http = require('http');
var url = require('url');
var ROOT_DIR = "html/";
http.createServer(function (req, res) {
    var urlObj = url.parse(req.url, true, false);
    if (req.method == "GET") {
	fs.readFile(ROOT_DIR + urlObj.pathname, function (err,data) {
	    if (err) {
		res.writeHead(404);
		res.end(JSON.stringify(err));
		return;
	    }
	    res.writeHead(200, {
		'Content-Type': 'text/html',
		'Access-Control-Allow-Origin': '*',
		'Set-Cookie': 'foo=bar'
	    });
	    res.end(data);
	});
    } else {  // POST - presume an AJAX request and echo payload back
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
}).listen(8008, 'localhost', 3, function() { 
                console.log('I am now ready!'); 
});
