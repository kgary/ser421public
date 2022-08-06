// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development
// Updated for Buffer 2021

var fs = require('fs');
fd = fs.openSync('./data/veggie.txt', 'r');
var veggies = "";
do {
  let buf = Buffer.alloc(5);
  buf.fill();
  var bytes = fs.readSync(fd, buf, null, 5);
  console.log("read %d bytes", bytes);
  veggies += buf.toString();
} while (bytes > 0);
fs.closeSync(fd);
console.log("Veggies: " + veggies);
