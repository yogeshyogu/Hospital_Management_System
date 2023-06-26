package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.DoctorSignupdto;
import dto.PatientDto;
import dto.StaffSignupdto;

public class Patientdao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public void savePatient(PatientDto patient) {
		et.begin();
		em.persist(patient);
		et.commit();
	}

	public PatientDto fetchByMobile(long mobile) {
		List<PatientDto> lst = em.createQuery("select x from PatientDto x where mobile=?1").setParameter(1, mobile)
				.getResultList();
		if (lst.isEmpty()) {
			return null;
		} else {
			return lst.get(0);
		}
	}

	public void updatePatient(PatientDto patient) {
		et.begin();
		em.merge(patient);
		et.commit();
	}

	public List<PatientDto> fetchallpatient() {
		return em.createQuery("select x from PatientDto x ").getResultList();
	}

	public PatientDto fetchPatient(int id) {
		return em.find(PatientDto.class, id);
	}

}
