# Information
- This examples interacts with a weather reporting server. 
- It takes a city name, and in response gets the weather data for the city.
- It uses AJAX fetch API along with plain NodeJS (No Express). Please refer index.html for more details on how the form data is used to make a fetch call.
- The flow is like this: Client makes an AJAX fetch request to the HTTP server (ServerA), this server then makes a request to a public weather reporting server (ServerB). The response is returned back from ServerB to ServerA and then to the client.

# Steps
1. node http_server_external.js
2. open 'http://localhost:8088/' in a web browser