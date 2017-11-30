var http = require('http');
var url = require('url');

http.createServer(function (req, res) {
var data=JSON.parse('{"coord":{"lon":151.21,"lat":-33.87},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"base":"stations","main":{"temp":296.25,"pressure":1021,"humidity":46,"temp_min":295.15,"temp_max":297.15},"visibility":10000,"wind":{"speed":6.7,"deg":90},"clouds":{"all":20},"dt":1510626600,"sys":{"type":1,"id":8233,"message":0.1671,"country":"AU","sunrise":1510598999,"sunset":1510648521},"id":2147714,"name":"Sydney","cod":200}');
    var urlObj = url.parse(req.url, true, false);
    var qstr = urlObj.query;
    data.dt = Math.floor(new Date().getTime()/1000);
    data.main.temp += Math.floor(Math.random()*35-5);
    data.main.humidity += Math.floor(Math.random()*9-5);
    data.wind.speed += Math.floor(Math.random()*12-5);
    data.clouds.all += Math.floor(Math.random()*15-5);
    data.name = qstr.cityName;
    res.writeHead(200, {
	    'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    });
    res.end(JSON.stringify(data));
}).listen(8081, 'localhost', 3, function() { 
                console.log('I am now ready!'); 
});
