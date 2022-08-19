// PARAMETER TYPING 
// functions can now have typed parameters
function computeArea(length: number, width: number) {
    return length * width;
}
console.log("Area: " + computeArea(5,10));
console.log("Area: " + computeArea(5,"ten"));    // obviously a problem
console.log("Area: " + computeArea(5));          // you need the right number too

// but we could make an optional parameter too like in JS
function computeArea2(length: number, width?: number) {
    if (!width) width = length;
    return length * width;
}
console.log("Area: " + computeArea2(5));          // now we are good

// we can use our object types as well
// from demo_objects.ts
type Rectangle = {
    length: number
    width:  number
}
let rect2 : Rectangle
rect2 = {
    length: 15,
    width: 25
}
// note: my VSCode picks up a duplicate type from demo_objects.ts even though
// it is not imported here. The tsc transpiler will not find it by default either
function computeArea3(r: Rectangle) {
    return r.length * r.width;
}
console.log("Area: " + computeArea3(rect2));          // good

// RETURN TYPING
// you can implicitly return type, in fact we did with computeArea
let x: number = computeArea(5,7);
let y: string = computeArea(5,7);    // oopsey!

// I personally do not like implicit typing, paticularly when it comes to return values
// explicit is better, and with parameter types gives us a classic signature

function computeArea4(length: number, width: number): number {
    return length * width;
}  // fine
function computeArea5(length: number, width: number): string {
    return length * width;
}  // not fine

// but what if want to pass around functions as parameters as we see in JS?
// the above signature definition puts explicit types on params and return types
// but there is no named type for the function itself. We need to do:
type computeAreaType = (length: number, width: number) => number;



// so we have arrow syntax and an identifier computeAreaType now, so
function timedCompute(l: number, w: number, ca: computeAreaType) {
    setTimeout(() => { 
        console.log("timedCompute: " + ca(l, w)); 
    }, 5000)
}
// works even though computeArea doesn't mention our type
timedCompute(3, 5, computeArea)
timedCompute(11,13,computeArea2)  // still works with the optional param
timedCompute(11,13,computeArea3)  // nope, does not know what a rectangle is
timedCompute(13,15,computeArea4)  // explicit function type signatures fine too if they match

// we can also do it inline
function timedCompute2(l: number, w: number, ca2: (le: number, wi: number) => number) {
    setTimeout(() => { 
        console.log("timedCompute2: " + ca2(l, w)); 
    }, 2000)
}
timedCompute2(7, 9, computeArea)
// I can send in a closure too
timedCompute2(1,3, (x,y) => { return x+y })
// but not one that doesn't match
timedCompute2(1,3, (x: string,y) => { return x+y })

// Functions in Javascript do not have to have a return, or could have an empty one,
// in which case undefined is the value of the expression. In TS we can use void to express this
// Note there is also a "never" you can use which means no return or thrown exception at all
function pointOfNoReturn(s: string): void {
    console.log("No return!")
}  // ok
function pointOfNoReturn2(s: string): void {
    console.log("No return!")
    return;
} // ok
function pointOfNoReturn3(s: string): void {
    console.log("No return!")
    return true;
} // not ok
function pointOfNoReturn4(s: string): never {
    console.log("No return!")
} // not ok - the function terminates
function pointOfNoReturn5(s: string): never {
    console.log("No return!")
    return;
} // not ok - has a return
