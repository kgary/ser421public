function Circle(radius) {
  this.radius = radius;
  
  Circle.prototype.getArea = 
    function() {
      return(Math.PI * this. radius * this.radius);
    };
}

var c = new Circle(10);
c.getArea();         

// Trick so that the Firebug console.log function will
// be ignored (instead of crashing) in Internet Explorer.
// Also see Firebug Lite and Faux Console if you want 
// logging to actually do something on IE.
// Firebug Lite: http://www.getfirebug.com/lite.html
// Faux Console: http://icant.co.uk/sandbox/fauxconsole/
 
try { console.log("Loading script"); 
} catch(e) { console = { log: function() {} }; }