// from (with slight modifications
// https://medium.com/@tkssharma/writing-neat-asynchronous-node-js-code-with-promises-async-await-fa8d8b0bcd7c
function doubleAfter2Seconds(x) {
    setTimeout(() => {
      return x * 2;
    }, 2000);
}

// This doesn't work!
var val1 = doubleAfter2Seconds(10);
var val2 = doubleAfter2Seconds(20);
var val3 = doubleAfter2Seconds(30);
console.log(10 + val1 + val2 + val3);
