a = new Array();
// []
a instanceof Array;
// true
typeof a;
// 'object'
Array.prototype
// Object(0) []
b = 5;
// 5
typeof b;
// 'number'
c = "str";
// 'str'
typeof c;
// 'string'
d = true;
// true
typeof d;
// 'boolean'
d = 7;
// 7
typeof d;
// 'number'
typeof ("3+4"+5);
// 'string'
d instanceof String;
//false
c instanceof String;
// false
c instanceof Object;
// false - why?
Object.prototype.toString.call(c);
// '[object String]'
// so it was not a "case" thing
Object.getPrototypeOf(b);
// {} - it is a primitive
Object.getPrototypeOf(a);
// Array: Object(0) []
Object.getOwnPropertyNames(b);
// [] primitive
Object.getOwnPropertyNames(c);
// [ '0', '1', '2', 'length' ] string
Object.getOwnPropertyNames(a);
// [ 'length' ]  Array - shows the enumerables
// This shows all on the prototype
Object.getOwnPropertyNames(Array.prototype);
// [
//  'length',      'constructor',    'concat',
//  'copyWithin',  'fill',           'find',
//  'findIndex',   'lastIndexOf',    'pop',
//  'push',        'reverse',        'shift',
//  'unshift',     'slice',          'sort',
//  'splice',      'includes',       'indexOf',
//  'join',        'keys',           'entries',
//  'values',      'forEach',        'filter',
//  'flat',        'flatMap',        'map',
//  'every',       'some',           'reduce',
//  'reduceRight', 'toLocaleString', 'toString'
// ]
function getAllMethods(obj) { 
   return Object.getOwnPropertyNames(obj).filter(function(property){ 
		    return typeof obj[property] == 'function'; }); 
}
getAllMethods(a);
//[]
getAllMethods(b);
//[]
getAllMethods(c);
//[]
getAllMethods(d);
//[]
getAllMethods(Array.prototype);
// [
//  'constructor',    'concat',   'copyWithin',
//  'fill',           'find',     'findIndex',
//  'lastIndexOf',    'pop',      'push',
//  'reverse',        'shift',    'unshift',
//  'slice',          'sort',     'splice',
//  'includes',       'indexOf',  'join',
//  'keys',           'entries',  'values',
//  'forEach',        'filter',   'flat',
//  'flatMap',        'map',      'every',
//  'some',           'reduce',   'reduceRight',
//  'toLocaleString', 'toString'
// ]
getAllMethods(String.prototype);
// [
//  'constructor',       'anchor',            'big',
//  'blink',             'bold',              'charAt',
//  'charCodeAt',        'codePointAt',       'concat',
//  'endsWith',          'fontcolor',         'fontsize',
//  'fixed',             'includes',          'indexOf',
//  'italics',           'lastIndexOf',       'link',
//  'localeCompare',     'match',             'matchAll',
//  'normalize',         'padEnd',            'padStart',
//  'repeat',            'replace',           'search',
//  'slice',             'small',             'split',
//  'strike',            'sub',               'substr',
//  'substring',         'sup',               'startsWith',
// 'toString',          'trim',              'trimStart',
//  'trimLeft',          'trimEnd',           'trimRight',
//  'toLocaleLowerCase', 'toLocaleUpperCase', 'toLowerCase',
//  'toUpperCase',       'valueOf'
// ]
getAllMethods(Object.prototype);
// [
//  'constructor',
//  '__defineGetter__',
//  '__defineSetter__',
//  'hasOwnProperty',
//  '__lookupGetter__',
//  '__lookupSetter__',
//  'isPrototypeOf',
//  'propertyIsEnumerable',
//  'toString',
//  'valueOf',
//  'toLocaleString'
// ]
