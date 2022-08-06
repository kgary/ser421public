// Example from Brad Dayley updated by Kevin Gary
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url  = require('url').URL;

// Refactored the message processing out (step 3 of our template)
function makeMsg(msg) {
    console.log(msg);
    let messages = ['Hello World', 'From a Node.js server', 'Take Luck'];
    if (!msg) {
	return "No msg parameter";
    } else {
	return "Message is " + messages[msg];
    }
}

http.createServer(function (req, res) {
    let resBody = '';
    let resMsg = '';
    // This version of the example also updates to use the new WHATwg URL object and
    // retrieves the query string parameters through the URLSearchParams object
    let urlObj = new URL(req.url, "http://localhost:8088"); // url.parse(req.url, true, false);
    let qstr = urlObj.searchParams; // urlObj.query;
    console.log(qstr);
    resMsg = '<h2>' + makeMsg(qstr.get("msg")) + '</h2>';
    resBody = resBody + '<html><head><title>Simple HTTP Server</title></head>';
    resBody = resBody + '<body>' + resMsg;
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    res.end(resBody + '\n</body></html>');
}).listen(8088);

