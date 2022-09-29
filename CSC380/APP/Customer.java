import java.sql.Date;

public class Customer {
	String NID;
	String Name, Email, Mobile, Address;
	Date DOB;

	Customer(String nid, String name, String email, String dob, String mobile, String address){
		NID = nid;
		Name = name;
		Email = email;
		Mobile = mobile;
		Address = address;
		DOB = Date.valueOf(dob);
	}
	
	@Override
	public String toString() {
		return String.format("National ID: %s \nName: %s \nDate Of Birth: %s \nEmail: %s \nMobile: %s \nAddress: %s", NID, Name, DOB.toString(), Email, Mobile, Address);
	}
}
