// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development
var express = require('express');
var url = require('url');
var app = express();
app.listen(8081);
app.get('/image', function (req, res) {
  res.sendFile('arch.jpg', 
               { maxAge: 60*1000,
                 root: './views/'},
               function(err){
    if (err){
      console.log("Error");
    } else {
      console.log("Success");
    }
  });
});
