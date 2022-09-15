<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
<%
response.setHeader("refresh", "20;url=./index.jsp");

String message = (String)request.getAttribute("mvcmessage");
if (message != null && !message.isEmpty()) {
	out.write("<p><b>" + message + "</b></p>");
}
%>
</p>
<p>
You are being redirected back to the <a href="./index.jsp">homepage</a>...
</p>
</body>
</html>