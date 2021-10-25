var express = require('express');
var message = require('./message/message');

const fileUpload = require('express-fileupload');
const fs = require('fs');

var app = express();
app.use(fileUpload());
app.listen(1337);

app.get('/', function (req, res) {
    res.sendFile('index.html', { root: __dirname + '/view' });
});

app.get('/blogs', function (req, res) {
    res.end(buildFileLinks());
});

function buildFileLinks() {
    let files = "<ul>\n";

    fs.readdirSync("./blog/").forEach(file => {
        fileLink = file.split(" ").join("%20");
        fileLink = file.split(".")[0];
        files += "<li><a href=./blog/" + fileLink + "> " + file + "</a></li>\n";
    });
    return files + "</ul>\n";
}

app.post('/', function (req, res) {
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).send('No files were uploaded.');
    }

    let uploadFile = req.files.file;
    let fileName = req.files.file.name;

    fs.stat('./blog/' + fileName, function (err, stat) {
        if (err == null) {
            console.log('Blog exists');
            return res.status(409).send();
        }
        uploadFile.mv('./blog/' + fileName, function (err) {
            if (err) {
                return res.status(500).send(err);
            }
            res.status(201).send();
        });
    });
});

app.put('/', function (req, res) {
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).send('No files were uploaded.');
    }

    let uploadFile = req.files.file_upload;
    let fileName = req.files.file_upload.name;

    uploadFile.mv('./blog/' + fileName, function (err) {
        if (err)
            return res.status(500).send(err);

        var ahref = '<a href=/>Return</a><br>'
        var html = "Blog uploaded and updated successfully! " + ahref;
        res.send(html);
    })
});

app.get('/blog/:file', function (req, res) {

    message.readTextFile("/blog/" + req.params.file + ".md", (err, rawContent) => {
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
            res.end('Post not found.');
            return;
        }

        message.readTextFile('view/blogPost.html', (err, html) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain; charset=utf-8' });
                res.end('Internal error.');
                return;
            }

            var htmlContent = message.marked(rawContent);
            var responseContent = message.mustacheTemplate(html, { postContent: htmlContent });
            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(responseContent);
        });

    });
});

console.log('Listening on http://localhost:1337');
