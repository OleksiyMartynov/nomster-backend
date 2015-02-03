<%@page import="nomster.MyConstants"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%!
public String getStateString()
{
	return new BigInteger(130, new SecureRandom()).toString(32);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function getStateValue()
{
	return Math.random();
}
function getGoogleUrl()
{
	return 'https://accounts.google.com/o/oauth2/auth'
	+'?scope=openid%20email'
	+'&state=<%  out.print(request.getSession().getId()); session.setAttribute("state", session.getId()); %>'
	+'&redirect_uri=<%=MyConstants.REDIRECT_URI%>'
	+'&response_type=<%=MyConstants.RESPONSE_TYPE%>'
	+'&client_id=<%=MyConstants.CLIENT_ID%>'
	+'&access_type=<%=MyConstants.ACCESS_TYPE%>';
}
function main()
{
	document.getElementById("myButton").href = getGoogleUrl();
}
</script>
<title>Insert title here</title>
</head>
<body onload="main();">
<a id="myButton" href=>Sign in</a>
</body>
</html>