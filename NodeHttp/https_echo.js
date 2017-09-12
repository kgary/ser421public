// Example from Mixu's NodeJS eBook Ch. 10
// http://book.mixu.net/node/ch10.html
// HTTPS
var https = require('https');
var fs = require('fs');
// read in the private key and certificate
var pk = fs.readFileSync('./keys/privatekey.pem');
var pc = fs.readFileSync('./keys/certificate.pem');
var opts = { key: pk, cert: pc };
// create the secure echo server
https.createServer(opts, function(req, res) {
    console.log(req);
    if (req.method == "GET") {
        res.end("You called GET");
    } else {
        var reqContent = "";
        req.on('data', function (chunk) {
            reqContent += chunk;
        });
        req.on('end', function (chunk) {
            reqContent += chunk;
            res.writeHead(200);
            res.end(reqContent);
        });
    }
}).listen(8443, '0.0.0.0');