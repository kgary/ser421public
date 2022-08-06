var express = require('express');
var app = express();
app.listen(8081);

app.get('/', function(req, res){
  res.send('Hello from Express');
});
app.get('/foo', function(req, res) { res.send('bar'); });
app.post('/pink', function(req, res) { res.send('elephant'); });
app.all('/user/*', function(req,res) { res.send('Any verb for users');});