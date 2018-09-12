// Example attributed to Ch. 2 of Mixu's Node Book, http://book.mixu.net/node/ch2.html

var myTimer = setTimeout(function() {
    console.log('Timeout at ' + new Date().toTimeString());
}, 500);
myTimer.unref();  // if timers are all that is left exit (comment out for setInterval

// start time
var sTime = new Date();
console.log('Started app processing loop at ' + sTime.toString());

// delay block
var cb = function(err, fd) {};
var i = 0;
while (new Date().getTime() < sTime.getTime() + 25000) { 
	if (i++ % 100 == 1) {
		console.log(i);
		require('fs').open("hello.js", 'r', cb);
	}
 }

console.log('Exiting processing loop at ' + new Date().toTimeString() + ' after ' +i+ ' iterations');
