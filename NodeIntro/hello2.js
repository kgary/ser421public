/**
 * From http://www.theprojectspot.com/tutorial-post/Node-js-for-beginners-part-1-hello-world/2
 */
var http = require('http');
http.createServer(function (req, res) {
	res.writeHead(200, {'Content-Type': 'text/plain'});
	res.end('Hello World\n');
    }).listen(8081);
console.log('Server started');
