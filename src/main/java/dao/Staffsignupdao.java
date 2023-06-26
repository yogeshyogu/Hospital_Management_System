package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.PatientDto;
import dto.StaffSignupdto;

public class Staffsignupdao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public void save(StaffSignupdto staff) {
		et.begin();
		em.persist(staff);
		et.commit();

	}

	public StaffSignupdto fetchstaff(long mobile) {
		 List<StaffSignupdto> lst=em.createNativeQuery("select * from Staffsignupdto where mobile=?1",StaffSignupdto.class).setParameter(1, mobile).getResultList();
		 if(lst.isEmpty())
		 {
			 return null;
		 }
		 else{
			 return lst.get(0);
		 }
	}

	public StaffSignupdto fetchstaff(String email) {
		 List<StaffSignupdto> lst=em.createNativeQuery("select * from Staffsignupdto where email=?1", StaffSignupdto.class).setParameter(1, email).getResultList(); 
		if(lst.isEmpty())
		 {
			 return null;
		 }
		 else{
			 return lst.get(0);
		 }	
	}
	
	
	public StaffSignupdto fetchstaffid(int id){
			return em.find(StaffSignupdto.class, id);
			
	}

	public void updateSatff(StaffSignupdto staff) {
		et.begin();
		em.merge(staff);
		et.commit();
	}

	public List<StaffSignupdto> fetchallstaff(){
		return em.createQuery("select x from StaffSignupdto x").getResultList();
	}
	public List<PatientDto> fetchAllPatient() {
		return em.createQuery("select x from PatientDto x").getResultList();
	}
	
	
	
}
