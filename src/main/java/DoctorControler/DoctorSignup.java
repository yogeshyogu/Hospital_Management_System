package DoctorControler;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dto.DoctorSignupdto;

@WebServlet("/doctor")
public class DoctorSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DoctorSignupdto dst = new DoctorSignupdto();
		DoctorSignupdao dsa = new DoctorSignupdao();

		String email = req.getParameter("email");
		long mobile = Long.parseLong(req.getParameter("mobile"));

		if (dsa.fetchByMobile(mobile) == null && dsa.fetchByemail(email) == null) {

			dst.setGmail(email);
			dst.setMobile(mobile);
			Date date = Date.valueOf(req.getParameter("dob"));
			dst.setName(req.getParameter("name"));
			dst.setPass(req.getParameter("pass"));
			dst.setDob(date);// conversion of string to date

			dst.setSpecialization(req.getParameter("speacialization"));
			dst.setGender(req.getParameter("gender"));

			int age = Period.between(date.toLocalDate(), LocalDate.now()).getYears();
				dst.setAge(age);
			dsa.save(dst);

			resp.getWriter().print("<h1>DOCTOR ID IS CREATED SUCCESFULLY.Wait for admin approvel</>");
			resp.getWriter().print("<h1>YOUR Doctor ID IS:" + dst.getId() + "</>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1>Mobile number or email is already exist</>");
			req.getRequestDispatcher("DoctorSignup.html").include(req, resp);
		}
	}

}
