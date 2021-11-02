function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else {
    return(null);
  }
}

function sendRequest() {
  var request = getRequestObject();
  request.onreadystatechange = 
    function() { handleResponse(request); };
  request.open("GET", "http://localhost:8008/message-data.html", true);
  request.send(null);
}

function handleResponse(request) {
  switch (request.readyState) {
  case 0:
      console.log("UNSENT");
      break;
  case 1:
      console.log("OPENED");
      break;
  case 2:
      console.log("HEADERS RECEIVED");
      break;
  case 3:
      console.log("LOADING");
      break;
  case 4:
      alert(request.responseText);
      break;
  }
}
