package Admincontroler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dto.DoctorSignupdto;

@WebServlet("/ChangeDoctorStatus")
public class DoctorStatus extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		DoctorSignupdao dactor = new DoctorSignupdao();
		DoctorSignupdto docdto = dactor.fetchDoctorId(id);
		if (docdto.isStatus()) {
			docdto.setStatus(false);
		} else {
			docdto.setStatus(true);
			
			dactor.updateDoctor(docdto);
			
			resp.getWriter().println("<h1 style='color:green'>Updated succesfully");
			req.setAttribute("list", dactor.fetchallDoctor());
			req.getRequestDispatcher("ApproveDoctor.jsp").include(req, resp);
		}
	}
	

}
