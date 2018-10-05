// Compare to file_folders_promise.js

var fs = require('fs');

function makeDir(dir) {
    return new Promise(function(resolve, reject) {
	fs.mkdir(dir, function(err) {
	    if (err) reject(err);
	    else resolve(dir);
	});
    });
}

async function makeDirectories() {
    try {
	const dir1 = await makeDir("./data");
	const dir2 = await makeDir("./data/folderA");
	const dir3 = await makeDir("./data/folderA/folderB");
	const dir4 = await makeDir("./data/folderA/folderB/folderD");
	console.log("Created " + dir1 + " then " + dir2 + " then " + dir3 + " and finally " + dir4); 
    } catch (err) {
	console.log("Encountered error: " + err.toString());
    }
}

makeDirectories();
