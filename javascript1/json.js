var person = '{"firstName":"Butch", "lastName":"Cassidy", \
"bestFriend":{"firstName":"Sundance","lastName":"Kid"}, \
"age":30}';

var personObj = { 'firstName': 'Butch', 'lastName': 'Cassidy',
		  'bestFriend': {'firstName':'Sundance', 'lastName': 'Kid'},
		  'greeting': function() { console.log("Hello " + this.firstName); }
};

console.log(person.firstName);     // makes no sense, json string
console.log(personObj.firstName);  // makes perfect sense, object
console.log(personObj.greeting()); // you can have a function on it

var personObj2 = JSON.parse(person);
console.log(personObj2.firstName);

var f = function(key, val) {
    console.log("***" + key);
    console.log("***" + val);
    if (key == "firstName") {
	return (val+"ee");
    }
};

var personObj3 = JSON.parse(person, f);
console.log(personObj3.firstName);