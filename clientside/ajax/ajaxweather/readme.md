# Information
- This is a third weather example.
- The 1st was on the server-side only (NodeHttp directory), which used a Node function on the server to make an API call.
- The second, in the sibling directory to this one named weather, makes an AJAX call back to a NodeJS server which in turn calls the weather api (essentially a simple proxy).
- This example uses straight AJAX to invoke the weather API directly
- A second related example uses a JSONP callback, support by this API

# Steps
1. You need to create a file in the same directory named config.js with one line: var config = { APITOKEN : 'YOUR TOKEN HERE' }. You need to get a token from openweatherdata.org and put it here.
2. load file ajaxweather.html in a browser, turn on the web dev tools console
3. load file ajaxweather_jsonp.html in a browser, turn on the web dev tools console