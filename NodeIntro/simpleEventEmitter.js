// modified from http://www.hacksparrow.com/node-js-eventemitter-tutorial.html
var EventEmitter = require('events').EventEmitter;
var radium = new EventEmitter();

function cb(ray) {
    console.log(ray);
}
// on and addListener are the same thing
radium.addListener('radiation', cb);
radium.on('foo', function(ray) {
	console.log("Boo hoo I will never get called");
});
radium.once('radiation', function(ray) {
	console.log("JUST ONCE " + ray);
});
setTimeout(function() {
	radium.removeListener('radiation', cb);
}, 5000);
setInterval(function() {
    radium.emit('radiation', 'GAMMA');
}, 500);
radium.emit('foo', 'BETA');