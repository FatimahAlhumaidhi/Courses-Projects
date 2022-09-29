import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AccountsTable extends AbstractTableModel {
	
	private static final int IBAN = 0;
	private static final int Balance = 1;
	private static final int Customer_ID = 2;
	private static final int STATUS = 3;
	private static final int TYPE = 4;
	private static final int BRANCH = 5;
	
	//ACCOUNT`( `IBAN` , `Balance` , `Customer ID` , `STATUS` , `TYPE` , `BRANCH#` )
	private String[] columnNames = { "IBAN", "Balance", "Customer ID", "STATUS", "TYPE", "BRANCH#" };
	private List<Account> accounts;
	
	public AccountsTable(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int getRowCount() {
		return accounts.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account account = accounts.get(rowIndex);

		switch (columnIndex) {
		case IBAN:
			return account.IBAN;
		case Balance:
			return account.Balance;
		case Customer_ID:
			return account.CustomerID;
		case STATUS:
			return account.Status;
		case TYPE:
			return account.Type;
		case BRANCH:
			return account.Branch;
		default:
			return account.IBAN;
		}

	}
	
	@Override
	public Class getColumnClass(int c) {
		Object o = getValueAt(0, c);
        if(o==null) return Object.class;
        return o.getClass();	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
