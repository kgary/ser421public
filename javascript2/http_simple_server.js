// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

// Access http://localhost:8080
var fs = require('fs');
var http = require('http');
var url = require('url');
var ROOT_DIR = ".";
http.createServer(function (req, res) {
  var urlObj = url.parse(req.url, true, false);
  fs.readFile(ROOT_DIR + urlObj.pathname, function (err,data) {
    if (err) {
      res.writeHead(404);
      res.end(JSON.stringify(err));
      return;
    }
    if (req.url.endsWith('html')) {
    	res.writeHead(200, {
		'Content-Type': 'text/html'
    	});
    } else if (req.url.endsWith('js')) {
        res.writeHead(200, {
                'Content-Type': 'application/javascript'
	});
    }
    res.end(data);
  });
}).listen(8080, 'localhost', 3, function() { 
                console.log('I am now ready!'); 
});
