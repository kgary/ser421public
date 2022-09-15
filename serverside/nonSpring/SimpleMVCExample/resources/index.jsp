<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MVC Ex Solution Landing Page</title>
</head>
<body>
<% String cookieName = "mvcname"; %>
<%@include file="./getCookie.jsp" %>
<p>
<em>SER421 MVC Ex Landing Page</em>
</p>
<p>
<FORM ACTION="./controller" METHOD="POST">
Username: <INPUT TYPE="text" NAME="username" SIZE="10" VALUE="<%= cookieValue %>"/><br/>
<INPUT TYPE="submit" NAME="action" value="survey"/>
<INPUT TYPE="submit" NAME="action" value="match"/>
</FORM>
</p>
</body>
</html>