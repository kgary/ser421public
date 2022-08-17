function getMessage() {
  var amount = Math.round(Math.random() * 100000);
  var message =
    "You won $" + amount + "!\n" +
    "To collect your winnings, send your credit card\n" +
    "and bank details to oil-minister@phisher.com.";
  return(message);
}

function showWinnings1() {
	var elem = document.getElementById('fish');
	elem.innerHTML=getMessage();
 // alert(getMessage());
}

function showWinnings2() {
  document.write("<h1><blink>" + getMessage() +
                 "</blink></h1>");
}
