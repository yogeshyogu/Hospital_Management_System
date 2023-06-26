package StaffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorSignupdao;
import dao.Patientdao;
import dto.Appointmentdto;
import dto.DoctorSignupdto;
import dto.PatientDto;

@WebServlet("/bookappointment")
public class BookAppointment extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pid=Integer.parseInt(req.getParameter("pid"));
//		System.out.println(pid);
		
		int did=Integer.parseInt(req.getParameter("doctor"));
//		System.out.println(did);
		
		String problem=req.getParameter("problem");
//	System.out.println(problem);
		
		DoctorSignupdao ddao=new DoctorSignupdao();
		
		Patientdao pdao=new Patientdao();
		
		DoctorSignupdto ddto=ddao.fetchDoctorId(did);
//		System.out.println(ddto);
		
		PatientDto pdto=pdao.fetchPatient(pid);
//		System.out.println(pdto);
		
		Appointmentdto appointment=new Appointmentdto();
		appointment.setDoctordto(ddto);
		appointment.setPatient(pdto);
		appointment.setProblem(problem);
		
		
		List<Appointmentdto> list1=pdto.getApointment();
		if(list1==null)
		{
			list1=new ArrayList<Appointmentdto>(); 
		}
		list1.add(appointment);
		
		pdto.setApointment(list1);
		
		List<Appointmentdto>list2=ddto.getAppointmets();
		if(list2==null){
			list2=new ArrayList<Appointmentdto>();
		}
		list2.add(appointment);
		ddto.setAppointmets(list2);
		
		ddao.saveAppointment(appointment);
		ddao.updateDoctor(ddto);
		pdao.updatePatient(pdto);
		
		resp.getWriter().print("<h1 style='color:green'>Appointment of "+pdto.getName()+" is booked with "+ddto.getName()+" For "+problem+"</h1>");
		req.getRequestDispatcher("StaffHome.html").include(req, resp);
	}

}
