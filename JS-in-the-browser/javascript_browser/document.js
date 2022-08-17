function getXmlDoc(xmlString) {
  return (new DOMParser().parseFromString(xmlString, "application/xml"));
}

const test =
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
  
let testDoc = getXmlDoc(test);

testDoc.documentElement.nodeName;
testDoc.documentElement.getAttribute("rating");

testDoc.getElementsByTagName("lastName")[1].firstChild.nodeValue;

let rafie = testDoc.getElementsByTagName("customer")[0];
rafie.getAttribute("id");
testDoc.getElementById("a1234");


