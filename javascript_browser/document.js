function getXmlDoc(xmlString) {
  var parser = new DOMParser();
  var xmlDocument =
    parser.parseFromString(xmlString, "application/xml");
  return(xmlDocument);
}

var test =
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
  
var testDoc = getXmlDoc(test);

testDoc.documentElement.nodeName;
testDoc.documentElement.getAttribute("rating");

testDoc.getElementsByTagName("lastName")[1].firstChild.nodeValue;

var rafie = testDoc.getElementsByTagName("customer")[0];
rafie.getAttribute("id");
testDoc.getElementById("a1234");


