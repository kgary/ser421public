function Circle(radius) {
  this.radius = radius;
  
  Circle.prototype.getArea = 
    function() {
      return(Math.PI * this. radius * this.radius);
    };
}

var c = new Circle(10);
c.getArea();         
