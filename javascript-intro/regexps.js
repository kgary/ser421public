"use strict";

let firstString = "aaxbbxxxcccxddd";
console.log("\nfirstString = " + firstString);
console.log(firstString.split("x"));
console.log(firstString.split(/x*/));
console.log(firstString.split(/x+/));

let secondString = "foo123bar321baz222boo";
console.log("\nsecondString = " + secondString);
console.log(secondString.split("123"));
console.log(secondString.split(/[123]+/));

let thirdString = "foo <blink>bar</BLINK> baz";
console.log("\nthirdString = " + thirdString);
console.log(thirdString.replace(/<\/?blink>/gi, ""));
console.log(thirdString.replace(/b./g, "QQ"));
