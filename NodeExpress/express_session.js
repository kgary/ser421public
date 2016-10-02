// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var express = require('express');
var cookieParser = require('cookie-parser');
//var session = require('cookie-session');
var session = require('express-session');
var app = express();
app.use(cookieParser());
//app.use(cookieSession({secret: 'MAGICALEXPRESSKEY'}));
app.use(session({
	secret: 'MAGICALEXPRESSKEY',
	resave: true,
	saveUninitialized: true
}));
app.get('/library', function(req, res) {
  console.log(req.cookies);
  if(req.session.restricted) {
    res.send('You have been in the restricted section ' + 
             req.session.restrictedCount + ' times.');
  }else {
    res.send('Welcome to the library.');
  }
});
app.get('/restricted', function(req, res) {
  req.session.restricted = true;
  if(!req.session.restrictedCount){
    req.session.restrictedCount = 1;
  } else {
    req.session.restrictedCount += 1;
  }
  res.redirect('/library');
});
app.listen(8081);
