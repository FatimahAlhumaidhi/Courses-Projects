import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

public class BankAPI {
	Connection con;
	PreparedStatement insert;
	PreparedStatement delete;
	PreparedStatement update;
	PreparedStatement select;
	
	public BankAPI(){
        try {
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void AddCustomer(Customer s) throws SQLException{
		insert = con.prepareStatement("insert into `customer` values (?, ?, ?, ?, ?, ?);");
		insert.setString(1, s.NID);
		insert.setString(2, s.Name);
		insert.setDate(3, s.DOB);
		insert.setString(4, s.Email);
		insert.setString(5, s.Mobile);
		insert.setString(6, s.Address);
		insert.execute();
	}
	
	public void updateCustomer(Customer s) throws SQLException {
		update = con.prepareStatement("update `customer` set Name=?, DOB=?, Email=?, Mobile=?, Address=? where NID=?;");
		update.setString(1, s.Name);
		update.setDate(2, s.DOB);
		update.setString(3, s.Email);
		update.setString(4, s.Mobile);
		update.setString(5, s.Address);
		update.setString(6, s.NID);
		update.execute();
	}
	
	public Customer MakeObjectCustomer(ResultSet rs) throws SQLException {
		Customer c = new Customer(rs.getString("NID"), rs.getString("Name"), rs.getString("Email"), rs.getDate("DOB").toString(), rs.getString("Mobile"), rs.getString("Address"));
		return c;
	}
	
	public Customer selectCustomer(String NID) {
		Customer customer = null;
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `customer` where `NID`='" + NID + "';");
			if(result.next()) {
				customer= MakeObjectCustomer(result);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	private static Random rnd = new Random();

	public static String getRandomNumber(int digCount) {
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return sb.toString();
	}
	
	public void AddAccount(Account acc) throws SQLException {
		if(acc == null)
			return;
		String s = "SA";
		s += getRandomNumber(22);
		acc.IBAN = s;
		acc.Balance = 0;
		acc.Status = "ACTIVE";
		insert = con.prepareStatement("insert into `account` values (?, ?, ?, ?, ?, ?);");
		insert.setString(1, acc.IBAN);
		insert.setDouble(2, acc.Balance);
		insert.setString(3, acc.CustomerID);
		insert.setString(4, acc.Status);
		insert.setString(5, acc.Type);
		if(acc.Branch == null) {
			insert.setNull(6, java.sql.Types.VARCHAR);
		}else {
			insert.setString(6, acc.Branch);
		}
		insert.execute();
	}
	
	public void updateAccount(Account acc) throws SQLException {
		if(acc == null)
			return;
		update = con.prepareStatement("update `account` set `Balance`=?, `Customer ID`=?, `Status`=?, `Type`=?, `Branch#`=? where `IBAN`=?;");
		update.setDouble(1, acc.Balance);
		update.setString(2, acc.CustomerID);
		update.setString(3, acc.Status);
		update.setString(4, acc.Type);
		update.setString(5, acc.Branch);
		update.setString(6, acc.IBAN);
		update.execute();
	}
	
	public void deleteAccount(String IBAN) throws SQLException {
		if(IBAN == null)
			return;
		delete = con.prepareStatement("delete from `account` where IBAN=?;");
		delete.setString(1, IBAN);
		delete.execute();
	}
	
	public List<Account> selectCustomerAccounts(String NID) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `account` where `customer id`= '" + NID + "';");
			while(result.next()) {
				Account acc = MakeObjectAccount(result);
				accounts.add(acc);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	public Account selectCustomerAccount(String IBAN) throws SQLException {
		Account account = null;
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `account` where `IBAN`= '" + IBAN + "';");
			if(result.next()) {
				account = MakeObjectAccount(result);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public Account MakeObjectAccount(ResultSet rs) throws SQLException {
		Account acc = new Account(rs.getString("IBAN"), rs.getDouble("Balance"), rs.getString("customer id"), rs.getString("status"), rs.getString("Branch#"), rs.getString("Type"));
		return acc;
	}
	
	public void AddCard(Card c) throws SQLException {
		if(c == null)
			return;
		c.card = getRandomNumber(16);
		long ct = System.currentTimeMillis();  
        Date d = new Date(ct);  
        d.setYear(d.getYear()+5);
        c.Edate = d;
        c.CVV = getRandomNumber(3);
		insert = con.prepareStatement("insert into `card` values (?, ?, ?, ?, ?);");
		insert.setString(1, c.card);
		insert.setString(2, c.IBAN);
		insert.setDate(3, c.Edate);
		insert.setString(4, c.CVV);
		insert.setString(5, c.Type);
		insert.execute();
	}
	
	public void deleteCard(String card) throws SQLException {
		if(card == null)
			return;
		delete = con.prepareStatement("delete from `card` where `card#`=?;");
		delete.setString(1, card);
		delete.execute();
	}
	
	public List<Card> selectCustomerCards(String NID) throws SQLException {
		List<Card> card = new ArrayList<Card>();
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `card` where `IBAN` in (select `IBAN` from `account` where `customer id`= '" + NID + "');");
			while(result.next()) {
				Card c = MakeObjectCard(result);
				card.add(c);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public Card MakeObjectCard(ResultSet rs) throws SQLException {
		Card c = new Card(rs.getString("card#"), rs.getString("IBAN"), rs.getString("CVV"), rs.getString("Type"), rs.getString("Expiration Date"));
		return c;
	}
	
	public void AddTransaction(Transaction t) throws SQLException {
		t.timestamp = Timestamp.from(Instant.now());
		insert = con.prepareStatement("insert into `Transaction` values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		insert.setString(1, t.Type);
		insert.setTimestamp(2, t.timestamp);
		insert.setString(3, t.Currency);
		insert.setDouble(4, t.Amount);
		if(t.Branch == null) {
			insert.setNull(5, java.sql.Types.VARCHAR);
		}else {
		insert.setString(5, t.Branch);
		}
		
		insert.setString(6, t.SenderIBAN);
		
		if(t.Card == null) {
			insert.setNull(7, java.sql.Types.VARCHAR);
		}else {
			insert.setString(7, t.Card);
		}
		if(t.ReceiverIBAN == null) {
			insert.setNull(8, java.sql.Types.VARCHAR);
		}else {
			insert.setString(8, t.ReceiverIBAN);
		}
			insert.setString(9, t.Comments);
		insert.execute();
	}
	
	public List<Transaction> selectCustomerTransactions(String NID) throws SQLException {
		List<Transaction> transaction = new ArrayList<Transaction>();
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `transaction` where `Sender IBAN` in (select `IBAN` from `account` where `customer id`= '" + NID + "');");
			while(result.next()) {
				Transaction t = MakeObjectTransaction(result);
				transaction.add(t);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return transaction;
	}
	
	public Transaction MakeObjectTransaction(ResultSet rs) throws SQLException {
		Transaction t = new Transaction(rs.getString("Reference#"), rs.getString("Type"), rs.getString("Currency"), rs.getString("Branch#"), rs.getString("Sender IBAN"), rs.getString("Card#"), rs.getString("Receiver IBAN"), rs.getString("Comments"), rs.getDouble("Amount"), rs.getTimestamp("Time Stamp"));
		return t;
	}
	
	public Loan MakeObjectLoan(ResultSet rs) throws SQLException {
		Loan l = new Loan(rs.getString("Loan#"), rs.getString("Branch#"), rs.getString("Type"), rs.getString("customer id"), rs.getDate("date").toString(), rs.getDouble("interest rate"), rs.getDouble("amount"), rs.getDouble("paid amount") );
		return l;
	}
	
	public List<Loan> selectCustomerLoans(String NID) throws SQLException{
		List<Loan> loans = new ArrayList<Loan>();
		Statement query;
		ResultSet result;
		
		try {
			query = con.createStatement();
			result = query.executeQuery("select * from `loan` where `customer id`='" + NID + "';");
			while(result.next()) {
				Loan l = MakeObjectLoan(result);
				loans.add(l);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return loans;
	}

}
