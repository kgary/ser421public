// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url  = require('url');

function makeMsg(msg) {
    console.log(msg);
    let messages = ['Hello World', 'From a Node.js server', 'Take Luck'];
    if (!msg) {
	return "No msg parameter";
    } else {
	return "Message is " + messages[msg.msg];
    }
}

http.createServer(function (req, res) {
    let resBody = '';
    let resMsg = '';
    // This way of parsing a query string is deprecated but I still find it much easier
    // than trying to use the new WhatWG URL object and trying to parse the searchParams
    let urlObj = url.parse(req.url, true, false);
    let qstr = urlObj.query;
    console.log(qstr);
    resMsg = '<h2>' + makeMsg(qstr) + '</h2>';
    resBody = resBody + '<html><head><title>Simple HTTP Server</title></head>';
    resBody = resBody + '<body>' + resMsg;
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    res.end(resBody + '\n</body></html>');
}).listen(8088);

