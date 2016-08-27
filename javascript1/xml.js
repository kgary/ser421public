text = "<bookstore><book>" +
"<title>Everyday Italian</title>" +
"<author>Giada De Laurentiis</author>" +
"<year>2005</year>" +
"<title>Everyday Latin</title>" +
"<author>Socrates</author>" +
"<year>300 B.C.</year>" +
    "</book></bookstore>";

parser = new DOMParser();
xmlDoc = parser.parseFromString(text,"text/xml");
xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue;
xmlDoc.getElementsByTagName("title")[1].childNodes[0].nodeValue;