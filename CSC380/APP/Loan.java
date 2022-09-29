import java.sql.Date;

public class Loan {
	String loan, Branch, Type, CID;
	double IRate, Amount, PaidAmount;
	Date Ldate;
	Loan(String loan, String Branch, String Type, String CID, String Ldate, double IRate, double Amount, double PaidAmount){
		this.loan = loan;
		this.Branch = Branch;
		this.Type = Type;
		this.CID = CID;
		this.Ldate = Date.valueOf(Ldate);
		this.Amount = Amount;
		this.IRate = IRate;
		this.PaidAmount = PaidAmount;
	}
}
