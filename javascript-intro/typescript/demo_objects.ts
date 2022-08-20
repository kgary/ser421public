// we can specify object literals with implicit types
let rect1 = {
    length: 10,
    width: 20
}
// these work ( as in JS)
console.log("Length is " + rect1[length] + ", width is " + rect1.width)
// these will not work
console.log(rect1.area)   // not declared, JS would let you do it though
rect1 = "rectangle"       // like before, the type is already set

// we can apply our type aliasing here to good effect
type Rectangle = {
    length: number
    width:  number
}
let rect2 : Rectangle
rect2 = {
    length: 15,
    width: 25
}
rect2 = "rectangle"  // same problem

// this is a little weird to me
type HeightOnly = { h: number }
const h = 10;
const honly: HeightOnly = { h }  // makes sense

const rect3 = {
    h: 20,
    w: 30
}
let rect4:Rectangle = rect3    // Note that name and type have to match
let height:HeightOnly = rect3  // this is fine as h matches in name and type; what is w?
console.log(height.w)          // it was dropped!

// we can nest
type Cuboid = {
    r: Rectangle
    d: number
}
const myCuboid: Cuboid = {
    r: {
        length: 5,
        width: 7
    },
    d: 9
}   // this is OK

// you can have optional properties on object declrations
type CuteCuboid = {
    c: Cuboid,
    color?: string   // ? makes it optional
}
let cc1: CuteCuboid = { c: myCuboid }   // ok even though no color
let cc2: CuteCuboid = { c: myCuboid, color: "green" }  // has color too!

// union type concepts extend to objects, and there is an intersection concept too,
// but I do not like either w.r.t. OO semantics so I am not including them here.

// What about interfaces? They look like object types:
interface IRectangle {
    length: number,
    width: number
}
// same assignability cases apply here as they did with type Rectangle above
// What about the compatibility of type Rectangle and interface IRectangle?
let irect2: IRectangle = { 
    length: 10,
    width: 20
}
rect2 = {
    length: 15,
    width: 25
}
rect2 = irect2;  // assignable!

// what is the difference then between types and interfaces? typed object literals are just 
// that - literals. They cannot be merged, used to check class declarations, etc. while interfaces can
// generally speaking then it is better (and usualy more intuitive) to use interfaces

// interfaces can have read-only values
// interfaces can have typed functions as interface members
interface IRectangle2 {
    length: number;
    width: number;
    // two ways of declaring a method
    getArea: () => number;
    getArea2(): number;
}
const ir2: IRectangle2 = {
    length: 90,
    width: 89,
    getArea: () => { return ir2.length * ir2.length },
    getArea2() { return this.length * this.length }
}