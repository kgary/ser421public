// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url = require('url');
var qstring = require('querystring');
var fs = require('fs');
var util = require('util');

function sendHomepage(res){
  fs.readFile('./html/index.html', function (err, html) {
    if (err) {
        throw err; 
    }       
    res.writeHeader(200, {"Content-Type": "text/html"});  
    res.write(html);  
    res.end();  
});
}

function parseWeather(weatherResponse, res) {
  var weatherData = '';
  weatherResponse.on('data', function (chunk) {
    weatherData += chunk;
  });
  weatherResponse.on('end', function () {
    res.end(weatherData);
  });
}

// You will need to go get your own free API key to get this to work
function getWeather(city, res){
  var options = {
    host: 'api.openweathermap.org',
    path: '/data/2.5/weather?q=' + city + "&APPID=d3772e348ab8cf35f9877845f6942efb"
  };

  http.request(options, function(weatherResponse){
    parseWeather(weatherResponse, res);
  }).end();
}

http.createServer(function (req, res) {
  console.log(req.method);
  if (req.method == "POST"){
    var reqData = '';
    req.on('data', function (chunk) {
      reqData += chunk;
    });
    req.on('end', function() {
      var postParams = qstring.parse(reqData);
      getWeather(postParams.city, res);
    });
  } else{
    sendHomepage(res);
  }
}).listen(8088);
