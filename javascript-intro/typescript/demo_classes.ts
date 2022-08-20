// Typescript support for classes
// The class looks like what you would expect at this point,
// but go check out the Javascript!
class CRectangle {
    length: number;
    width: number;
    readonly color: string;

    constructor(l: number, w: number) {
        this.length = l;
        this.width = w;
        this.color = "green";    // readoly properties can be assigned as declared or in constructor only
                                 // you could actually do this on interfaces too
        // this.z = 10;   // if you uncomment you will see you cannot deduce properties like in JS
    }

    // TS also allows a function syntax that does not transpile to the prototype as we talked
    // about with the Circle example in the notes. But I am not showing that here, as this is
    // the best and most understandable way to do it. The JS generated will put the method on the prototype
    computeArea() {
        return this.length * this.width
    }
}
let cr1 = new CRectangle(5,10);      // ok
cr1.computeArea();                   // ok
cr1.computeArea(2,3);                // nope
cr1.color = "blue";                  // nope, readonly 
cr1.z = 12;                          // nope, again cannot deduce properties
let cr2 = new CRectangle(9, "ten");  // nope

// One thing that is different compared to our objects and interfaces discussion before is that classes
// are added to the type system. This means we can use them in ways we saw in demo_types.ts
cr1 = "foo";  // cannot assign a typed variable of CRectangle to string

// but what is a little different is that I can create an object that looks like CRectangle and it is assignable

function CRectangleType(l, w, c) {
    this.len = l;
    this.wid = w;
    this.col = c;
    this.computeMyArea = function() { return this.len * this.wid; }
}
let cr3 = new CRectangleType(5,10, "yellow");
cr3.computeMyArea();

cr1 = cr3;
cr1;
cr1.computeMyArea();   // Typescript doesn't like this, but the generated JS works! Kinda weird

// Just like in Java, you can have a Class provide the implementation for an Interface
// This is about the most normal part of OO that I can see in TS/JS
interface IEllipse {
    minorAxis: number;
    majorAxis: number;
    computeArea(): number;
    readonly pi: number;
}

class Ellipse implements IEllipse {
    minorAxis: number;
    majorAxis: number;
    readonly pi: number = 3.1415;

    constructor(minor: number, major: number) {
        this.minorAxis = minor;
        this.majorAxis = major;
    }

    computeArea() {
        return this.pi * this.minorAxis * this.majorAxis;
    }
}

let ell1 = new Ellipse(5,10);
ell1.computeArea();

// and ... now we are having some OO fun!
class Circle extends Ellipse {
    constructor(radius: number) {
        super(radius, radius);
    }
}
let circ1 = new Circle(5);
circ1.computeArea();

// One thing that is less Java and more C++ is that your class can implement multiple interfaces
interface IPrint {
    printMe(): void
}

// mixin
class PrintableEllipse implements IEllipse, IPrint {
    minorAxis: number;
    majorAxis: number;
    readonly pi: number = 3.1415;

    constructor(minor: number, major: number) {
        this.minorAxis = minor;
        this.majorAxis = major;
    }

    computeArea() {
        return this.pi * this.minorAxis * this.majorAxis;
    }

    printMe() {
        console.log("Print Me!!!");
    }
}

// we also could have done a Decorator Pattern style
// this avoids all the prior cut-and-paste!
class PrintableEllipse2 extends Ellipse implements IPrint {
    printMe() {
        console.log("Print Me!!!");
    }
}

// Abstract classes are also allowed, providing default implementations of some
// behaviors but specifying thet extending classes must implement
abstract class ATriangle {
    base: number;
    height: number;
    constructor(b: number, h: number) {
        this.base = b;
        this.height = h;
    }
    computeArea() {
        return this.base * this.height * 0.5;
    }

    abstract getThirdAngle(angle1: number, angle2: number): number
}
// the rest is inherited from ATriangle here
class Triangle extends ATriangle {
    getThirdAngle(angle1, angle2) {
        return 180 - (angle1 + angle2);
    }
}

// Information Hiding
// In Javascript we can have a private class property using the # prefix
// In Typescript we can use readable keywords pubic, private, and protected
// These do not come through to the Javascript, instead substitute code is generated
class Trapezoid {
    width1: number;                 // this will be public
    public width2: number;          // this will be public
    protected height1: number;      // this can only be seen by subclasses
    private height2: number;        // this can only be seen by subclasses
    #isIsosceles : boolean;         // "true private" in JS notation
    // the difference is that if you generate the code you can still access height2 in the JS
    // interpreter console, whereas you will get an error for isIsosceles

    constructor(w1: number, w2: number, h1: number, h2: number) {
        this.width1 = w1;
        this.width2 = w2;
        this.height1 = h1;
        this.height2 = h2;
        this.#isIsosceles = (w1 == w2 && h1 == h2);
    }

    testIsosceles(): boolean {
        return this.#isIsosceles;
    }
}

class ThreeSidesEqualTrapezoid extends Trapezoid {
    constructor(a: number, b: number) {
        super(a, a, a, b);
        // this.isIsosceles = true;  // nope
    }
    getHeight1() { return this.height1; }   // ok
    getHeight2() { return this.height2; }   // nope, but JS will see it
}
// some tests
let t1 = new Trapezoid(3,4,5,7);
t1;
t1.testIsosceles();
t1.#isIsosceles;   // error
t1.isIsosceles;   // undefined
let t2 = new Trapezoid(3,3,5,5);
t2.testIsosceles();
let t3 = new ThreeSidesEqualTrapezoid(7,9);
t3;
t3.getHeight1();
t3.getHeight2();
t3.testIsosceles();