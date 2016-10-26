// ------------------
// Begin common area

// These should be immutable global vars
const qorderarr=['DaysInPain','NightSleptPoorly','TroubleTakingCare','MissedSchoolWork','LeftEarly','UnableToEnjoy','FeelingSad'];
const qtypearr =['mcsa','mcsa','mcsa','mcsa','mcsa','mcsa','mcsa'];
const qquesttxt=new Array();
qquesttxt[0]='How many days have you had any pain?';
qquesttxt[1]='How many nights have you slept poorly because of pain?';
qquesttxt[2]='How many days have you had trouble taking care of yourself because of pain ?';
qquesttxt[3]='How many days have you missed school/work because of pain?';
qquesttxt[4]='How many days have you left school/work early because of pain?';
qquesttxt[5]='How many days have you been unable to do things you enjoy because of pain?';
qquesttxt[6]='How many days have you felt sad, mad or upset because of pain ?';

function varCheck(v) {
    if (v == null || v == 'undefined') {
        return false;
    } else {
        return true;
    }
}

// Got this from http://stackoverflow.com/questions/9496427/how-to-get-elements-by-attribute-selector-w-native-javascript-w-o-queryselector
// Need this so we can find all widget elements with attribute "_prAnswer"
function getAllElementsWithAttribute(attribute)
{
  var matchingElements = [];
  var allElements = document.getElementsByTagName('*');
  for (var i = 0; i < allElements.length; i++)
  {
    if (allElements[i].getAttribute(attribute))
    {
      // Element exists with attribute. Add to array.
      matchingElements.push(allElements[i]);
    }
  }
  return matchingElements;
}

// End common area
// ----------------

function getPageId() {
    // put it in the session
    return sessionStorage.getItem('currentPageId');
}

function clearAnswers() {
    for (var i = 0; i < qorderarr.length; i++) {
        console.log('Cleared ' + qorderarr[i]+'Ans');
        sessionStorage.removeItem(qorderarr[i]+'Ans');
    }
}

// The localStorage init for PIN needs to come from the app
// provisioning process, this is mock code
function initPainReport() {
    localStorage.setItem('PIN','11111');
    sessionStorage.setItem('targetPageId', qorderarr[0]);
    // clear any session storage answers
    clearAnswers();    
}

function echoAnswers() {
    for (var i = 0; i < qorderarr.length; i++) {
        console.log(qorderarr[i] + ' = ' + sessionStorage.getItem(qorderarr[i]+'Ans'));
    }
}

function prepAnswersForSubmit() {
	var finalAns = {};
	for (var i = 0; i < qorderarr.length; i++) {
		finalAns[qorderarr[i]] = sessionStorage.getItem(qorderarr[i]+'Ans');
	}
	return JSON.stringify(finalAns);
}

function checkPIN(el) {
    var rval = false;
    console.log("Password is " + el.value);
    if (varCheck(el) && el.type == 'password' && el.value.length == 5) {
        var storedPIN = localStorage.getItem('PIN');
        var respElement = document.getElementById('PINcheck');
        if (varCheck(storedPIN) && el.value == storedPIN) { 
            respElement.innerHTML='Correct PIN entered<br/><input type="button" id="nextBtn" value="submit" onclick="echoAnswers()" />\n<br/>Look in your console</br/><a href="./index.html">Go home</a>\n'
	    //<input type="submit" value="submit"/>';            
        } else {
            respElement.innerHTML='Incorrect PIN entered, try again';
        }
    }
    return rval;
}

function chkAnswers() {
    var prresults = document.getElementById("_prans");
    if (!varCheck(prresults)) {
        console.log("Could not get DOM element _prans");
        return;
    }
    var resultHTML = '';
    for (var i = 0; i < qorderarr.length; i++) {
        var answer = sessionStorage.getItem(qorderarr[i]+'Ans');
         if (answer == '{}' || answer == null || typeof answer == 'undefined') {
            resultHTML += '<li>Please answer the question on the ' + '<a href="#" onclick=\'navToQuestionURL(' + i + ');\' a>' + qorderarr[i] + '<\a> page </li>';
        }
    }
    if (resultHTML == '') {
        // All questions were answered, instead show the PIN
        resultHTML += 'Thank you for completing the questions. Please enter your PIN and press submit<br/>\n';
	// We don't do anything for real in this app, so shipping it off to a snoop for echo purposes
//        resultHTML += '<form method="POST" action="http://lead2.poly.asu.edu:8081/examples/snoop.jsp">\n';
//      for(var i = 0; i < qorderarr.length; i++) {
//        		resultHTML += '<input type="hidden" name="'+qorderarr[i]+'" value="'+sessionStorage.getItem(qorderarr[i]+'Ans')+'/>';
//        }
        resultHTML += '<input type="password" size="5" name="passwd" maxLength="5" onkeyup="checkPIN(this)" />';
        resultHTML += '<div id="PINcheck"></div>'

        prresults.innerHTML = resultHTML;
    } else {
        prresults.innerHTML = '<ul>'+resultHTML+'</ul>';
    }
}


function saveAnswer(ans) {
    var qId = getPageId();    
    sessionStorage.setItem(qId + 'Ans', ans);
}

function getDisplayQuestionURL(delta) {
    // get the current question
    var qId = getPageId();
    var qn = qorderarr.indexOf(qId);
    var targetQn = qn + Number(delta);
    var targetURL = '';
    
    if (targetQn < 0) {
        targetURL = qtypearr[0]+'.html';
        sessionStorage.setItem('targetPageId', qorderarr[0]);
    } else if (targetQn >= qtypearr.length) {
        targetURL = 'submit.html';
        sessionStorage.setItem('targetPageId', 'debrief');
    } else {  // must be a valid index
        targetURL = qtypearr[targetQn]+'.html';
        sessionStorage.setItem('targetPageId', qorderarr[targetQn]);
    }
    return targetURL;
}


function navToQuestionURL(index) {
    var qn = index;
    var targetQn = qn;
    var targetURL = '';
    
    if (targetQn < 0) {
        targetURL = qtypearr[0]+'.html';
        sessionStorage.setItem('targetPageId', qorderarr[0]);
    } else if (targetQn >= qtypearr.length) {
        targetURL = 'submit.html';
        sessionStorage.setItem('targetPageId', 'debrief');
    } else {  // must be a valid index
        targetURL = qtypearr[targetQn]+'.html';
        sessionStorage.setItem('targetPageId', qorderarr[targetQn]);
    }
    window.location.assign(targetURL);
}

function displayTarget(delta) {
    var targetURL = getDisplayQuestionURL(delta);
    var targetPageId = sessionStorage.getItem('targetPageId');
    if (targetPageId != null && targetPageId != 'undefined') { 
        window.location.assign(targetURL);
        // now we set the page id on the page load (finalizePageRender below)
    } else {
        console.log('ERROR: could not get target page id!');
    }
}

function printNav() {
    var prNavEl = document.getElementById('_prnav');
    var qId = getPageId();
    var qn = qorderarr.indexOf(qId);
    
    if (varCheck(prNavEl)) {
        if (qn == 0) {
            prNavEl.innerHTML = '<input type="button" id="nextBtn" value="Next" onclick="displayTarget(1)" />';
        } else {
            prNavEl.innerHTML = '<input type="button" value="Prev" onclick="displayTarget(-1)"><input type="button" id="nextBtn" value="Next" onclick="displayTarget(1)" />';
        }
    }
}

function disableNextButton(disableIt) {
    var btnEl = document.getElementById("nextBtn");
    if (varCheck(btnEl)) {
        console.log("FOUND THE BUTTON " + disableIt);
        btnEl.disabled=disableIt;
    }
}

/*
 * When we leave a page (anyway it happens), we want to
 * save the answer state of that page for all answer elements on it. The
 * issue is how to determine all of those answer elements. Solution idea
 * is to create a custom attribute _prAnswer with it's value set to a
 * unique question identifier. When we save we read the property of the
 * widget and store { PK : VALUE }.
 */
function collectPageAnswerState() {
    var ansArr = getAllElementsWithAttribute('_prAnswer');
    var newArr = {};
    console.log('Num elements with _prAnswer: ' + ansArr.length);
    
    for (var i = 0; i < ansArr.length; i++)  {
        // all array entries are widget elements
        // Can't use a switch because the element we are dealing with is
        // identified either by type attribute or tagname
        var thisElem = ansArr[i];
        var tagname  = thisElem.nodeName.toLowerCase();
        var tagtype  = thisElem.getAttribute('type');
        if (varCheck(tagtype)) {
            tagtype = tagtype.toLowerCase();
        }
        var itemId   = thisElem.getAttribute('_prAnswer');
        
        console.log('\tElement ' + i + ' ' + tagname + ' ' + tagtype + ' ' + itemId);
        if (tagname == 'range' || (tagtype != null && tagtype == 'range'))  {
            // slider: if we have a range we always record it's value
            newArr[itemId] = thisElem.value;
        }
        else if (tagname == 'input' && (tagtype != null && (tagtype == 'radio' || tagtype == 'checkbox'))) {
            // check to see if it is checked
             if (thisElem.checked == true) {
                newArr[itemId] = true;
             }
        } else if (tagname == 'textarea' || (tagtype != null && tagtype == 'text')) {
            var txtValue = thisElem.value;
            if (txtValue != null && txtValue != 'undefined' && txtValue.trim().length > 0) {
                newArr[itemId] = txtValue;
            }
        } 
    }
    console.log(JSON.stringify(newArr));
    return newArr;
}

function onUnloadListener() {
    var pageArr = collectPageAnswerState();
    console.log('UNLOAD CALLED');
    if (varCheck(pageArr)) {
        console.log('Saving page state: ' + JSON.stringify(pageArr));
        saveAnswer(JSON.stringify(pageArr));
    }
}

// check to see if there was a label. Thanks to
// http://stackoverflow.com/questions/285522/find-html-label-associated-with-a-given-input

function findLabelForElement(el) {
    if (varCheck(el)) {
        var idVal = el.id;
        labels = document.getElementsByTagName('label');
        for (var i = 0; i < labels.length; i++) {
            if (labels[i].htmlFor == idVal)
                return labels[i];
        }
    }
}


function onChangeListener(el) {
	
    var pageArr = collectPageAnswerState();
    console.log("ANSWERS: " + JSON.stringify(pageArr).length);
	
    if (varCheck(pageArr) && JSON.stringify(pageArr).length > 2) {  // 2 for {}
        disableNextButton(false);
    } else {
        disableNextButton(true);
    }
    
    // change the label if there is one
    var label = findLabelForElement(el);
    if (varCheck(label)) {
        label.innerHTML = el.value;
    }
}

/*
 * This should be called after the rest of the page has loaded. The goal is
 * to 1) check for answers on this page, 2) if there are not answers, disable the
 * Next button, 3) if there are answers to populate state in the answer options.
 */ 
function finalizePageRender() {
    // first: set the current page id
    var targetPageId = sessionStorage.getItem('targetPageId');
    sessionStorage.setItem('currentPageId', targetPageId);
    
    // standard content template substitutions
    // Must be done before trying to manipulate widget properties
    printNav();
    printFooterIndex();
    printQuestion();
      
    var pageAns = sessionStorage.getItem(getPageId()+'Ans');

    if (varCheck(pageAns) && pageAns.length > 2) { // JSON {}
        disableNextButton(false);
    } else {
        disableNextButton(true);
        return;
    }
       
    // OK, this is the part where we re-render answers    
    var ansArr = getAllElementsWithAttribute('_prAnswer');
    var pageId = sessionStorage.getItem('currentPageId');
    var arrayAns = JSON.parse(pageAns);

    console.log('RENDER: Num elements with _prAnswer: ' + ansArr.length);

    // for each tag found, determine if we have an answer already for it
    for (var i = 0; i < ansArr.length; i++) {
        // all array entries are widget elements
        // Can't use a switch because the element we are dealing with is
        // identified either by type attribute or tagname
        var thisElem = ansArr[i];
        var tagname = thisElem.nodeName.toLowerCase();
        var tagtype = thisElem.getAttribute('type');
        if (varCheck(tagtype)) {
            tagtype = tagtype.toLowerCase();
        }
        var itemId = thisElem.getAttribute('_prAnswer');
        var ansValue = arrayAns[itemId];

        console.log('\tElement ' + i + ' ' + tagname + ' ' + tagtype + ' ' + itemId + ' VALUE = ' + ansValue);
        console.log("thisElem is " + thisElem);
        if (varCheck(ansValue)) {
            if (tagname == 'range' || (tagtype != null && tagtype == 'range')) {
                // slider: if we have a range we always record it's value
                thisElem.value = ansValue;
                // check for a label associated with this slider
                var label = findLabelForElement(thisElem);
                if (varCheck(label)) {
                    label.innerHTML = ansValue;
                }
            } else if (tagname == 'input' && (tagtype != null && (tagtype == 'radio' || tagtype == 'checkbox'))) {
                // check to see if it is checked
                if (ansValue == true) {
                    console.log("SETTING IT TO CHECKED");
                    thisElem.checked = true;                    
                }
            } else if (tagname == 'textarea' || (tagtype != null && tagtype == 'text')) {
                thisElem.value = ansValue;
            }
	}
    }
}

function printFooterIndex() {
    var qId = getPageId();
    var qn = qorderarr.indexOf(qId);
    var prNavEl = document.getElementById('_prfooter');
    if (varCheck(prNavEl)) {
        prNavEl.innerHTML = 'Page ' + (qn+1) + ' of ' + qorderarr.length;
    }
}

function printQuestion() {
    var prNavEl = document.getElementById('_prquestion');
    var qId = getPageId();
    var qn = qorderarr.indexOf(qId);
    if (varCheck(prNavEl)) {
        prNavEl.innerHTML = qquesttxt[qn];
    }
}
