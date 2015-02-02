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
	+'?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.login'
	+'&state=<%  out.print(request.getSession().getId()); session.setAttribute("state", session.getId()); %>'
	+'&redirect_uri=http://nomster-backend.appspot.com/oauth2callback'
	+'&response_type=code'
	+'&client_id=453077549340-0mbc5et33d267m2eg292sv19362ohosa.apps.googleusercontent.com'
	+'&access_type=offline';
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