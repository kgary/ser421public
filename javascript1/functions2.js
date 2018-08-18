function multiplier(m) {
  return(function(x) { return(x * m); });
}

function operate2() {
  var nums = [1, 2, 3];
  var functions =
    [multiplier(1/3), multiplier(3), multiplier(9)];
  for(var i=0; i<functions.length; i++) {
    for(var j=0; j<nums.length; j++) {
      var f = functions[i];
      var num = nums[j];
      console.log("Operation on " + num + " is " + f(num));
    }
  }
}

operate2();

