import java.sql.Date;

public class Card {
	String card, IBAN, CVV, Type;
	Date Edate;
	
	Card(String IBAN, String Type){
		this.IBAN = IBAN;
		this.Type = Type;
	}
	Card(String card, String IBAN, String CVV, String Type, String Edate){
		this.card = card;
		this.IBAN = IBAN;
		this.CVV = CVV;
		this.Type = Type;
		this.Edate = Date.valueOf(Edate);
	}
}
