package model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import person.core.*;

public class DriverModel extends AbstractTableModel{
	
	public static final int OBJECT_COL = -1;
	private static final int REG_NO_COL = 0;
	private static final int USER_NAME_COL = 1;
	private static final int PWD_COL = 2;
	private static final int FNAME_COL = 3;
	private static final int LNAME_COL = 4;
	private static final int NIC_NO_COL = 5;
	private static final int LICENSE_ID_COL = 6;
	private static final int INSURANCE_ID_COL = 7;
	private static final int MAIL_COL = 8; 
	private static final int ADDRESS_COL = 9;
	private static final int MONTHY_PAY_COL = 10;
	private static final int STATU_COL = 11;
	
	private String[] columnNameDriver = {"Reg No","User Name", "Password", "First Name","Last Name","NIC No","License ID","Insurance ID",
			"Email", "Address", "Payment Per Day", "A/D"} ;
	
	private List<Drivers> drivers;
	
	public DriverModel(List<Drivers> theDriver) {
		drivers = theDriver;
	}
	
	@Override
	public int getColumnCount() {
		return columnNameDriver.length;
	}

	@Override
	public int getRowCount() {
		return drivers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNameDriver[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		Drivers tempDriver = drivers.get(row);

		switch (col) {
		case REG_NO_COL:
			return tempDriver.getRegNo();
		case USER_NAME_COL:
			return tempDriver.getUseName();
		case PWD_COL:
			return tempDriver.getPwd();
		case FNAME_COL:
			return tempDriver.getfName();
		case LNAME_COL:
			return tempDriver.getlName();
		case NIC_NO_COL:
			return tempDriver.getNic();
		case LICENSE_ID_COL:
			return tempDriver.getLicId();
		case INSURANCE_ID_COL:
			return tempDriver.getInsId();
		case MAIL_COL:
			return tempDriver.getMail();
		case ADDRESS_COL:
			return tempDriver.getAddess();
		case MONTHY_PAY_COL:
			return tempDriver.getDpay();
		case STATU_COL:
			return tempDriver.getStatu();
		case OBJECT_COL:
			return tempDriver;
		default:
			return tempDriver.getfName();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
