"use strict";

// Reminder: you do not need an HTML page to test basic pure-client-side
// JavaScript functions. Just open Firebug and cut/paste the expressions
// into the command line at the bottom (or right) of Console. 

let firstString = "aaxbbxxxcccxddd";
firstString.split("x");
firstString.split(/x*/);
firstString.split(/x+/);

let secondString = "foo123bar321baz222boo";
secondString.split("123");
secondString.split(/[123]+/);

let thirdString = "foo <blink>bar</BLINK> baz";
thirdString.replace(/<\/?blink>/gi, "");
thirdString.replace(/b./g, "QQ");
