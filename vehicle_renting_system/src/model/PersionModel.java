package model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import person.core.*;

public class PersionModel  extends AbstractTableModel{
	
	public static final int OBJECT_COL = -1;
	private static final int REG_NO_COL = 0;
	private static final int USER_NAME_COL = 1;
	private static final int PWD_COL = 2;
	private static final int FNAME_COL = 3;
	private static final int LNAME_COL = 4;
	private static final int MAIL_COL = 5;
	private static final int NIC_NO_COL = 6;
	private static final int ADDRESS_COL = 7;
	private static final int STATU_COL = 8;
	
	
	private String[] columnNamePersion = {"Reg No","User Name", "Password", "First Name","Last Name" ,"Email", "NIC No",
			"Address" ,"A/D"};
	
	private List<Persion> persions;
	
	public PersionModel(List<Persion> thePersion) {
		persions = thePersion;
	}
	
	@Override
	public int getColumnCount() {
		return columnNamePersion.length;
	}

	@Override
	public int getRowCount() {
		return persions.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNamePersion[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		Persion tempPersion = persions.get(row);

		switch (col) {
		case REG_NO_COL:
			return tempPersion.getRegNo();
		case USER_NAME_COL:
			return tempPersion.getUseName();
		case PWD_COL:
			return tempPersion.getPwd();
		case FNAME_COL:
			return tempPersion.getfName();
		case LNAME_COL:
			return tempPersion.getlName();
		case MAIL_COL:
			return tempPersion.getMail();
		case NIC_NO_COL:
			return tempPersion.getNic();
		case ADDRESS_COL:
			return tempPersion.getAddess();
		case STATU_COL:
			return tempPersion.getStatu();
		case OBJECT_COL:
			return tempPersion;
		default:
			return tempPersion.getfName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
