var fs = require('fs');
var http = require('http');
var url = require('url');

var ROOT_DIR = "html/";

function readFileAndReplace(output, file, replaceStr) {
    return new Promise(function(resolve, reject) {
	fs.readFile(ROOT_DIR + file, function (err, data) {
	    if (err) {
		reject({ status : 400, theOutput : "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process the request</Body</HTML>" });
	    } else if (replaceStr != null) {
		resolve({ status : 200, theOutput : output.theOutput.replace(replaceStr, data.toString()) });
	    } else {
		resolve({ status : 200, theOutput : data.toString() });
	    }
	});
    });
}

http.createServer(function (req, res) {
    let urlObj = url.parse(req.url, true, false);
    let qparams = urlObj.query;

    // qparams should be left=X banner=Y footer=Z and main=K
    var renderContent = { status : 200, theOutput : '' };
    var renderPromise = readFileAndReplace(renderContent, "template.html");
    //    renderPromise.then(function(result) {
    renderPromise.then(function(result) {
	return readFileAndReplace(result, "banner"+qparams.banner+".html", "BANNER");
    }).then(function(result) {
	return readFileAndReplace(result, "left"+qparams.left+".html", "LEFTNAV");
    }).then(function(result) {
	return readFileAndReplace(result, "footer"+qparams.footer+".html", "FOOTER");
    }).then(function(result) {
	return readFileAndReplace(result, "main"+qparams.main+".html", "MAIN");
    }).then(function(result) {
	res.writeHead(200, { 'Content-type': 'text/html' });
	res.end(result.theOutput);
    }, function(error) {
	console.log("Some error in the promise!");
	res.writeHead(error.status, { 'Content-type': 'text/html' });
	res.end(error.theOutput);
    });
}).listen(8088);
