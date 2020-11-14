// From Mozilla Developer Network example on use of ==>
var elements = [
  'Hydrogen',
  'Helium',
  'Lithium',
  'Beryllium'
];

elements.map(function(element ) { 
  return element.length; 
}); // [8, 6, 7, 9]

elements.map(element => {
  return element.length;
}); // [8, 6, 7, 9]
elements.map(element => element.length); // [8, 6, 7, 9]
elements.map(({ length }) => length); // [8, 6, 7, 9]
