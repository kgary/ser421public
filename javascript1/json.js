"use strict";

let person = '{"firstName":"Butch", "lastName":"Cassidy", \
"bestFriend":{"firstName":"Sundance","lastName":"Kid"}, \
"age":30}';

let personObj = { 'firstName': 'Butch', 'lastName': 'Cassidy',
		  'bestFriend': {'firstName':'Sundance', 'lastName': 'Kid'},
		  'greeting': function() { console.log("Hello " + this.firstName); }
};

console.log(person.firstName);     // makes no sense, json string
console.log(personObj.firstName);  // makes perfect sense, object
console.log(personObj.greeting()); // you can have a function on it

let personObj2 = JSON.parse(person);
console.log(personObj2.firstName);

let f = function(key, val) {
    if (key == "firstName") {
	   return (val+"ee");
    }
    // Thanks Chris H. for pointing out the 
    // missing return of val - love Javascript!
    return val;
};


let personObj3 = JSON.parse(person, f);
console.log(personObj3.firstName);
