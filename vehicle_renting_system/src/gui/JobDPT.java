package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.List;
import javax.swing.JOptionPane;

import persionSearch.dao.PersionDAO;
import person.core.StaffPosetion;
import person.core.ContactNo;
import person.core.Staff;
import model.PersionModel;
import model.StfPosetionModel;
import addAndUpdateGUI.PersionAddUpdate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JobDPT extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private PersionAddUpdate pau;
	private PersionDAO pearsiondao;
	private Staff tempP;
	private ContactNo tel1;
	private ContactNo tel2;
	private PersionSearchTab pSerTab;
	private boolean updateMoad = false;

	// constructor access this frame as staff 
	public JobDPT(PersionAddUpdate Pau,PersionDAO Pearsiondao, Staff theStf, ContactNo con1, ContactNo con2, 
			PersionSearchTab psertab, boolean um) {
		this();
		this.pau = Pau;
		this.pearsiondao = Pearsiondao;
		this.tempP = theStf;
		this.tel1 = con1;
		this.tel2 = con2;
		this.pSerTab = psertab;
		this.updateMoad = um;
	}
	
	// constructor access this frame as staff 
	public JobDPT(PersionAddUpdate Pau,PersionDAO Pearsiondao, Staff theStf,PersionSearchTab psertab, boolean um) {
		this();
		this.pau = Pau;
		this.pearsiondao = Pearsiondao;
		this.tempP = theStf;
		this.pSerTab = psertab;
		this.updateMoad = um;
	}

	public JobDPT() {
		
		try {
			 pearsiondao = new PersionDAO();
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Select Position and Department");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		// get job and department details to add or update
		JButton jDPTbtnNewButton = new JButton("SAVE");
		jDPTbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getdptDetails();
			}
		});
		panel.add(jDPTbtnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		try {
			// populate gui
				List<StaffPosetion> staffpos = null;
				
				staffpos = pearsiondao.listofposetion();
				
				StfPosetionModel obj = new StfPosetionModel(staffpos);
				
				table.setModel(obj);
			}
			catch (Exception exc) {
				JOptionPane.showMessageDialog(JobDPT.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
			}
		
		scrollPane.setViewportView(table);
	}

	protected void getdptDetails() {
		// TODO Auto-generated method stub
		Staff tempSTF = tempP;
		
		int row = table.getSelectedRow();
		
		if(updateMoad == false || row >= 0) {
			if(row < 0 ) {
				JOptionPane.showMessageDialog(JobDPT.this, "You must select a Person", "Error",
						JOptionPane.ERROR_MESSAGE);		
				return;
			}
		
			StaffPosetion tempp = (StaffPosetion)  table.getValueAt(row,PersionModel.OBJECT_COL);
			
			tempSTF.setTitleId(tempp.getSPTitId());
			tempSTF.SetTitle(tempp.getSPTitle());
			tempSTF.setDptID(tempp.getSPdptID());
			tempSTF.setDPT(tempp.getDPT());
			tempSTF.setPayPH(tempp.getSPpayH());
	}
		
		try {
			if(updateMoad) {
				pearsiondao.updateStaff(tempSTF);
				pSerTab.refreshPersionView(3, 1);
			}
			else {
				pearsiondao.addStaff(tempSTF);
				pSerTab.refreshPersionView(3, 1);
				if(tel1 != null) {
					pearsiondao.insertTel(tel1,3);
				}
				if(tel2 != null) {
					pearsiondao.insertTel(tel2,3);
				}
			}
			
			setVisible(false);
			dispose();
			
		}
		catch(Exception exc) {
				JOptionPane.showMessageDialog(
						pau,
						"Error saving : "
								+ exc.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
		}
	}

}
