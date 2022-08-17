function on() {
  var span = 
    document.getElementById("messageRegion");
  span.innerHTML = 
    "<br/><font color='red'>Wow!</font><br/>";
}

function off() {
  var span = 
    document.getElementById("messageRegion");
  span.innerHTML = "";
}