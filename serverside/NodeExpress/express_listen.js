// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var express = require('express');
var app = express();
app.listen(8081);

app.get('/', function(req, res){
  res.send('Hello from Express');
});
