package model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import person.core.Staff;
import person.core.StaffPosetion;

public class StfPosetionModel extends AbstractTableModel{
	
	public static final int OBJECT_COL = -1;
	private static final int JTITLE_COL = 0;
	private static final int DPT_COL = 1;
	private static final int PAY_H = 2;
	
	private String[] columnNameStaffpose = {"Posetion", "Department", "Payment Per Hour"};
	
	private List<StaffPosetion> stfpos;
	
	public StfPosetionModel (List <StaffPosetion> theStfpos) {
		stfpos = theStfpos;
	}
	
	@Override
	public int getColumnCount() {
		return columnNameStaffpose.length;
	}

	@Override
	public int getRowCount() {
		return stfpos.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNameStaffpose[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		StaffPosetion tempStaffpos = stfpos.get(row);

		switch (col) {
		case JTITLE_COL:
			return tempStaffpos.getSPTitle();
		case DPT_COL:
			return tempStaffpos.getDPT();
		case PAY_H:
			return tempStaffpos.getSPpayH();
		case OBJECT_COL:
			return tempStaffpos;
		default:
			return tempStaffpos.getSPTitle();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
