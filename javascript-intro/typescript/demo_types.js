// Some examples of implicit types
// Comment in/out various lines to experiment with what TS says
// Note that it will go through and find all errors, not just the first
// Also it will still generate the Javascript when using the default tsc
// Example 1: implicity String
var university = "ASU"; // TS now thinks university is a string
university = 10; // it won't like this
// It works for other primitive types
var loves421 = true;
loves421 = "false";
// It works for computed values, and flags null here as a type too
var sum = 33 + 67;
sum = null;
// Example from O'Reilly book of a ternary expression. Note how it figures it out
var bestSong = Math.random() > 0.5
    ? "Chain of Fools"
    : "Respect";
// But what if the ternary results in 2 different type outcomes? Hover on bestSong2
var bestSong2 = Math.random() > 0.5
    ? "Unchain My Heart"
    : false;
// what you see is called a "union type" in TS. It knows it has to be string or boolean
// hence the string | boolean you see
// TS also picks up on invalid functions or other property access
// We'll use bestSong here since we know it is a string
console.log("bestSong does not have a property foo: " + bestSong.foo);
console.log("The length of bestSong is " + bestSong.length());
// What if we do not assign an initial expression? The type will be any
var foo;
// What will the type be at any step here?
foo = "bar";
console.log("Foo length is " + foo.length);
console.log(foo.toUpperCase());
foo = 20;
console.log("Foo length is " + foo.length);
console.log(foo.toUpperCase());
// implicit typing doesn't work well with incorrect explicit types
var college = "engineering"; // this is fine but redundant
var school2 = 42; // and this is just wrong
// you can also declare union types, which can union any number of them
// this works!
var ufoo;
ufoo = "foo";
console.log(ufoo.toUpperCase());
ufoo = 42;
ufoo = true;
ufoo.length; // this does not since our last assignment is boolean (narrowing)
var ufoo2;
// proceed as before
ufoo2 = "foo";
console.log(ufoo2.toUpperCase());
ufoo2 = 42;
ufoo2 = true;
ufoo2.length;
// Literal typing is a funky feature, we can do this:
var bestBaseballTeam;
bestBaseballTeam = "Red Sox"; // this is fine
bestBaseballTeam = "Yankees"; // this is not (for more than the obvious reason)
// The literal type (note the colon not the = above) prevents arbitrary strings
