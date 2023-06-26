package StaffController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Patientdao;
import dto.PatientDto;

@WebServlet("/fetchallptient")
public class FetchAllPatients extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().println("<h1 style='color:red'>Session expired Login agian</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {

			Patientdao daop = new Patientdao();
			List<PatientDto> list = daop.fetchallpatient();
			if (list.isEmpty()) {
				resp.getWriter().println("<h1 style='color:red'>No patient details found </h1>");
				req.getRequestDispatcher("BookAppointment.jsp").include(req, resp);
			} else {
				req.setAttribute("list", list);
				req.getRequestDispatcher("Patient.jsp").include(req, resp);
			}
		}
	}
}
