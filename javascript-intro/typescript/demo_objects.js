// we can specify object literals with implicit types
var rect1 = {
    length: 10,
    width: 20
};
// these work ( as in JS)
console.log("Length is " + rect1[length] + ", width is " + rect1.width);
// these will not work
console.log(rect1.area); // not declared, JS would let you do it though
rect1 = "rectangle"; // like before, the type is already set
var rect2;
rect2 = {
    length: 15,
    width: 25
};
rect2 = "rectangle"; // same problem
var h = 10;
var honly = { h: h }; // makes sense
var rect3 = {
    h: 20,
    w: 30
};
var rect4 = rect3; // Note that name and type have to match
var height = rect3; // this is fine as h matches in name and type; what is w?
console.log(height.w); // it was dropped!
var myCuboid = {
    r: {
        length: 5,
        width: 7
    },
    d: 9
}; // this is OK
var cc1 = { c: myCuboid }; // ok even though no color
var cc2 = { c: myCuboid, color: "green" }; // has color too!
// same assignability cases apply here as they did with type Rectangle above
// What about the compatibility of type Rectangle and interface IRectangle?
var irect2 = {
    length: 10,
    width: 20
};
rect2 = {
    length: 15,
    width: 25
};
rect2 = irect2; // assignable!
var ir2 = {
    length: 90,
    width: 89,
    getArea: function () { return ir2.length * ir2.width; },
    getArea2: function () { return this.length * this.width; }
};
// Note that we do not "inherit" implementations, only interfaces. So when we provide
// an implementation we need everything
var cfr2 = {
    length: 20,
    width: 30,
    depth: 10,
    getArea: function () { return cfr2.length * cfr2.width; },
    getArea2: function () { return this.length * this.width; },
    getVolume: function () { return this.depth * this.getArea2(); }
};
