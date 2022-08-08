"use strict";

let text = "<bookstore><book>" +
"<title>Everyday Italian</title>" +
"<author>Giada De Laurentiis</author>" +
"<year>2005</year>" +
"<title>Everyday Latin</title>" +
"<author>Socrates</author>" +
"<year>300 B.C.</year>" +
    "</book></bookstore>";

// Uncomment the next line if executing the file via nodeJS REPL to include
// the parser. This will  run using the browser's built-in XML DOM parser instead.
// Install xmldom for node using npm "npm install xmldom" first if running via node

// const { DOMParser } = require('xmldom');
let parser = new DOMParser();
let xmlDoc = parser.parseFromString(text,"text/xml");
console.log(xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue);
console.log(xmlDoc.getElementsByTagName("title")[1].childNodes[0].nodeValue);
