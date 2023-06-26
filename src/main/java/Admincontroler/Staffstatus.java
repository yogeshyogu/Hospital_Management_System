package Admincontroler;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Staffsignupdao;
import dto.StaffSignupdto;

@WebServlet("/changestaffstatus")
public class Staffstatus extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		
		Staffsignupdao staffdao=new Staffsignupdao();
		
		StaffSignupdto staffdto=staffdao.fetchstaffid(id);
			
		if(staffdto.isStatus())
		{
			staffdto.setStatus(false);
		}
		else{
			staffdto.setStatus(true);
		}
		staffdao.updateSatff(staffdto);
		resp.getWriter().print("<h1 style='colo:green'>Update succesful</h>");
		req.setAttribute("list", staffdao.fetchallstaff());
		req.getRequestDispatcher("ApproveStaff.jsp").include(req, resp);
	}
}
