// Example attributed to Ch. 2 of Mixu's Node Book, http://book.mixu.net/node/ch2.html
var myTimer = setTimeout(function() {
    console.log('Timeout at ' + new Date().toTimeString());
}, 500);
myTimer.unref();  // if timers are all that is left exit (comment out for setInterval

// start time
var sTime = new Date();
console.log('Started app processing loop at ' + sTime.toString());

// delay block
var i = 0;
while (new Date().getTime() < sTime.getTime() + 2500) { i++ }

console.log('Exiting processing loop at ' + new Date().toTimeString() + ' after ' +i+ ' iterations');
