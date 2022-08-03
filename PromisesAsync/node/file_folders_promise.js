// Compare to file_folders.js

var fs = require('fs');

function makeDir(dir) {
    return new Promise(function(resolve, reject) {
	fs.mkdir(dir, function(err) {
	    if (err) reject(err);
	    else resolve();
	});
    });
}
makeDir("./data")
    .then(() => { return makeDir("./data/folderA") })
    .then(() => { return makeDir("./data/folderA/folderB") })
    .then(() => { return makeDir("./data/folderA/folderB/folderD") })
    .catch((err) => { console.log("Encountered error: " + err.toString()) })
