"use strict";

function longestString(/* varargs */) {
  let longest = "";
  for(let i=0; i<arguments.length; i++) {
    let candidateString = arguments[i];
    if (candidateString.length > longest.length) {
      longest = candidateString;
    }
  }
  return(longest);
}

longestString("a", "bb", "ccc", "dddd");