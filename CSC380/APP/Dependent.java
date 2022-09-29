import java.sql.Date;

public class Dependent {
	String name, EID, Relationship;
	char sex;
	Date DOB;
	Dependent(String name, String EID, String Relationship, char sex, String DOB){
		this.name = name;
		this.EID = EID;
		this.Relationship = Relationship;
		this.sex = sex;
		this.DOB = Date.valueOf(DOB);
	}
}
