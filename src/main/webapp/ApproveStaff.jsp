<%@page import="dto.StaffSignupdto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve staff</title>
</head>
<body>
	<%
		List<StaffSignupdto> list = (List<StaffSignupdto>) request.getAttribute("list");
	%>
	<table border="2px">
		<tr>
			<th>Id</th>
			<th>name</th>
			<th>mobile</th>
			<th>email</th>
			<th>DOB</th>
			<th>gender</th>
			<th>status</th>
			<th>change status</th>
		</tr>
		<% for(StaffSignupdto sdto : list) {%>
		<tr>
			<th><%=sdto.getId()%></th>
			<th><%=sdto.getName()%></th>
			<th><%=sdto.getMobile()%></th>
			<th><%=sdto.getEmail()%></th>
			<th><%=sdto.getDob()%></th>
			<th><%=sdto.getGender()%></th>
			<th><%=sdto.isStatus()%></th>
			<th><a href="changestaffstatus?id=<%=sdto.getId()%>"><button>change</button></a></th>
		</tr>
		<%
			}
		%>
	</table>
	<a href="Admin.html"><button>Back</button> </a>
	<a href="Login.html"><button>Login_page</button> </a>
</body>
</html>