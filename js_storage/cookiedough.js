// Some various cookie expressions and functions

// This function is on w3schools as a simple way to tease out a cookie from the cookie string
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}
// w3schools set a cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


// OK some interaction - output our cookies
console.log(document.cookie);
// set a cookie
document.cookie="foo=bar";
// output again, note this one has been added
console.log(document.cookie);
// get it using the function above
getCookie("foo");
// Set a new cookie
setCookie("hokey", "pokey", 1);
console.log(document.cookie);
// Now expire the cookie
setCookie("hokey", "pokey", 0);
console.log(document.cookie);
