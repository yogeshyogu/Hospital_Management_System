package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dao.Staffsignupdao;
import dto.DoctorSignupdto;
import dto.StaffSignupdto;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id=Integer.parseInt(req.getParameter("id"));
		
		String pass=req.getParameter("password");
		
		 Staffsignupdao staf=new Staffsignupdao();
			
		 StaffSignupdto stafdto=staf.fetchstaffid(id);
			
			DoctorSignupdao doctdao=new DoctorSignupdao();
			
			DoctorSignupdto dodto=doctdao.fetchDoctorId(id);
			
			if(stafdto==null && dodto==null && id!=999999)
			{
				resp.getWriter().print("<h1>Incorrect Id</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}else{
				if(stafdto!=null){
					
					if(stafdto.getPass().equals(pass))
					{ 
						if(stafdto.isStatus()){
							
							req.getSession().setAttribute("staff", stafdto);
							
						resp.getWriter().print("<h1>Login sucsesfull</h1>");
						req.getRequestDispatcher("StaffHome.html").include(req, resp);
						}
						else{
							
							resp.getWriter().print("<h1>wait for admin approvel</h1>");
							req.getRequestDispatcher("Login.html").include(req, resp);
					}
					}
						
					else{
						resp.getWriter().print("<h1 style='color:red'>Incorrect password</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);
					}
				}
				if(dodto!=null)
				{
					if(dodto.getPass().equals(pass)){
						if(dodto.isStatus()){
							req.getSession().setAttribute("doctor", dodto);
						resp.getWriter().print("<h1>Login succesfull</h1>");
						req.getRequestDispatcher("DoctorHome.html").include(req, resp);
						}
						
						else{
							resp.getWriter().print("<h1>wait for admin approvel</h1>");
							req.getRequestDispatcher("Login.html").include(req, resp);
						 }
						}
						
					else{
						resp.getWriter().print("<h1>Incorrect password</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);
					}
				}
				if(id==999999)
				{
					if("999999".equals(pass)){
						req.getSession().setAttribute("admin", "admin");
						resp.getWriter().print("<h1 style='color:green'>Login succesfull</h1>");
						req.getRequestDispatcher("Admin.html").include(req, resp);
					}
					else
					{
						resp.getWriter().print("<h1 style='color:red'>Incorrect password</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);
					}
				}
			}
	    }	
	} 


