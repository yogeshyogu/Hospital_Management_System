package Admincontroler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Staffsignupdao;
import dto.StaffSignupdto;

@WebServlet("/fetchallstaff")
public class FetchAllStaff extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("admin")!=null){
		Staffsignupdao dao = new Staffsignupdao();
		List<StaffSignupdto> list = dao.fetchallstaff();

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 style='color:red'>No staff information Available</h1>");
			req.getRequestDispatcher("Admin.jsp").include(req, resp);
		}
		else {
			req.setAttribute("list", list);
			req.getRequestDispatcher("ApproveStaff.jsp").include(req, resp);
		}
	
		}
		else{
			resp.getWriter().print("<h1 style='color:red'>Session expired Login agin</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
}
}