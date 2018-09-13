// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var url = require('url');
var qstring = require('querystring');
function sendResponse(weatherData, res){
  var page = '<html><head><title>External Example</title></head>' +
    '<body>' +
    '<form method="post">' +
    'City: <input name="city"><br>' +
    '<input type="submit" value="Weather">' +
    '</form>';
  if(weatherData){
    page += '<h1>Weather Info</h1><p>' + weatherData +'</p>';
  }
  page += '</body></html>';    
  res.end(page);
}
function parseWeather(weatherResponse, res) {
  var weatherData = '';
  weatherResponse.on('data', function (chunk) {
    weatherData += chunk;
  });
  weatherResponse.on('end', function () {
    sendResponse(weatherData, res);
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
    sendResponse(null, res);
  }
}).listen(8088);
