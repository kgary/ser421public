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

async function addAsyncParallel(x) {
    try {
	const [val1, val2, val3] = await Promise.all([
	    doubleAfter2Seconds(10),
	    doubleAfter2Seconds(20),
	    doubleAfter2Seconds(30)
	]);
	return x + val1 + val2 + val3;
    } catch (e) {
	console.log(e);
    }
}

// Note we are tempted to do this as we have a simple return
// statement with the sum in both of these - but it will not
// work. By definition an async returns a Promise, so the return
// value really gets bound to the resolve underneath the hood.
// Comment out and see what you get!
// console.log(addAsync(20));
// console.log(addAsyncParallel(25));

addAsync(10).then((sum) => {
  console.log(sum);
});
addAsync(15).then((sum) => {
  console.log(sum);
});

addAsyncParallel(20).then((sum) => {
  console.log(sum);
});
addAsyncParallel(25).then((sum) => {
  console.log(sum);
});

