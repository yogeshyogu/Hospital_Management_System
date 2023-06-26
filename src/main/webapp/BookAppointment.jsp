<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select one</title>
</head>
<body>
	<%
		if (session.getAttribute("staff") == null) {
			response.getWriter().print("<h1 style='color:red'>session expired Login again</h1>");
			request.getRequestDispatcher("Login.html").include(request, response);
		}else{
	%>
	<a href="fetchallptient"><button>view all Patient</button></a><br><br>
	<a href="staffHome.html"> <button>Back</button>
	 </a>
	<%} %>
</body>
</html>