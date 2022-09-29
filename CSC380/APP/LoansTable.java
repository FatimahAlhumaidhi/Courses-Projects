import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LoansTable extends AbstractTableModel {
	
	private static final int LOAN = 0;
	private static final int BRANCH = 1;
	private static final int TYPE = 2;
	private static final int IR = 3;
	private static final int DATE = 4;
	private static final int CustomerID = 5;
	private static final int Amount = 6;
	private static final int PaidAmount = 7;
	
	//`LOAN`(`LOAN#` ,`BRANCH#` ,`TYPE` , `Interest Rate` , `DATE`, `Customer ID` , `Amount` , `Paid Amount` ) 
	private String[] columnNames = { "LOAN#", "BRANCH#", "TYPE", "Interest Rate", "DATE", "Customer ID", "Amount" , "Paid Amount" };
	private List<Loan> loans;
	
	public LoansTable(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public int getRowCount() {
		return loans.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Loan loan = loans.get(rowIndex);

		switch (columnIndex) {
		case LOAN:
			return loan.loan;
		case BRANCH:
			return loan.Branch;
		case TYPE:
			return loan.Type;
		case IR:
			return loan.IRate;
		case DATE:
			return loan.Ldate;
		case CustomerID:
			return loan.CID;
		case Amount:
			return loan.Amount;
		case PaidAmount:
			return loan.PaidAmount;
		default:
			return loan.loan;
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
