setTimeout(function timeout() {
  console.log('TIMEOUT FIRED');
}, 0)

process.nextTick(function () {
	  process.nextTick(function () {
	    console.log(1);
	    process.nextTick(function () { console.log(2); });
	    process.nextTick(function () { console.log(3); });
	  });
	  process.nextTick(function () {
	    console.log(4);
	    process.nextTick(function () { console.log(5); });
	    process.nextTick(function () { console.log(6); });
	  });
	});
