package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;

import persionSearch.dao.*;
import person.core.*;
import model.*;
import addAndUpdateGUI.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class PersionSearchTab extends JFrame {

	private JPanel contentPane;
	private JTextField pNametextField;
	private JTable table;
	
	private PersionDAO persiondao;
	private JTextField RegPertextField;
	
	private static String actMsg= null;
	private static String ADbtn = null;
	private static String tabTitle = null;
	
	//customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	// stat (0-deactivate, 1-activate)
	public PersionSearchTab(int typ, int stat) {
		
		// assign message names, button names and tab name
		if(typ == 1 && stat == 1) {
			actMsg = "Searched All Active Customers"; 
			ADbtn = "Deactive Customers";
			tabTitle = "Active Customer Search";
		}
		else if(typ == 1 && stat == 0) {
			actMsg = "Searched All Deactive Customers";
			ADbtn = "Activate Customers";
			tabTitle = "Deactive Customer Search";
		}
		else if(typ == 2 && stat == 1) {
			actMsg = "Searched All Active Owners";
			ADbtn = "Deactive Owners";
			tabTitle = "Active Vehicle Owners Search";
		}
		else if(typ == 2 && stat == 0){
			actMsg = "Searched All Deactive Owners";
			ADbtn = "Activae Owners";
			tabTitle = "Deactive Vehicle Owners Search";
		}
		else if(typ == 3 && stat == 1){
			actMsg = "Searched All Active Staff";
			ADbtn = "Deactive Staff";
			tabTitle = "Active Staff Search";
		}
		else if(typ == 3 && stat == 0){
			actMsg = "Searched All Deactive Staff";
			ADbtn = "Activae Staff";
			tabTitle = "Deactive Staff Search";
		}
		else if(typ == 4 && stat == 1){
			actMsg = "Searched All Active Drivers";
			ADbtn = "Deactive Drivers";
			tabTitle = "Active Drivers Search";
		}
		else if(typ == 4 && stat == 0){
			actMsg = "Searched All Deactive Drivers";
			ADbtn = "Activae Drivers";
			tabTitle = "Deactive Drivers search";
		}
		else {
			// to diBug
			System.out.println("PersonSearchTan Constrauctor: Undefined value");
		}
		
		//create DAO
		try {
			persiondao = new PersionDAO();
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		setTitle(tabTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 20, 1300, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1324, 33);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Enter Name");
		panel.add(lblNewLabel);
		
		pNametextField = new JTextField();
		panel.add(pNametextField);
		pNametextField.setColumns(30);
		
		//search button
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get name from text box
					String PersionName = pNametextField.getText();
					
					if(typ == 1 || typ == 2) {
					//create Person array
						List<Persion> persions = null;
						
						
						//get Pearson with name
						if (PersionName != null && PersionName.trim().length() > 0) {
							persions = persiondao.searchPWithName(PersionName,typ,stat); // get Pearson with name
						}
						else {
							persions = persiondao.getListOfPersion(typ,stat); // get all Pearson
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
						
						// create table
						PersionModel model = new PersionModel(persions);
						table.setModel(model);
					}
					
					else if(typ == 3) {
						
						List<Staff> staffs = null;
						
						//get Pearson with name
						if (PersionName != null && PersionName.trim().length() > 0) {
							staffs = persiondao.searchStfWithName(PersionName,stat); // get Pearson with name
						}
						else {
							staffs = persiondao.getListOfStaff(stat); // get all Pearson
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
						
						// create table
						StaffModel model = new StaffModel(staffs);
						table.setModel(model);
					}
					
					else if(typ == 4) {
						
						List<Drivers> drivers = null;
						
						//get Pearson with name
						if (PersionName != null && PersionName.trim().length() > 0) {
							drivers = persiondao.searchDriWithName(PersionName,stat); // get Pearson with name
						}
						else {
							drivers = persiondao.getListOfDriver(stat); // get all Pearson
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
						
						// create table
						DriverModel model = new DriverModel(drivers);
						table.setModel(model);
					}
					
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(PersionSearchTab.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("    ");
		panel.add(lblNewLabel_2);
		
		JLabel lblEnterRegNo = new JLabel("Enter Reg No");
		panel.add(lblEnterRegNo);
		
		RegPertextField = new JTextField();
		RegPertextField.setColumns(10);
		panel.add(RegPertextField);
		
		// search button with registration number
		JButton cusRegbtnNewButton = new JButton("Search");
		cusRegbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get registration number from text box
					String PersionRegNo = RegPertextField.getText();
					
					if(typ == 1 || typ == 2) {
						List<Persion> persions = null;
						
						// for Persion Search with registration number
	
						if (PersionRegNo != null && PersionRegNo.trim().length() > 0) {
							persions = persiondao.searchPWithID(PersionRegNo,typ,stat); // get Persion with name
						}
						else {
							persions = persiondao.getListOfPersion(typ,stat); // get all Persion
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
	
	
						// create table
						PersionModel model = new PersionModel(persions);
						table.setModel(model);
					}
					else if(typ == 3) {
						
						List<Staff> staffs = null;
						
						// for Persion Search with registration number
						
						if (PersionRegNo != null && PersionRegNo.trim().length() > 0) {
							staffs = persiondao.searchStfWithID(PersionRegNo,stat); // get Persion with name
						}
						else {
							staffs = persiondao.getListOfStaff(stat); // get all Persion
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
	
						// create table
						StaffModel model = new StaffModel(staffs);
						table.setModel(model);
					}
					else if(typ == 4) {
						
						List<Drivers> drivers = null;
						
						// for Persion Search with registration number
						
						if (PersionRegNo != null && PersionRegNo.trim().length() > 0) {
							drivers = persiondao.searchDriWithID(PersionRegNo,stat); // get Persion with name
						}
						else {
							drivers = persiondao.getListOfDriver(stat); // get all Persion
								
							JOptionPane.showMessageDialog(null, actMsg); 
						}
	
						// create table
						DriverModel model = new DriverModel(drivers);
						table.setModel(model);
					}
					
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(PersionSearchTab.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(cusRegbtnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("                                          ");
		panel.add(lblNewLabel_3);
		
		// to active and deactivate users button
		JButton useDAbtnNewButton = new JButton(ADbtn);
		useDAbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Deactivate tab
				PersionSearchTab DiactiveTab = null;
				if (stat == 1) {
					DiactiveTab = new PersionSearchTab(typ,0);
				}
				if (stat == 0) {
					DiactiveTab = new PersionSearchTab(typ,1);
				}
				DiactiveTab.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		panel.add(useDAbtnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("             ");
		panel.add(lblNewLabel_4);
		
		//back to root menu
		JButton bkRootbtnNewButton = new JButton("Back To Root Menu");
		bkRootbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAction root1 = new RootAction();
				root1.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		panel.add(bkRootbtnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		scrollPane.setBounds(5, 38, 1048, 562);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// new registration
		if(stat == 1) {
			JButton pAddbtnNewButton = new JButton("New Registration");
			pAddbtnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PersionAddUpdate obj = new PersionAddUpdate(PersionSearchTab.this, persiondao, typ);
					obj.setVisible(true);
				}
			});
			
			pAddbtnNewButton.setBounds(1080, 269, 170, 23);
			contentPane.add(pAddbtnNewButton);
		}
		
		// go to update tab button
		if(stat == 1) {
			JButton pUpdbtnNewButton = new JButton("Update Details");
			pUpdbtnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//get selected row
					int row = table.getSelectedRow();
					
					if(row < 0 ) {
						JOptionPane.showMessageDialog(PersionSearchTab.this, "You must select a Person", "Error",
								JOptionPane.ERROR_MESSAGE);		
						return;
					}
					Persion thePersion = null;
					Drivers theDrivers1= null;
					Staff theStaff     = null;
					ContactNo theCont1 = null;
					ContactNo theCont2 = null;
					String regnum      = null;
					
					// get current Employee
					if(typ == 1 || typ == 2) {
						thePersion = (Persion)  table.getValueAt(row,PersionModel.OBJECT_COL);
						regnum = thePersion.getRegNo();
					}
					if(typ == 3) {
						theStaff = (Staff)  table.getValueAt(row,StaffModel.OBJECT_COL);
						regnum = theStaff.getRegNo();
					}
					if(typ == 4) {
						theDrivers1 = (Drivers)  table.getValueAt(row,DriverModel.OBJECT_COL);
						regnum = theDrivers1.getRegNo();
					}
					
					List<ContactNo> tellist = null;
					
					try {
						tellist = persiondao.getTelNumbers(regnum, typ);
					}
					catch (Exception exc){
						JOptionPane.showMessageDialog(PersionSearchTab.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					ContactNo coobj = new ContactNo("0","-");
					
					int sizearr = tellist.size();
					
					if(sizearr >= 2) {
						theCont1 = tellist.get(0);
						theCont2 = tellist.get(1);
					}
					else if(sizearr == 1) {
						theCont1 = tellist.get(0);
						theCont2 = coobj;
					}
					else {
						theCont1 = coobj;
						theCont2 = coobj;
					}
					
					PersionAddUpdate objU = null;
					
					if(typ == 1 || typ == 2) {
						objU = new PersionAddUpdate(PersionSearchTab.this, persiondao, thePersion, theCont1, theCont2, true, typ);
					}
					if(typ == 3) {
						objU = new PersionAddUpdate(PersionSearchTab.this, persiondao, theStaff, theCont1, theCont2, true, "x");
					}
					if(typ == 4) {
						objU = new PersionAddUpdate(PersionSearchTab.this, persiondao, theDrivers1, theCont1, theCont2, true);
					}
					
					objU.setVisible(true);
				}
			});
			pUpdbtnNewButton.setBounds(1080, 317, 170, 23);
			contentPane.add(pUpdbtnNewButton);
		}
			//active deactivate button
			JButton dApdbtnNewButton_1 = null;
			if(stat == 1) {
				dApdbtnNewButton_1 = new JButton("Deactive User");
			}
			if(stat == 0) {
				dApdbtnNewButton_1 = new JButton("Active User");
			}
			dApdbtnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String admsg  = null;
					
					if(stat == 1) {
						admsg = "Deactive this Person?";
					}
					if(stat == 0) {
						admsg = "Active this Person?";
					}
						
					try {
						int row = table.getSelectedRow();
							
						if(row < 0 ) {
							JOptionPane.showMessageDialog(PersionSearchTab.this, "You must select a Person", "Error",
									JOptionPane.ERROR_MESSAGE);		
							return;
						}
							
							// prompt the user
							int response = JOptionPane.showConfirmDialog(
									PersionSearchTab.this, admsg, "Confirm", 
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
							if (response != JOptionPane.YES_OPTION) {
								return;
							}
							
							boolean adstat = false;
							
							if(stat == 0) {
								adstat = true;
							}
							if(stat == 1) {
								adstat = false;
							}
							
							if(typ == 1 || typ == 2) {
								Persion tempPersion = (Persion) table.getValueAt(row, PersionModel.OBJECT_COL);
								tempPersion.setStatu(adstat);
								persiondao.updatePerson(tempPersion, typ);
							}
							else if(typ == 3) {
								Staff tempStaff = (Staff) table.getValueAt(row, StaffModel.OBJECT_COL);
								tempStaff.setStatu(adstat);
								persiondao.updateStaff(tempStaff);
							}
							else if(typ == 4) {
								Drivers tempDriver = (Drivers) table.getValueAt(row, DriverModel.OBJECT_COL);
								tempDriver.setStatu(adstat);
								persiondao.updateDriver(tempDriver);
							}
							refreshPersionView(typ, stat);
							
							String amsg1 = null;
							String amsg2 = null;
							
							if(stat == 0) {
								amsg1 = "Person Activated succesfully.";
								amsg2 = "Person Activated";
							}
							if(stat == 1) {
								amsg1 = "Person Deactivated succesfully.";
								amsg2 = "Person Deactivated";
							}
							
							JOptionPane.showMessageDialog(PersionSearchTab.this,
									amsg1, amsg2,
									JOptionPane.INFORMATION_MESSAGE);
						}
						catch (Exception exc) {
							JOptionPane.showMessageDialog(PersionSearchTab.this,
									"Error deleting Person: " + exc.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
				}
			});
			dApdbtnNewButton_1.setBounds(1080, 372, 170, 23);
			contentPane.add(dApdbtnNewButton_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(1063, 49, 210, 163);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("New label");
			lblNewLabel_5.setIcon(new ImageIcon(PersionSearchTab.class.getResource("/Image/aaa.png")));
			lblNewLabel_5.setBounds(0, -17, 210, 194);
			panel_1.add(lblNewLabel_5);
			
			// user  delete button 
			if(stat == 0) {
				JButton dilbtnNewButton_1 = new JButton("Permanatly Delete User");
				dilbtnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							int row = table.getSelectedRow();
							
							if(row < 0 ) {
								JOptionPane.showMessageDialog(PersionSearchTab.this, "You must select a Person", "Error",
										JOptionPane.ERROR_MESSAGE);		
								return;
							}
							
							// prompt the user
							int response = JOptionPane.showConfirmDialog(
									PersionSearchTab.this, "Delete this Person? When delete Some Other Table Data will be DELETE"
									 + " and USLESS. Are You Shure? ", "Confirm", 
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
							if (response != JOptionPane.YES_OPTION) {
								return;
							}
							
							if(typ == 1 || typ == 2) {
								Persion tempPersion = (Persion) table.getValueAt(row, PersionModel.OBJECT_COL);
								persiondao.permantlyDelUser(tempPersion.getRegNo(), typ);
							}
							else if(typ == 3) {
								Staff tempStaff = (Staff) table.getValueAt(row, StaffModel.OBJECT_COL);
								persiondao.permantlyDelUser(tempStaff.getRegNo(), typ);
							}
							else if(typ == 4) {
								Drivers tempDriver = (Drivers) table.getValueAt(row, DriverModel.OBJECT_COL);
								persiondao.permantlyDelUser(tempDriver.getRegNo(), typ);
							}
							
							refreshPersionView(typ, stat);
							
							JOptionPane.showMessageDialog(PersionSearchTab.this,
									"Staff deleted succesfully.", "Staff Deleted",
									JOptionPane.INFORMATION_MESSAGE);
						}
						catch (Exception exc) {
							JOptionPane.showMessageDialog(PersionSearchTab.this,
									"Error deleting Person: " + exc.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				dilbtnNewButton_1.setBounds(1080, 428, 170, 23);
				contentPane.add(dilbtnNewButton_1);
			//}
		}
	}
	
	// refresh table method
	//customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	// stat (0-deactivate, 1-activate)
	public void refreshPersionView(int typ, int stat) {
		
		try {
			if(typ == 1 || typ == 2) {
				 List<Persion> persion = persiondao.getListOfPersion(typ, stat);
				 
				 PersionModel model = new PersionModel(persion);
				 table.setModel(model);
			}
			else if(typ == 4) {
				List<Drivers> drivers = persiondao.getListOfDriver(stat);
				 
				 DriverModel model = new DriverModel(drivers);
				 table.setModel(model);
				
			}
			else if(typ == 3) {
				List<Staff> staffs = persiondao.getListOfStaff(stat);
				 
				 StaffModel model = new StaffModel(staffs);
				 table.setModel(model);
				
			}
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
