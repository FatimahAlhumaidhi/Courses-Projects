public class Account {
	String IBAN;
	String CustomerID, Status, Type, Branch;
	double Balance;
	
	Account(String CustomerID, String Branch, String Type){
		this.CustomerID = CustomerID;
		this.Branch = Branch;
		this.Type = Type;
	}
	
	Account(String IBAN, double Balance, String CustomerID, String Status, String Branch, String Type){
		this.IBAN = IBAN;
		this.Balance = Balance;
		this.CustomerID = CustomerID;
		this.Status = Status;
		this.Branch = Branch;
		this.Type = Type;
	}
}
