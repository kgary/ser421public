<html>
<head><title>Date as JSP</title></head>
<body>

<%@ page language="java" import="java.util.*, java.text.*" %>

	<%
		SimpleDateFormat dateFormat =
				new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
		Date currentDate = new Date();
	%>
	<H2>Current time is:</H2>
	<%= dateFormat.format(currentDate) %>
	<H2>From JSP</H2>

</body>
</html>
