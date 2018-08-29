function someCalculation(num) {
  console.log("in someCalculation, num is " + num);
  return num * num;
}
function someFunction(args) {
  var val = someCalculation(args);
  return(function(moreArgs) {
           return doSomethingWith(val, moreArgs);
         });  
}
function doSomethingWith(v, params) {
    console.log("doSomethingWith(" + v + ", " + params + ")");
    return someCalculation(v - params);
}
var f1 = someFunction(5);
console.log("The f1 function is " + f1);
var f2 = someFunction(7);
console.log("The f2 function is " + f2);

console.log(f1(10));
console.log(f2(19));


