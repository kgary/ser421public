<%@ page import="java.util.ArrayList,edu.asupoly.ser421.simplemvcex.MatchBean" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SER421 MVC Ex Survey Matches</title>
</head>
<body>
<%
ArrayList<MatchBean> matches = (ArrayList<MatchBean>)request.getAttribute("matches");
MatchBean matchBean;
String username = request.getParameter("username");
if (username == null) {
	out.write("<br/><em>Lost track of who I was matching for!!!</em></br/>");
}
%>
<p>
Best survey matches for <%= username %> best-to-worst:
</p>
<p>
<%
if (matches == null || matches.size() == 0) {
%>
	There were no matches for <%= username %>.
<%	
} else {
%>
<OL>
<%
	for (int i = 0; i < matches.size(); i++) {
		matchBean = matches.get(i);
%>
		<LI>User: <%= matchBean.getUsername() %> matched <%= matchBean.getMatchCount() %> answers</LI>
<%	
	}
%>
</OL>
<%
}
%>
</p>
</body>
</html>