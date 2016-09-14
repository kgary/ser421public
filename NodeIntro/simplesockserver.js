// simple socket server
var net = require('net');
net.createServer(function (sock) {
    console.log("Incoming connection accepted");
    sock.on('data', function (d) {
        console.log("Received from client: " + d);
        sock.write(d+d, function() {
        	console.log("Finished response to client");
        })
    }).on('error', function (e) {
	console.log("Some kind of server error");
    });
}).listen(3000);
