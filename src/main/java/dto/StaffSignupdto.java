package dto;

import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class StaffSignupdto {

	@Id
	@GeneratedValue(generator="satffid"	)
	@SequenceGenerator(initialValue=101,allocationSize=1,name="satffid",sequenceName="satff")
	private int id;
	private String name;
	private String email;
	private String pass;
	private Date dob;
	private String specialization;
	private boolean status;
	private String gender;
	private int age;
	private long mobile;
	
}
