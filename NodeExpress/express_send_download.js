// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development
var express = require('express');
var url = require('url');
var app = express();
app.listen(8081);
app.get('/download', function (req, res) {
  res.sendFile('word.docx', { root: './views/' }, function(err) {
	  if (err) 
	      next(err);
	  else 
	      console.log("Sent file!");
      });
});
app.get('/image', function (req, res) {
	  res.sendFile('arch.jpg', 
	               { maxAge: 24*60*60*1000,
	                 root: './views/'},
	               function(err){
	    if (err){
	      console.log("Error");
	    } else {
	      console.log("Success");
	    }
	  });
	});
