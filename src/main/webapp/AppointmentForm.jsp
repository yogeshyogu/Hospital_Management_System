
<%@page import="dao.DoctorSignupdao"%>
<%@page import="java.util.List"%>
<%@page import="dto.DoctorSignupdto"%>
<%@page import="dto.PatientDto"%>
<%@page import="dao.Patientdao"%>
<%@page import="dto.StaffSignupdto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointment Form</title>
</head>
<body>
	<%
		StaffSignupdto staff = (StaffSignupdto) session.getAttribute("staff");

		int pid = Integer.parseInt(request.getParameter("id"));
		Patientdao dao = new Patientdao();
		PatientDto patient = dao.fetchPatient(pid);
		
		if (patient == null) {

			response.getWriter().print("<h1 style='color:red'>Enter Proper Id</h1>");
			request.getRequestDispatcher("FetchById.html").include(request, response);
		} else {
			DoctorSignupdao doctordao = new DoctorSignupdao();
			List<DoctorSignupdto> list = doctordao.fetchAvailableDoctors();
			System.out.print(list);

			if (list.isEmpty()) {
				response.getWriter().print("<h1 style='color:red'>No doctors available</h1>");
				request.getRequestDispatcher("StaffHome.html").include(request, response);
			} else {
	%>
	<form action="bookappointment" method="post">
		Patient Id: <input type="text" name="pid" value="<%=pid%>" readonly="readonly"> <br> 
		Patient Name:<input type="text" name="pname" value="<%=patient.getName()%>" readonly="readonly"> <br> 
		Staff Name:<input type="text" name="staffname" value="<%=staff.getName()%>" readonly="readonly"><br>
		Problem:<input type="text" name="problem"> <br> 
		SelectDoctor: <select name="doctor">
			<%
				for (DoctorSignupdto doctor : list) {
			%>
			<option value="<%=doctor.getId()%>"><%=doctor.getName()%>(<%=doctor.getSpecialization()%>)
				<%
				}
			%>
			
		</select> <br> <br>
		<button>Fix Appointment</button>
		<button type="reset">Cancel</button>
	</form>
	<%
		}
		}
	%>
</body>

</html>