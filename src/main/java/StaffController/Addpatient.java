package StaffController;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Patientdao;
import dto.PatientDto;

@WebServlet("/addpatient")
@MultipartConfig
public class Addpatient extends HttpServlet {
	
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name = req.getParameter("name");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		Date Dob = Date.valueOf(req.getParameter("dob"));
		int age = Period.between(Dob.toLocalDate(), LocalDate.now()).getYears();
		Part picture = req.getPart("picture");
		byte[] image = new byte[picture.getInputStream().available()];
		picture.getInputStream().read(image);

		Patientdao dao = new Patientdao();

		PatientDto patient1 = dao.fetchByMobile(mobile);

		if (patient1 == null) {

			PatientDto patient = new PatientDto();
			patient.setName(name);
			patient.setAge(age);
			patient.setDob(Dob);
			patient.setMobile(mobile);
			patient.setPicture(image);

			dao.savePatient(patient);

			resp.getWriter().print("<h1> Patient added succesfully</h1>");
			req.getRequestDispatcher("StaffHome.html").include(req, resp);
		}

		else {
			resp.getWriter().print("<h1>mobile alredy exist.try different number</h1>");
			req.getRequestDispatcher("AddPatient.html").include(req, resp);
		}
	}
}
