var fs = require('fs');
var http = require('http');
var url = require('url');

var ROOT_DIR = "html/";

function readFileAndReplace(output, file, replaceStr) {
    return new Promise(function(resolve, reject) {
	fs.readFile(ROOT_DIR + file, function (err, data) {
	    if (err) {
		reject("<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process the request</Body</HTML>");
	    } else if (replaceStr != null) {
		resolve(output.replace(replaceStr, data.toString()));
	    } else {
		resolve(data.toString());
	    }
	});
    });
}

// THIS DOES NOT WORK AS WE DESIRE, WHY???
async function readReplaceWrapper(qparams, res) {
    try {
	// qparams should be left=X banner=Y footer=Z and main=K
	var output = '';
	var result = await readFileAndReplace(output, "template.html");
	const [banner, left, footer, main] = await Promise.all([
	    readFileAndReplace(result, "banner"+qparams.banner+".html", "BANNER"),
	    readFileAndReplace(result, "left"+qparams.left+".html", "LEFTNAV"),
	    readFileAndReplace(result, "footer"+qparams.footer+".html", "FOOTER"),
	    readFileAndReplace(result, "main"+qparams.main+".html", "MAIN")
	]);
	res.writeHead(200, { 'Content-type': 'text/html' });
	res.end(result);
    } catch (error) {
	res.writeHead(500, { 'Content-type': 'text/html' });
	res.end(error);
    }
}

http.createServer(function (req, res) {
    let urlObj = url.parse(req.url, true, false);
    let qparams = urlObj.query;

    readReplaceWrapper(qparams, res);
}).listen(8088);
