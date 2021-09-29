var express = require('express');
var message = require('./message/message');

const fileUpload = require('express-fileupload');
const cookieParser = require('cookie-parser');
const fs = require('fs');

var app = express();
app.use(fileUpload());
app.use(cookieParser());
app.listen(1337);

app.get('/', function (req, res){
    var lastSeenBlogName = "None";
    if(req.cookies.lastSeenBlog) {
        lastSeenBlogName = req.cookies.lastSeenBlog;
    }

    var html = '<html>' +
        '<h3>A simple micro blog website with no frills nor nonsense.</h3>' +
        '<h4>Last Seen Blog -> ' + lastSeenBlogName + '</h4>' +
        '<form method="POST" enctype="multipart/form-data">' +
        '<label for="file_upload">Upload a blog (markdown only):</label> <br><br>' +
        '<input type="file" id="file_upload" name="file_upload" accept=".md"></input><br><br>'+
        '<button>Submit</button>' +
        '</form></html>';

  res.send(html);
});

app.post('/', function(req, res) {
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).send('No files were uploaded.');
    }

    let uploadFile = req.files.file_upload;
    let fileName = req.files.file_upload.name;

    var ahref = '<a href=/>Return</a><br>'
    fs.stat('./blog/'+fileName, function(err, stat) {
        if(err == null) {
            console.log('Blog exists');
            return res.status(409).send('Blog already exists, upload failed.');
        }
    });

    uploadFile.mv('./blog/'+fileName, function(err) {
        if(err)
          return res.status(500).send(err);
        
        var html = "Blog uploaded successfully! " + ahref;
        res.send(html);
    })
});

app.put('/', function(req, res) {
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).send('No files were uploaded.');
    }

    let uploadFile = req.files.file_upload;
    let fileName = req.files.file_upload.name;

    uploadFile.mv('./blog/'+fileName, function(err) {
        if(err)
          return res.status(500).send(err);
        
        var ahref = '<a href=/>Return</a><br>'
        var html = "Blog uploaded and updated successfully! " + ahref;
        res.send(html);
    })
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

            res.cookie('lastSeenBlog', req.params.file);
            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(responseContent);
        });

    });
});

console.log('Listening on http://localhost:1337');
