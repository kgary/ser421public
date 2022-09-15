<%
String cookieValue = "";

Cookie[] c = request.getCookies();
if (c != null) {
	for (Cookie i : c) {
		if (i.getName().equals(cookieName)) {
			cookieValue = i.getValue();
		}
	}
}
%>