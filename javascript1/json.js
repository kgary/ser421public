var person =
  { 'firstName':  'Brendan',
    'lastName':   'Eich',
    'bestFriend': { 'firstName': 'Chris',
                    'lastName':  'Wilson' },
    'greeting': function() {
                  return("Hi, I am " + this.firstName +
                         " " + this.lastName + ".");
                }
  };
  
person.firstName;
person.lastName;
person.bestFriend.firstName;
person.bestFriend.lastName;
person.greeting();