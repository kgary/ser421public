console.log('EXTERNAL SCRIPT JSDOMReplace3FooEl EXECUTED OFF ' + document.domain);
var fooElem=document.getElementById("foo");
if (fooElem == 'undefined' || fooElem == null) console.log("GOT NO REMOTE FOO");
else {
    console.log("GOT ME SOME REMOTE FOO");
    fooElem.innerHTML="Table 1 changed FOO";
    fooElem.addEventListener('click', function(e) { alert('CLICK ' + e); e.currentTarget.style.color='#DD0'; }, false);
}
