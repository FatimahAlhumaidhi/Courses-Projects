import java.sql.Date;

public class Employee {
	String Name, NID, Mobile, Email, Insurance, JobTitle, Address, Branch, Department;
	char Sex;
	double Salary;
	Date DOB;
	Employee(String nid, String name, String email, String dob, String mobile, String address, char sex, double salary, String department, String branch, String job, String insurance){
		NID = nid;
		Name = name;
		Email = email;
		Mobile = mobile;
		Address = address;
		DOB = Date.valueOf(dob);
		Insurance = insurance;
		JobTitle = job;
		Branch = branch;
		Department = department;
		Sex = sex;
		Salary = salary;
	}
}
