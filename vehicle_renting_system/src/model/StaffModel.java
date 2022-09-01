package model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import person.core.*;

public class StaffModel extends AbstractTableModel{
	
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
	private static final int JTITLE_COL = 9;
	private static final int DPT_COL = 10;
	private static final int PAY_H = 11;
	
	private String[] columnNameStaff = {"Reg No", "User Name", "Password", "First Name","Last Name", 
			"Email", "NIC No","Address" ,"A/D", "Posetion", "Department", "Payment Per H"};
	
	private List<Staff> staffs;
	
	public StaffModel (List <Staff> theStaff) {
		staffs = theStaff;
	}
	
	@Override
	public int getColumnCount() {
		return columnNameStaff.length;
	}

	@Override
	public int getRowCount() {
		return staffs.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNameStaff[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		Staff tempStaff = staffs.get(row);

		switch (col) {
		case REG_NO_COL:
			return tempStaff.getRegNo();
		case USER_NAME_COL:
			return tempStaff.getUseName();
		case PWD_COL:
			return tempStaff.getPwd();
		case FNAME_COL:
			return tempStaff.getfName();
		case LNAME_COL:
			return tempStaff.getlName();
		case MAIL_COL:
			return tempStaff.getMail();
		case NIC_NO_COL:
			return tempStaff.getNic();
		case ADDRESS_COL:
			return tempStaff.getAddess();
		case STATU_COL:
			return tempStaff.getStatu();
		case JTITLE_COL:
			return tempStaff.getTitle();
		case DPT_COL:
			return tempStaff.getDPT();
		case PAY_H:
			return tempStaff.getPayPH();
		case OBJECT_COL:
			return tempStaff;
		default:
			return tempStaff.getfName();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	

}
