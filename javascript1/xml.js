"use strict";

let text = "<bookstore><book>" +
"<title>Everyday Italian</title>" +
"<author>Giada De Laurentiis</author>" +
"<year>2005</year>" +
"<title>Everyday Latin</title>" +
"<author>Socrates</author>" +
"<year>300 B.C.</year>" +
    "</book></bookstore>";

// Comment the next line if executing the file via a browser dev console
// so that it uses the browser's built-in XML DOM parser instead. Install
// xmldom for node using npm "npm install xmldom" first if running via node

let DOMParser = require('xmldom').DOMParser;
let parser = new DOMParser();
let xmlDoc = parser.parseFromString(text,"text/xml");
console.log(xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue);
console.log(xmlDoc.getElementsByTagName("title")[1].childNodes[0].nodeValue);
