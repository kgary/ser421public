function arrayLoops() {
  var names = ["Joe", "Jane", "John"];
  printArray1(names);
  printArray2(names);
  names.length = 6;
  printArray1(names);
  printArray2(names); 
}
    
function printArray1(array) {
  for(var i=0; i<array.length; i++) {
    console.log("[printArray1] array[%o] is %o", i, array[i]);
  }
}

function printArray2(array) {
  for(var i in array) {
    console.log("[printArray2] array[%o] is %o", i, array[i]);
  }
}

arrayLoops();

// Trick so that the Firebug console.log function will
// be ignored (instead of crashing) in Internet Explorer.
// Also see Firebug Lite and Faux Console if you want 
// logging to actually do something on IE.
// Firebug Lite: http://www.getfirebug.com/lite.html
// Faux Console: http://icant.co.uk/sandbox/fauxconsole/
 
try { console.log("Loading script"); 
} catch(e) { console = { log: function() {} }; }