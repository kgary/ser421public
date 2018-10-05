// from (with slight modifications
// https://medium.com/@tkssharma/writing-neat-asynchronous-node-js-code-with-promises-async-await-fa8d8b0bcd7c
function doubleAfter2Seconds(x) {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(x * 2);
    }, 2000);
  });
}

async function addAsync(x) {
    const a = await doubleAfter2Seconds(10);
    console.log(x + " doubled 10 after 2 seconds");
    const b = await doubleAfter2Seconds(20);
    console.log(x + " doubled 20 after 2 seconds");
    const c = await doubleAfter2Seconds(30);
    console.log(x + " doubled 30 after 2 seconds");
    return x + a + b + c;
}


addAsync(10).then((sum) => {
  console.log(sum);
});
addAsync(15).then((sum) => {
  console.log(sum);
});

