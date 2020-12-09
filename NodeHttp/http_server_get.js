// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url  = require('url');
var messages = ['Hello World', 'From a Node.js server', 'Take Luck'];

http.createServer(function (req, res) {
    let resBody = '';
    let resMsg = '';
    // This way of parsing a query string is deprecated but I still find it much easier
    // than trying to use the new WhatWG URL object and trying to parse the searchParams
    let urlObj = url.parse(req.url, true, false);
    let qstr = urlObj.query;
    console.log(qstr);
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
}).listen(8088);

