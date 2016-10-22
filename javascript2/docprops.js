console.log(document.cookie.split(';'));
console.log(document.body);
console.log(document.anchors[0]);
console.log(document.domain);
console.log(document.URL);
console.log(document.title);
console.log("All the props in document not from it's proto chain");
for (var key in document) {
    if (document.hasOwnProperty(key)) {
        console.log(key+" = "+ document[key]);
    }
}