package StaffController;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dao.Staffsignupdao;
import dto.StaffSignupdto;

@WebServlet("/staff")
public class SatffSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		Date date = Date.valueOf(req.getParameter("Date"));
		String gender = req.getParameter("gender");
		String spec = req.getParameter("specialtization");
		long mobile = Long.parseLong(req.getParameter("mobile"));

		// int age=LocalDate.now().getYear()-date.toLocalDate().getYear();
		// this has some exception so we use Period class

		Staffsignupdao stafdao = new Staffsignupdao();
		DoctorSignupdao docdao = new DoctorSignupdao();
		int age = Period.between(date.toLocalDate(), LocalDate.now()).getYears();
		if (stafdao.fetchstaff(mobile) == null && stafdao.fetchstaff(email) == null
				&& docdao.fetchDoctor(mobile) == null && docdao.fetchDoctor(email) == null) {
			StaffSignupdto staff = new StaffSignupdto();

			staff.setDob(date);
			staff.setEmail(email);
			staff.setName(name);
			staff.setPass(pass);
			staff.setSpecialization(spec);
			staff.setMobile(mobile);
			staff.setAge(age);
			staff.setGender(gender);

			/*
			 * EntityManagerFactory
			 * emf=Persistence.createEntityManagerFactory("dev"); EntityManager
			 * em=emf.createEntityManager(); EntityTransaction
			 * et=em.getTransaction();
			 */
			// we can write here also

			stafdao.save(staff);

			resp.getWriter().print("<h1>STAFF ID IS CREATED SUCCESFULLY.wait for Admin Approval</>");
			resp.getWriter().print("<h1>YOUR STAFF ID IS:" + staff.getId() + "</>");
			req.getRequestDispatcher("Login.html").include(req, resp);

		} else {
			resp.getWriter().print("<h1>Mobile number and email is already exist</h1>");
			req.getRequestDispatcher("Staffsignup.html").include(req, resp);

		}

	}

}
