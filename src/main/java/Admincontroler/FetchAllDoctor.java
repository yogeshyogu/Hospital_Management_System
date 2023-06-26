package Admincontroler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dto.DoctorSignupdto;

@WebServlet("/fetchallDoctor")
public class FetchAllDoctor extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DoctorSignupdao dao = new DoctorSignupdao();
		List<DoctorSignupdto> list = dao.fetchallDoctor();

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 style='color:red'>No doctor information Available</h1>");
			req.getRequestDispatcher("Admin.jsp").include(req, resp);
		}
		else {
			req.setAttribute("list", list);
			req.getRequestDispatcher("ApproveDoctor.jsp").include(req, resp);
		}
	}
	
	
	
}
