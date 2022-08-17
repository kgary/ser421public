// Try these interactively in your browser js console
// but first you need to load a remote page

// Try this, close the tab, try again, what happens?
if (sessionStorage.count) sessionStorage.count++;
else sessionStorage.count = 0;
console.log(sessionStorage.count);

// Now close the browser again and try it
if (localStorage.count) localStorage.count++;
else localStorage.count = 1;
console.log(localStorage.count);
