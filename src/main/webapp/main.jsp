<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main page</title>
</head>
<body>
<h1>how the weather is?</h1>
<form method="get" action="${pageContext.request.contextPath}/main">
  <p>please, write the city you want to know how weather is in</p>
  <input type="text" name="city">
  <input type="submit" value="search">
</form>

<%
  String weather = (String) request.getAttribute("weather");
  if (weather != null) {

%>
<h2>here it is!</h2>
<pre><%= weather %></pre>
<%
  }
%>
</body>
</html>
