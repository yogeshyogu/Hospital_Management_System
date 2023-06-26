package controler;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.PrimaryKey;

import dao.DoctorSignupdao;
import dao.Staffsignupdao;
import dto.DoctorSignupdto;
import dto.StaffSignupdto;

@WebServlet("/forgotpassword")
public class Forgottpassword extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
//		String name=req.getParameter("name");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		Date dob = Date.valueOf(req.getParameter("dob"));
		String password = req.getParameter("password");
		System.out.println(id);
//		System.out.println(name);
		System.out.println(mobile);
		System.out.println(dob);
		System.out.println(password);

		DoctorSignupdao ddao = new DoctorSignupdao();
		Staffsignupdao sdao = new Staffsignupdao();

		DoctorSignupdto dactor = ddao.fetchDoctorId(id);
		StaffSignupdto staff = sdao.fetchstaffid(id);

		if (dactor == null && staff == null) {
			resp.getWriter().print("<h1 style='color:re'>Invalid id </h1>");
			req.getRequestDispatcher("Forgetpassword.html").include(req, resp);

		} else {
			if (dactor != null) {
				if ( dactor.getMobile() == mobile && dactor.getDob().equals(dob)) {//dactor.getName().equals(name) &&

					dactor.setPass(password);
					ddao.updateDoctor(dactor);
					resp.getWriter().print("<h1 style='color:green'>password reset succesfull</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:red>Invalid Detail</h1>");
					req.getRequestDispatcher("ForgetPassword.html").include(req, resp);
				}
			} else {
				if ( staff.getMobile() == mobile && staff.getDob().equals(dob)) {//staff.getName().equals(name) &&
					staff.setPass(password);
					sdao.updateSatff(staff);
					resp.getWriter().print("<h1 style='colo:green'>Password reset succesfully</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:red'>Invalid Details</h1>");
					req.getRequestDispatcher("ForgetPassword.html").include(req, resp);
				}
			}
		}

	}
}
