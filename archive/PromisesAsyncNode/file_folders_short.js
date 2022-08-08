// Modified Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development

var fs = require('fs');
fs.mkdir("./data", function(err) {
    if (err) {
	console.log("Unable to make data/folderA " + err);
    } else {
	fs.mkdir("./data/folderA", function(err){
	    if (err) {
		console.log("Unable to make data/folderA " + err);
	    } else {
		fs.mkdir("./data/folderA/folderB", function(err){
		    if (err) {
			console.log("Unable to make data/folderA/folderB");
		    } else {
			fs.mkdir("./data/folderA/folderB/folderD", function(err){
			    if (err) {
				console.log("Unable to make data/folderA/folderB/folderD");
			    } else {
				console.log("All directories made");
			    }
			});
		    }
		});
	    }
	});
    }
});
