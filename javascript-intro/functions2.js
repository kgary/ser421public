"use strict";

function multiplier(m) {
  return(function(x) { return(x * m); });
}

function operate2() {
  let nums = [1, 2, 3];
  let functions =
    [multiplier(1/3), multiplier(3), multiplier(9)];
  for(let i=0; i<functions.length; i++) {
    for(let j=0; j<nums.length; j++) {
      let f = functions[i];
      let num = nums[j];
      console.log("Operation on " + num + " is " + f(num));
    }
  }
}

operate2();

