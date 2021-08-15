 function sleep(milliSeconds){
		var startTime = new Date().getTime(); // get the current time
		while (new Date().getTime() < startTime + milliSeconds); // hog cpu
	}
sleep(3000);
console.log('EXTERNAL SCRIPT EXECUTED OFF ' + document.domain);
alert(document.domain);
