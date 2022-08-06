'use strict'; 

// Function expression assigned to a global variable
var calcFunc = function(x) { 
  let tax = x * x;
  return tax; 
}
console.log("1. " + calcFunc(5)); // 25
try {
  console.log("2. " + tax);         // undefined tax
} catch (err) {
  console.log("Caught exception trying to refer to tax");
}
function calc2Func(y) {  // function declaration
  var tax2 = y * y;
  console.log("3. " + tax2);
  return tax2;
}
console.log("4. " + calc2Func(3)); // 9
// console.log("5. " + tax2);        // undefined

var tax3;
function calc3Func(y) {  // function declaration
  tax3 = y * y;
  console.log("6. " + tax3);
  return tax3;
}
console.log("7. " + calc3Func(4)); // 16
console.log("8. " + tax3);         // 16

function calc4Func(z) { // function declaration
  let foo = 'bar';
  if (z == 1) {
	let foo = 'fighters';
	console.log("foo " + foo);
  }
  console.log("outer foo " + foo);
  return foo;
}
console.log("9. " + calc4Func(1));
console.log("10. " + calc4Func(2));

