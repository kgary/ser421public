// from (with slight modifications
// https://medium.com/@tkssharma/writing-neat-asynchronous-node-js-code-with-promises-async-await-fa8d8b0bcd7c
// now see https://codeburst.io/javascript-es-2017-learn-async-await-by-example-48acc58bad65
function doubleAfter2Seconds(x) {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(x * 2);
    }, 2000);
  });
}

function addPromise(x) {
  return new Promise(resolve => {
    doubleAfter2Seconds(10).then((a) => {
      console.log(x + " doubled 10 after 2 seconds");
      doubleAfter2Seconds(20).then((b) => {
        console.log(x + " doubled 20 after 2 seconds");
        doubleAfter2Seconds(30).then((c) => {
          console.log(x + " doubled 30 after 2 seconds");
          resolve(x + a + b + c);
        })
      })
    })
  });
}

addPromise(10).then((sum) => {
  console.log(sum);
});
addPromise(15).then((sum) => {
  console.log(sum);
});
