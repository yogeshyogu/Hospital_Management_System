package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Appointmentdto;
import dto.DoctorSignupdto;

public class DoctorSignupdao {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	
	public void save(DoctorSignupdto dst) {
		et.begin();
		em.persist(dst);
		et.commit();
		
	}


	public DoctorSignupdto fetchByMobile(long mobile) {
		List<DoctorSignupdto> lst=em.createQuery("select x from DoctorSignupdto x where mobile=?1").setParameter(1, mobile).getResultList();
		if(lst.isEmpty())
		{
			return null;
		}
		else{
			return lst.get(0);
		}
	}

	public DoctorSignupdto fetchByemail(String gmail) {
		List<DoctorSignupdto> lst=em.createNativeQuery("select * from DoctorSignupdto where gmail=?1",DoctorSignupdto.class).setParameter(1,gmail).getResultList();
		if(lst.isEmpty())
		{
			return null;
		}
		else{
			return lst.get(0);
		}
		
		
	}
	
	
	
	
			
	public DoctorSignupdto fetchDoctorId(int id)
	{
		return em.find(DoctorSignupdto.class, id);
	}


	public  DoctorSignupdto fetchDoctor(long mobile) {
		List<DoctorSignupdto> lst=em.createQuery("select x from DoctorSignupdto x where mobile=?1").setParameter(1, mobile).getResultList();
	if(lst.isEmpty()){
		return null;
	}
	else{
		return lst.get(0);
	}
	}


	public DoctorSignupdto fetchDoctor(String gmail) {
		List<DoctorSignupdto>lst1=em.createNativeQuery("select * from DoctorSignupdto where gmail=?1",DoctorSignupdto.class).setParameter(1,gmail).getResultList();
	if(lst1.isEmpty()){
		return null;
		
	}
	else{
		return lst1.get(0);
	}
	
	}


	public void updateDoctor(DoctorSignupdto dactor) {
		et.begin();
		em.merge(dactor);
		et.commit();
		
		
	}


	public List<DoctorSignupdto> fetchallDoctor(){
		return em.createQuery("select x from DoctorSignupdto x ").getResultList();
	}
	public List<DoctorSignupdto> fetchAvailableDoctors()
	{
		return em.createQuery("select x from DoctorSignupdto x where available=true").getResultList();
	}
	
	public void saveAppointment(Appointmentdto appointment) {
		et.begin();
		em.persist(appointment);
		et.commit();
	}
	public void updateAppointment(Appointmentdto appointment) {
		et.begin();
	em.merge(appointment);
		et.commit();
	}

	public Appointmentdto fetchAppointment(int id) {
		return em.find(Appointmentdto.class, id);
	}

	
}
