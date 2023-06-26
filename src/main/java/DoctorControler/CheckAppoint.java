package DoctorControler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dto.Appointmentdto;

@WebServlet("/checkappointment")
public class CheckAppoint extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		DoctorSignupdao dao=new DoctorSignupdao();

		Appointmentdto appointment = dao.fetchAppointment(id);
		appointment.setTime(LocalDateTime.now());
		dao.updateAppointment(appointment);

		resp.getWriter().print("<h1>Successfully Updated</h1>");
		req.getRequestDispatcher("DoctorHome.html").include(req, resp);
	}

}
