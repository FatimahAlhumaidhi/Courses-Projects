import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TransactionsTable extends AbstractTableModel {
	
	private static final int REFERENCE = 0;
	private static final int TYPE = 1;
	private static final int TIMESTAMP = 2;
	private static final int Currency = 3;
	private static final int Amount = 4;
	private static final int BRANCH = 5;
	private static final int SenderIBAN = 6;
	private static final int CARD = 7;
	private static final int ReceiverIBAN = 8;
	private static final int COMMENTS = 9;
	
	//TRANSACTION`(`Reference#`, `TYPE`, `TIME STAMP`,`Currency`,`Amount`, `BRANCH#` , `Sender IBAN` , `CARD#` ,`Receiver IBAN`,`COMMENTS`)
	private String[] columnNames = { "Reference#", "TYPE", "TIMESTAMP", "Currency", "Amount", "BRANCH#", "Sender IBAN" , "CARD#" ,"Receiver IBAN", "COMMENTS" };
	private List<Transaction> transactions;
	
	public TransactionsTable(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int getRowCount() {
		return transactions.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Transaction transaction = transactions.get(rowIndex);

		switch (columnIndex) {
		case REFERENCE:
			return transaction.Reference;
		case TYPE:
			return transaction.Type;
		case TIMESTAMP:
			return transaction.timestamp;
		case Currency:
			return transaction.Currency;
		case Amount:
			return transaction.Amount;
		case BRANCH:
			return transaction.Branch;
		case SenderIBAN:
			return transaction.SenderIBAN;
		case CARD:
			return transaction.Card;
		case ReceiverIBAN:
			return transaction.ReceiverIBAN;
		case COMMENTS:
			return transaction.Comments;
		default:
			return transaction.Reference;
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		Object o = getValueAt(0, c);
        if(o==null) return Object.class;
        return o.getClass();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
