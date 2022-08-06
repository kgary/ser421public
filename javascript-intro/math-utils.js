"use strict";

var MathUtils = new Object(); 
// Some people prefer to say "function MathUtils() {};"

MathUtils.fact = function(n) {
  if (n <= 1) {
    return(1);
  } else {
    return(n * MathUtils.fact(n-1));
  }
}

MathUtils.log10 = function(x) {
  return(Math.log(x)/Math.log(10));
}