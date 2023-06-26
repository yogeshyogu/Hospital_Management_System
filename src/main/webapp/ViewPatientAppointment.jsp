<%@page import="dto.Appointmentdto"%>
<%@page import="java.util.List"%>
<%@page import="dto.PatientDto"%>
<%@page import="dao.Patientdao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% int pid=Integer.parseInt(request.getParameter("id")); 
	Patientdao dao=new Patientdao();
	PatientDto dto=dao.fetchPatient(pid);
	List<Appointmentdto> list=dto.getApointment();
	if(list.isEmpty())
	{
		response.getWriter().print("<h1>no appointments yet</h1>");
		request.setAttribute("list", dao.fetchallpatient());
		request.getRequestDispatcher("ViewPatientHistory.jsp").include(request, response);
	
	}
	else{
		%>
		<h1>Appointment Details</h1>
		<% for(Appointmentdto appointment:list){%>
		<h1>Appointment ID:<%=appointment.getId() %></h1>
		<h1>Problem:<%=appointment.getProblem() %></h1>
		<h1>Doctor:<%=appointment.getDoctordto() %></h1>
		<h1>Appointment Date:<%=appointment.getTime() %></h1>

		<%}
	}
	%>
</body>
</html>