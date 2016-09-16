console.log('start');
process.nextTick(() => {
  console.log('nextTick callback');
});
console.log('scheduled');
function hello() { console.log('hello world'); }
hello();
process.nextTick(hello);
