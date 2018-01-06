<%-- 
    Document   : home
    Created on : Dec 7, 2017, 12:24:12 PM
    Author     : oskar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
	<%
		Cookie[] cks = request.getCookies();
		if (cks != null) {
			for (int i = 0; i < cks.length; i++) {
				String name = cks[i].getName();
				String value = cks[i].getValue();
				if (name.equals("auth")) {
					break; // exit the loop and continue the page
				}
				if (i == (cks.length - 1)) // if all cookie are not valid redirect to error page
				{
					response.sendRedirect("sessionExpired.html");
					return; // to stop further execution
				}
				i++;
			}
		} else {
			response.sendRedirect("sessionExpired.html");
			return; // to stop further execution
		}
	%>
	<h3>You had successfully logged in.</h3>
	<br> your session is set to expire in 10min
	<br> try reloading after 10 min
	<br>
	<form action="Logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>
