// from https://dzone.com/articles/a-word-about-nodejs-event-loop
// article has a great explanation
// npm install fibonacci first
const request = require("request"),
      fibonacci = require("fibonacci"),
      fs = require("fs");

process.nextTick(() => {
    process.stdout.write("NT #1\n");
});
fs.readFile("./nexttick2.js", (err, data) => {
    process.stdout.write("1: I/O Polling...\n");
});
request.get("http://google.com", (err, res, body) => {
    process.stdout.write("2: System polling...\n")
})
setImmediate(() => {
    process.stdout.write("3: Set Immediate phase...\n");
});
setTimeout(() => {
    process.stdout.write("4: Timers...\n");
}, 0);
process.stdout.write("5: Fibonacci(20): " + fibonacci.iterate(20).number + " - Callback\n");
process.nextTick(() => {
    process.stdout.write("NT #2\n");
})
