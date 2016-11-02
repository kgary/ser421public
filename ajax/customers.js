// relies on ajax-basics-1.js for getRequestObject
function showCustomersAJAX(address, resultRegion, typeFlag) {
 var request = getRequestObject();
  request.onreadystatechange = 
	function() {
	    if (typeFlag === "XML") {
		showCustomersXML(request, resultRegion);
	    } else {
		showCustomersJSON(request, resultRegion);
	    }
	};
  request.open("GET", address, true);
  request.send(null);
}

function showCustomersXML(request, resultRegion) {
    console.log("showCustomersXML");
  if ((request.readyState == 4) &&
      (request.status == 200)) {
      var xmlDocument = request.responseXML;
      var fnames = xmlDocument.getElementsByTagName("firstName");
      var lnames = xmlDocument.getElementsByTagName("lastName");
      var str = "<p>";
      for(var i=0; i<fnames.length; i++) {
	  str += fnames[i].childNodes[0].nodeValue + " ";
	  str += lnames[i].childNodes[0].nodeValue + "<br/>";
      }
      str += "</p>";
    document.getElementById(resultRegion).innerHTML=str;
  }
}
// json version
function showCustomersJSON(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
      var jsonDocument = JSON.parse(request.responseText);
      var str = "<p>";
      for (ji in jsonDocument.customers.customer) {
	  nextCustomer = jsonDocument.customers.customer[ji]; 
	  console.log(nextCustomer.firstName);
	  console.log(nextCustomer.lastName);
	  str += nextCustomer.firstName + " ";
	  str += nextCustomer.lastName + "<br/>";
      }
      str += "</p>";
      document.getElementById(resultRegion).innerHTML=str;
  }
}
