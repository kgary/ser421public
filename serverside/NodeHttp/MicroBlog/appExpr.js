var express = require('express');
var message = require('./message/message');
var app = express();
app.listen(1337);

app.get('/', function (req, res){
  res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8'});
  res.end('A simple micro blog website with no frills nor nonsense.');
});

app.get('/blog/:file', function (req, res){

    message.readTextFile("/blog/"+req.params.file+".md", (err, rawContent)=>{
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
            res.end('Post not found.');
            return;
        }

        message.readTextFile('view/blogPost.html', (err, html)=>{
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain; charset=utf-8' });
                res.end('Internal error.');
                return;
            }

            var htmlContent = message.marked(rawContent);
            var responseContent = message.mustacheTemplate(html, {postContent: htmlContent});

            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(responseContent);
        });

    });
});

console.log('Listening on http://localhost:1337');
