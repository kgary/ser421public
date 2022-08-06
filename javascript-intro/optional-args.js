"use strict";

function convertString(numString, /* Optional */ base) {
  if (typeof base == "undefined") {
    base = 10;
  }
  let num = parseInt(numString, base);
  console.log(numString + " base " + base + " equals " + num + " base 10.");
}

convertString("1010");
convertString("1010", 2);
convertString("2");
convertString("2", 16);
