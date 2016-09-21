// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url = require('url');
var messages = ['Hello World', 'From a Node.js server', 'Take Luck'];
http.createServer(function (req, res) {
    var urlObj = url.parse(req.url, true, false);
    var qstr = urlObj.query; // qs.parse(urlObj.query);
    console.log('req.url is ' + req.url);
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    res.write('<html><head><title>Simple HTTP Server</title></head>');
    res.write('<body>');
    if (!qstr.msg) {
        res.write('<h2>No msg parameter</h2>\n');
    } else {
        res.write('<h1>'+messages[qstr.msg]+'</h2>');
    }
    res.end('\n</body></html>');
}).listen(8080);
