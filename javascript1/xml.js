"use strict";

let text = "<bookstore><book>" +
"<title>Everyday Italian</title>" +
"<author>Giada De Laurentiis</author>" +
"<year>2005</year>" +
"<title>Everyday Latin</title>" +
"<author>Socrates</author>" +
"<year>300 B.C.</year>" +
    "</book></bookstore>";

//Uncomment the next line while executing the file via node command file 

//let DOMParser = require('xmldom').DOMParser;
let parser = new DOMParser();
let xmlDoc = parser.parseFromString(text,"text/xml");
xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue;
xmlDoc.getElementsByTagName("title")[1].childNodes[0].nodeValue;