function convertString(numString, /* Optional */ base) {
  if (typeof base == "undefined") {
    base = 10;
  }
  var num = parseInt(numString, base);
  console.log("%s base %o equals %o base 10.",
              numString, base, num);
}

convertString("1010");
convertString("1010", 2);
convertString("2");
convertString("2", 16);
  
  
// Trick so that the Firebug console.log function will
// be ignored (instead of crashing) in Internet Explorer.
// Also see Firebug Lite and Faux Console if you want 
// logging to actually do something on IE.
// Firebug Lite: http://www.getfirebug.com/lite.html
// Faux Console: https://github.com/csanquer/fauxconsole
 
try { console.log("Loading script"); 
} catch(e) { console = { log: function() {} }; }
