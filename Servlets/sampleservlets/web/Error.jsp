<%@ page language="java" %>
<html>
<head><title>Error!!!</title></head>
<body>
        <H4>
        An error occurred in processing:
        <%= request.getAttribute("errorMsg") %>
        </H4>
</body>
</html>
