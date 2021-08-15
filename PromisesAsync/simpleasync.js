// https://thecodebarbarian.com/80-20-guide-to-async-await-in-node.js.html
async function test() {
  await new Promise((resolve, reject) => setTimeout(() => resolve(), 1000));
  console.log('Hello, World!');
}

test();
