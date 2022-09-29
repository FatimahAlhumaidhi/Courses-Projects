import java.sql.Timestamp;

public class Transaction {
	String Reference, Type, Currency, Branch, SenderIBAN, Card, ReceiverIBAN, Comments;
	Timestamp timestamp;
	double Amount;
	
	Transaction(String Type, String Currency, String Branch, String SenderIBAN, String Card, String ReceiverIBAN, String Comments, double Amount){
		this.Type = Type;
		this.Currency = Currency;
		this.Branch = Branch;
		this.SenderIBAN = SenderIBAN;
		this.Card = Card;
		this.ReceiverIBAN = ReceiverIBAN; 
		this.Comments = Comments;
		this.Amount = Amount;
	}
	
	Transaction(String Reference, String Type, String Currency, String Branch, String SenderIBAN, String Card, String ReceiverIBAN, String Comments, double Amount, Timestamp timestamp){
		this.Reference = Reference;
		this.Type = Type;
		this.Currency = Currency;
		this.Branch = Branch;
		this.SenderIBAN = SenderIBAN;
		this.Card = Card;
		this.ReceiverIBAN = ReceiverIBAN; 
		this.Comments = Comments;
		this.Amount = Amount;
		this.timestamp = timestamp;
	}
	
}
