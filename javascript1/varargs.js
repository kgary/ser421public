function longestString(/* varargs */) {
  var longest = "";
  for(var i=0; i<arguments.length; i++) {
    var candidateString = arguments[i];
    if (candidateString.length > longest.length) {
      longest = candidateString;
    }
  }
  return(longest);
}

longestString("a", "bb", "ccc", "dddd");