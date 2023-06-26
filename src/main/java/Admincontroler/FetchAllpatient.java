package Admincontroler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Patientdao;
import dto.PatientDto;
@WebServlet("/adminfetchallpatient")
public class FetchAllpatient extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			if(req.getSession().getAttribute("admin")==null){
				resp.getWriter().print("<h1 style='color:red'>Session expired </h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
			else{
				Patientdao dao=new Patientdao();
				List<PatientDto> list= dao.fetchallpatient();
				if(list.isEmpty())
				{
					resp.getWriter().print("<h1 style='color:red'>no patient data found</h1>");
					req.getRequestDispatcher("AdminHome.html").include(req, resp);	
				}else{
					req.setAttribute("list", list);
					req.getRequestDispatcher("viewPatientHistory.jsp").forward(req, resp);
				}
				
			}
		}

}
