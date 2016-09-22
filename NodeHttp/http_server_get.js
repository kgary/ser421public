// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url = require('url');
var messages = ['Hello World', 'From a Node.js server', 'Take Luck'];
http.createServer(function (req, res) {
    var resBody = '';
    var resMsg = '';
    var urlObj = url.parse(req.url, true, false);
    var qstr = urlObj.query;
    console.log(urlObj);
    if (!qstr.msg) {
        resMsg = '<h2>No msg parameter</h2>\n';
    } else {
        resMsg = '<h1>'+messages[qstr.msg]+'</h2>';
    }
    resBody = resBody + '<html><head><title>Simple HTTP Server</title></head>';
    resBody = resBody + '<body>' + resMsg;
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    res.end(resBody + '\n</body></html>');
}).listen(8080);

