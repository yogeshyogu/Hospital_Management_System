package StaffController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Staffsignupdao;
import dto.PatientDto;

@WebServlet("/stafffetchpatienthistory")
public class FetchPatientHistory extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			Staffsignupdao dao=new Staffsignupdao();
			
			List<PatientDto> list = dao.fetchAllPatient();
			if (list.isEmpty()) {
				resp.getWriter().print("<h1 style='color:red'>No Patient Data Found</h1>");
				req.getRequestDispatcher("StaffHome.html").include(req, resp);
			} else {
				req.setAttribute("list", list);
				req.getRequestDispatcher("ViewPatientHistory2.jsp").forward(req, resp);
			}
		}
	}

}
