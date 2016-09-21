var http = require('http');
var options = { hostname: 'www.asu.edu', port: '80', path: '/index.html' };
var req = http.request(options, function(resp){
    var s=''; 
    resp.on('data', function(c) { 
        s += c; 
    });
    resp.on('end', function() { 
        console.log(s); 
    });
});
req.end();