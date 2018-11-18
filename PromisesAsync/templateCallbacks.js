var fs = require('fs');
var http = require('http');
var url = require('url');

var ROOT_DIR = "html/";

http.createServer(function (req, res) {
    let urlObj = url.parse(req.url, true, false);
    let qparams = urlObj.query;

    // qparams should be left=X banner=Y footer=Z and main=K
    var theOutput = '';
    var scode = 200;
    fs.readFile(ROOT_DIR + "template.html", function (err, template) {
	if (err) {
	    scode = 500;
	    theOutput = "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process the template<br/></br/>" + err + "</Body></HTML>";
	    res.writeHead(scode, {
		'Content-Type': 'text/html',
	    });
	    res.end(theOutput);
	} else {
	    theOutput = template.toString();
	    fs.readFile(ROOT_DIR + "banner" + qparams.banner + ".html", function (err, banner) {
		if (err) {
		    scode = 400;
		    theOutput = "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process banner<br/></br/>" + err + "</Body></HTML>";
		    res.writeHead(scode, {
			'Content-Type': 'text/html',
		    });
		    res.end(theOutput);
		} else {
		    theOutput = theOutput.replace("BANNER", banner.toString());
		    fs.readFile(ROOT_DIR + "footer" + qparams.footer + ".html", function (err, footer) {
			if (err) {
			    scode = 400;
			    theOutput = "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process footer<br/></br/>" + err + "</Body></HTML>";
			    res.writeHead(scode, {
				'Content-Type': 'text/html',
			    });
			    res.end(theOutput);
			} else {
			    theOutput = theOutput.replace("FOOTER", footer.toString());			    
			    fs.readFile(ROOT_DIR + "left" + qparams.left + ".html", function (err, left) {
				if (err) {
				    scode = 400;
				    theOutput = "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process left nav<br/></br/>" + err + "</Body></HTML>";
				    res.writeHead(scode, {
					'Content-Type': 'text/html',
				    });
				    res.end(theOutput);
				} else {
				    theOutput = theOutput.replace("LEFTNAV", left.toString());
				    fs.readFile(ROOT_DIR + "main" + qparams.main + ".html", function (err, main) {
					if (err) {
					    scode = 400;
					    theOutput = "<HTML><HEAD><Title>Error!</Title></HEAD><Body>Unable to process main<br/></br/>" + err + "</Body></HTML>";
					} else {
					    theOutput = theOutput.replace("MAIN", main.toString());
					}
					res.writeHead(scode, {
					    'Content-Type': 'text/html',
					});
					res.end(theOutput);
				    });
				}
			    });
			}
		    });
		}
	    });
	}
    });
}).listen(8088);
