// Example modified from https://stackoverflow.com/questions/3393854/get-and-set-a-single-cookie-with-node-js-http-server

var http = require('http');

function parseCookies (request) {
    var list = {},
        rc = request.headers.cookie;

    rc && rc.split(';').forEach(function( cookie ) {
        var parts = cookie.split('=');
        list[parts.shift().trim()] = decodeURI(parts.join('='));
    });

    return list;
}


http.createServer(function (request, response) {

    // To Read a Cookie
    var cookies = parseCookies(request);

    // To Write a Cookie
    response.writeHead(200, {
	'Set-Cookie': ['mycookie=test', 'mysecondcookie=test2; Expires=${new Date(Date.now() + 1000 * 10).toGMTString()}; Max-Age=2000', 'mythirdcookie=test3; Secure'],
	'Content-Type': 'text/plain'
    });
    response.end('Hello World\n');
}).listen(3000);

console.log('Server running at http://127.0.0.1:3000/');
