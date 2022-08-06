const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
const fs = require('fs');

// Fetches all the strings from a file
function getMessages() {
    const data = fs.readFileSync('data.txt', {encoding:'utf8', flag:'r'});
    console.log(data);

    printMenu();
}

// Submenu to add a new string to the file
function subMenuAddMessage() {
    rl.question("Enter a string: ", function(str) {
        addMessage(str + "\n");
        printMenu();
    });
}

function addMessage(line) {
    fs.appendFileSync('data.txt', line);
    console.log("Appended successfully");
}

// Menu function
function printMenu() {
    console.log("##########################");
    console.log("1. Add a string");
    console.log("2. Get all strings");
    console.log("Hit Ctrl-D to exit");
    console.log("##########################");
    
    rl.question("Make a choice: ", function(choice) {
        switch(choice) {
            case '1':
            subMenuAddMessage();
            break;
            
            case '2':
            getMessages();
            break;
        }
    });
}

// This is called after pressing Ctrl+C
rl.on("close", function() {
    console.log("\n Exiting...");
    process.exit(0);
});

// This is where the program starts executing after running from command line
// Please run using: node climenu.js
// (Do not use REPL)
printMenu();
