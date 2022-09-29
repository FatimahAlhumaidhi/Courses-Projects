import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CardsTable extends AbstractTableModel{
	
	private static final int CARD = 0;
	private static final int IBAN = 1;
	private static final int Expiration_DATE = 2;
	private static final int CVV = 3;
	private static final int TYPE = 4;
	
	//CARD`( `CARD#`, `IBAN` , `Expiration Date` , `CVV`, `TYPE`)
	private String[] columnNames = { "CARD#", "IBAN", "Expiration Date", "CVV", "TYPE"};
	private List<Card> cards;
	
	public CardsTable(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int getRowCount() {
		return cards.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Card card = cards.get(rowIndex);

		switch (columnIndex) {
		case CARD:
			return card.card;
		case IBAN:
			return card.IBAN;
		case Expiration_DATE:
			return card.Edate;
		case CVV:
			return card.CVV;
		case TYPE:
			return card.Type;
		default:
			return card.card;
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
