var http = require('http');


http.createServer(function (request, response) {   

    response.writeHead(200, {       

        'Content-Type': 'text/plain',       

        'Access-Control-Allow-Origin' : '*'   

    });   

    response.end('You won! You got here first!\n');   

    while (true) {}

}).listen(1337);
