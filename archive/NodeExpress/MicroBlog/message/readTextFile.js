var fs = require('fs');
var path = require('path');

function readTextFile(relativePath, fn) {
  var fullPath = path.join(__dirname, '../') + relativePath;

  fs.readFile(fullPath, 'utf-8', function fileRead(err, text) {
    fn(err, text);
  });
}

module.exports = readTextFile;
