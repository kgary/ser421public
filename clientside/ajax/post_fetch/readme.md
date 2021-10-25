# Steps to Run the example
1. node http_server.js
2. node http_server_post.js
3. Enter in web browser: http://localhost:8008/ajax_http_server_post.html

# Information
- Server A (port 8008) => http_server.js: Serves the HTML files which are a starting point for AJAX examples
    - ajax_http_server_post.html
    - fetch_http_server_post.html
- Server B (port 8088) => http_server_post.js: This server responds to the ajax requests made by the HTML files served by Server A.