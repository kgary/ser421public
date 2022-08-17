function showInfo(node, indent) {
  if (node.nodeType == Node.TEXT_NODE) {
    console.log("%s Body content is '%s'.", 
                spaces(indent), node.nodeValue);
  } else if (node.nodeType == Node.ELEMENT_NODE) {
    console.log("%s Found element '%s'.", 
                spaces(indent), node.nodeName);
    let children = node.childNodes;
    for(var i=0; i<children.length; i++) {
      showInfo(children[i], indent+1);
    }
  }
}

function spaces(n) {
  let indentString = "  ";
  let result = "";
  for(var i=0; i<n; i++) {
    result = result.concat(indentString);
  }
  return(result);
}

// we saw this in document.js
const test2 =
  "<customers rating='vip'>" +
    "<customer id='a1234'>" +
      "<firstName>Rafael</firstName>" +
      "<lastName>Nadal</lastName>" +
    "</customer>" +
    "<customer id='a1235'>" +
      "<firstName>Roger</firstName>" +
      "<lastName>Federer</lastName>" +
    "</customer>" +
    "</customers>";
var testDoc2 = new DOMParser().parseFromString(test2, "application/xml");

testDoc2.documentElement.normalize();
showInfo(testDoc2.documentElement, 0);