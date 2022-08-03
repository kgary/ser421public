var fs = require('fs');
var http = require('http');
var url = require('url');

var ROOT_DIR = "html/";

function readFileWrapper(file) {
    return new Promise(function(resolve, reject) {
	fs.readFile(ROOT_DIR + file, function (err, data) {
	    if (err) {
		reject("Unable to process the request");
	    } else {
		resolve(data.toString());
	    }
	});
    });
}

function replaceTemplateContent(template, marker, content) {
    return template.replace(marker, content);
}

async function readReplaceWrapper(qparams, res) {
    try {
	// qparams should be left=X banner=Y footer=Z and main=K
	var output = '';
	console.log(qparams);
	const [template, banner, leftnav, footer, main] = await Promise.all([
	    readFileWrapper("template.html"),
	    readFileWrapper("banner"+qparams.banner+".html"),
	    readFileWrapper("left"+qparams.left+".html"),
	    readFileWrapper("footer"+qparams.footer+".html"),
	    readFileWrapper("main"+qparams.main+".html"),
	]);
	// I have a master template and 4 strings to sub. string replace
	// is synchronous by definition so just do them in sequence
	output = template.replace("BANNER", banner);
	output = output.replace("LEFTNAV", leftnav);
	output = output.replace("MAIN", main);
	output = output.replace("FOOTER", footer);
	
	res.writeHead(200, { 'Content-type': 'text/html' });
	res.end(output);
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
