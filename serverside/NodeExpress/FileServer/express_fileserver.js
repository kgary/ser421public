var express = require('express');
const fileUpload = require('express-fileupload');
var app = express();
const fs = require('fs');

app.use(fileUpload());

app.get('/', function (req, res) {
  var fileList = buildFileLinks();
  if(!fileList) {
    links = "<h3>No files on server</h3>";
  } else {
    links = "<h3> Files </h3>" + fileList;
  }

  var form = links + '<form method="POST" enctype="multipart/form-data">' +
        '<label for="file_upload">Choose a picture:</label> <br><br>' +
        '<input type="file" id="file_upload" name="file_upload" accept="image/png, image/jpeg"></input><br><br>'+
        '<button>Submit</button>' +
        '</form>';
  res.send(form);
});

app.post('/', function(req, res) {
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).send('No files were uploaded.');
    }

    let uploadFile = req.files.file_upload;
    let fileName = req.files.file_upload.name;

    uploadFile.mv('./files/'+fileName, function(err) {
        if(err)
          return res.status(500).send(err);
        
        var ahref = '<a href=/>Return</a><br>'
        var html = "File uploaded! " + ahref;
        res.send(html);
    })
});

app.get('/download/:filename', function(req, res) {
  res.download("./files/"+req.params.filename);
});

function buildFileLinks() {
  var files = "<ul>";

  fs.readdirSync("./files/").forEach(file => {
    fileLink = file.split(" ").join("%20");
    files += "<li><a href=/download/"+fileLink+"> "+file+"</a></li>";
  });

  return files + "</ul>";
}

app.listen(8081);