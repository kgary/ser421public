var app = require('express')();

app.get('/', function(req, res, next){
  res.send('Hello from Express');
  next();
});
app.use ('/foo', function(req, res, next) {
	console.log('First app.use call');
	next();
});
app.use (function(req, res, next) {
	console.log('2nd app.use call');
	next();
});
app.use (function(req, res, next) {
	console.log('3rd app.use call');
});
app.listen(8081);
