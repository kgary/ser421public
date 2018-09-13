// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var http = require('http');
var options = {
  host: '127.0.0.1',
  path: '/',
  port: '8088',
  method: 'POST'
};
function readJSONResponse(response) {
  var responseData = '';
  response.on('data', function (chunk) {
    responseData += chunk;
  });  
  response.on('end', function () {
    var dataObj = JSON.parse(responseData);
    console.log("Raw Response: " +responseData);
    console.log("Message: " + dataObj.message);
    console.log("Question: " + dataObj.question);
  });
}
var req = http.request(options, readJSONResponse);
req.write('{"name":"Bilbo", "occupation":"Burglar"}');
req.end();
