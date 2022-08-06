// Example from Brad Dayley, updated by Kevin Gary
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

// Access http://localhost:8088/hello.html
// If you want an alternative using legacy url way, see https://www.w3schools.com/nodejs/nodejs_url.asp
var fs = require('fs');
var http = require('http');
var url = require('url').URL; // This is the updated WHATwg url parser
var ROOT_DIR = "html/";
http.createServer(function (req, res) {
	console.log(req.headers); // take off the .headers part to see a full socket IncomingMessage
	if (req.method == "GET") {  // 1. Process request header, check method
		//var urlObj = url.parse(req.url, true, false);  // This is the legacy url way
		let urlObj = new URL(req.url, "http://localhost:8088"); // 2. Process request params
		console.log(urlObj);
		// 3. Route (or handle) request logic; here is is just a ile retrieve
		fs.readFile(ROOT_DIR + urlObj.pathname, function (err, data) {
			// 4. Assmble payload, either no file found error or get content
			if (err) {
				res.writeHead(404);  // 5. In case of error, set response code
				res.end("404 Not Found: " + JSON.stringify(err));
				return;
			}
			res.writeHead(200, {  // 5. in case of success, set 200, Content-Type
				'Content-Type': 'text/html',
				'Set-Cookie': 'servercookie=ser421'
			});
			res.end(data);   // 6. Write out the payload
		});
	} else {  // we did not get the GET we expected
		res.writeHead(405);  // 5. In case of error, set response code
		res.end("405 Method Not Allowed: " + req.method);
		return;
	}
}).listen(8088, 'localhost', 3, function () {
	console.log('I am now ready!');
});
