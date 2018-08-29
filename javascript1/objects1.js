"use strict";

function Circle(radius) {
  this.radius = radius;
  
  this.getArea = 
    function() {
      return(Math.PI * this. radius * this.radius);
    };
}

let c = new Circle(12);
console.log(c.getArea());
