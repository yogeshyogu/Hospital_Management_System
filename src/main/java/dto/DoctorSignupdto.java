package dto;





import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class DoctorSignupdto {

	@Id
	@GeneratedValue(generator="doctorid")
	@SequenceGenerator(initialValue=5001,allocationSize=1,name="doctorid",sequenceName="doctorid")
	private int id;
	private String name;
	private	String gmail;
	private String pass;
	private Date dob;
	private int age;
	private boolean status;
	private String gender;
	private String specialization;
	private long mobile;
	private boolean available;
		
	@OneToMany
		List<Appointmentdto>appointmets;
	
  }
