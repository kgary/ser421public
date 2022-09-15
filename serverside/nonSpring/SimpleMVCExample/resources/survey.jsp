<%@ page import="edu.asupoly.ser421.simplemvcex.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey</title>
</head>
<body>
<%
String cookieName = "mvcrendering";
Integer pageNo = (Integer)session.getAttribute("pagenum");
String[] surveyAnswers = (String[])session.getAttribute("answers");
Survey survey = Survey.getSurvey();
SurveyItem item = survey.getSurveyItem(pageNo);
int previousAnswer = -1;
if (surveyAnswers != null && surveyAnswers.length >= pageNo) {
	try {
		if (surveyAnswers[pageNo-1] != null && !surveyAnswers[pageNo-1].isEmpty()) {
			previousAnswer = Integer.parseInt(surveyAnswers[pageNo-1]);
		}
	} catch (Throwable t) { // type conversion, something is scrogged
		t.printStackTrace();
	}
}
%>
<%@ include file="./getCookie.jsp" %>

<p>Survey page: <%= pageNo %> for user <%= (String)session.getAttribute("username") %><br/><br/>
<%= item.getQuestion() %>
</p>
<p>
<a href="./preferences.jsp">Set your rendering preferences</a>
</p>
<p>
<FORM ACTION="./controller" METHOD="POST">
<INPUT TYPE="hidden" NAME="action" VALUE="survey"/>
<%
String[] choices = item.getChoices();
for (int i = 0; choices != null && i < choices.length; i++) {
	// The answers array stores choices by number
	out.write("<INPUT TYPE=\"radio\" NAME=\"answer\" VALUE=\"" + i + "\"");
	if (i == previousAnswer) {
		out.write(" checked=\"yes\" ");
	}
	out.write(">" + choices[i] + "</INPUT>\n");
	if (cookieValue.equals("vertical")) {
		out.write("<br/>\n");
	}
}
if (pageNo > 1) {
%>
<INPUT TYPE="submit" NAME="submit" VALUE="previous"></INPUT>
<% }
if (pageNo <= survey.getNumPages()){ %>
<INPUT TYPE="submit" NAME="submit" VALUE="next"></INPUT>
<% } %>
</FORM>
</p>
</body>
</html>