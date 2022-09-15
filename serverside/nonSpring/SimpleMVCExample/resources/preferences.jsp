<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SER421 MVC Example Set Preferences</title>
</head>
<body>
<% String cookieName = "mvcrendering"; %>
<%@include file="./getCookie.jsp" %>
<p>
Your current rendering preference is <em><%= cookieValue %></em>
</p>
<p>
<FORM METHOD="POST" ACTION="./controller">
<INPUT TYPE="hidden" NAME="action" VALUE="setpreferences"/>
<INPUT TYPE="radio" NAME="preference" VALUE="horizontal"></INPUT>Horizontal<br/>
<INPUT TYPE="radio" NAME="preference" VALUE="vertical"></INPUT>Vertical<br/>
<INPUT TYPE="submit" NAME="submit" VALUE="OK"/>
</FORM>
</p>
</body>
</html>