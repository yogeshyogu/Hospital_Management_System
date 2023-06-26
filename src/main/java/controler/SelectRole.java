package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/go")
public class SelectRole extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String role=req.getParameter("Doctor");
		if(role.equals("d")){
			req.getRequestDispatcher("DoctorSignup.html").forward(req, resp);
		}
		else if(role.equals("f"))
		{
			req.getRequestDispatcher("Staffsignup.html").forward(req,resp);;
		}
	}
}
