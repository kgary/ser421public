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
      console.log("Operation on %o is %o.", 
                  num, f(num));
    }
  }
}

operate2();

// Trick so that the Firebug console.log function will
// be ignored (instead of crashing) in Internet Explorer.
// Also see Firebug Lite and Faux Console if you want 
// logging to actually do something on IE.
// Firebug Lite: http://www.getfirebug.com/lite.html
// Faux Console: https://github.com/csanquer/fauxconsole
 
try { console.log("Loading script"); 
} catch(e) { console = { log: function() {} }; }
