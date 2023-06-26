package DoctorControler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dto.DoctorSignupdto;

@WebServlet("/changedoctoravailable")
public class ChangeDoctorAvailable extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DoctorSignupdto doctor= (DoctorSignupdto)req.getSession().getAttribute("doctor") ;
		DoctorSignupdao dao=new DoctorSignupdao();
//		System.out.println(doctor+"this is fetched session object");
		
		if(doctor==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else{
			if(doctor.isAvailable()){
				doctor.setAvailable(false);
				dao.updateDoctor(doctor);
				req.getSession().setAttribute("doctor", doctor);
				resp.getWriter().print("<h1 style='pading-left:20px;position:absalutre:left:10px;'>changed to not available</h1>");
				req.getRequestDispatcher("DoctorHome.html").include(req, resp);
			}
			else{
				doctor.setAvailable(true);
				dao.updateDoctor(doctor);
				req.getSession().setAttribute("doctor", doctor);
				resp.getWriter().print("<h1 style='pading-left:20px;position:absalutre:left:10px;'>Changed to Available</h1>");
				req.getRequestDispatcher("DoctorHome.html").include(req, resp);
			}
		}
	}

}
