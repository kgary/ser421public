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
    console.log("[printArray1] array[" + i + "] is " + array[i]);
  }
}

function printArray2(array) {
  for(var i in array) {
    console.log("[printArray2] array[" + i + "] is " + array[i]);
  }
}

arrayLoops();

