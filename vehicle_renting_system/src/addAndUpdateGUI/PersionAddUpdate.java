package addAndUpdateGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.math.BigDecimal;

import persionSearch.dao.PersionDAO;
import gui.JobDPT;
import gui.PersionSearchTab;
import person.core.*;

public class PersionAddUpdate extends JDialog {

	private final JPanel addUpdatePcontentPanel = new JPanel();
	private JTextField pRegtextField;
	private JTextField pUntextField;
	private JTextField pPwdtextField;
	private JTextField pCPwdtextField;
	private JTextField pFNametextField;
	private JTextField pLNametextField;
	private JTextField pMailtextField;
	private JTextField pNICtextField;
	private JTextField pAddsstextField;
	private JTextField pTeltextField1;
	private JTextField pTeltextField2;
	private JTextField dLictextField;
	private JTextField dInstextField;
	
	
	private PersionDAO persiondao;
	private PersionSearchTab pSerTab;
	private ContactNo conTac1;
	private ContactNo conTac2;
	private ContactNo newconTac1;
	private ContactNo newconTac2;
	String msg1 = null;
	String msg2 = null;
	private JTextField dPaytextField;
	
	private Persion previousPersion = null;
	private Drivers previousDrivers = null;
	private Staff previousStaff     = null;
	private boolean updateMode = false;
	/**
	 * @wbp.parser.constructor
	 */
	public PersionAddUpdate(PersionSearchTab pst, PersionDAO pdao, Persion prePersion, ContactNo telNum1,ContactNo telNum2,
			boolean upMod, int Typ) {
		
		this(Typ);
		persiondao      = pdao;
		pSerTab         = pst;
		previousPersion = prePersion;
		updateMode      = upMod;
		conTac1         = telNum1;
		conTac2         = telNum2;
		
		if(updateMode) {
			populateTelGui(conTac1,conTac2);
			
			if(Typ == 1) {
				setTitle("Customer Details Update");
				populatePGui(prePersion);
			}
			else if(Typ == 2) {
				setTitle("Vehicle Owners Details Update");
				populatePGui(prePersion);
			}
		}
		else {
			
			if(Typ == 1) {
				setTitle("Customer Registration");
			}
			else if(Typ == 2) {
				setTitle("Vehicle Owners Registration");
			}
		}
	}
	
	public PersionAddUpdate(PersionSearchTab pst, PersionDAO pdao, Drivers preDriver,ContactNo telNum1,ContactNo telNum2, boolean upMod) {
		
		this(4);
		persiondao      = pdao;
		pSerTab         = pst;
		previousDrivers = preDriver;
		updateMode      = upMod;
		conTac1         = telNum1;
		conTac2         = telNum2;
		
		if(updateMode) {
			setTitle("Drivers Details Update");
			populateDGui(preDriver);
			populateTelGui(conTac1,conTac2);
		}
		else {
			setTitle("Drivers Registration");
		}
	}
	
	public PersionAddUpdate(PersionSearchTab pst, PersionDAO pdao, Staff preStaff, ContactNo telNum1,ContactNo telNum2, boolean upMod, String x) {
		
		this(3);
		persiondao    = pdao;
		pSerTab       = pst;
		previousStaff = preStaff;
		updateMode    = upMod;
		conTac1       = telNum1;
		conTac2       = telNum2;
		
		if(updateMode) {
			setTitle("Staff Details Update");
			populateSGui(preStaff);
			populateTelGui(conTac1,conTac2);
		}
		else {
			setTitle("Staff Registration");
		}
	}
	
	public PersionAddUpdate (PersionSearchTab pst, PersionDAO pdao, int Typ) {
		this(pst, pdao, null, null, null, false, Typ);
	}
	
	public PersionAddUpdate(PersionSearchTab pst, PersionDAO pdao) {
		this(pst, pdao, null, null, null, false);
	}
	
	public PersionAddUpdate(PersionSearchTab pst, PersionDAO pdao, String x) {
		this(pst, pdao, null, null, null, false, x);
	}
	
	// populate update GUI with data  for customer and owner
	private void populatePGui(Persion prePer) {
		
		pRegtextField.setText(prePer.getRegNo());
		pUntextField.setText(prePer.getUseName());
		pPwdtextField.setText(prePer.getPwd());
		pCPwdtextField.setText(prePer.getPwd());
		pFNametextField.setText(prePer.getfName());
		pLNametextField.setText(prePer.getlName());
		pMailtextField.setText(prePer.getMail());
		pNICtextField.setText(prePer.getNic());
		pAddsstextField.setText(prePer.getAddess());
	}
	
	// populate update GUI with data  for Drivers
	private void populateDGui(Drivers preDir) {
		
		pRegtextField.setText(preDir.getRegNo());
		pUntextField.setText(preDir.getUseName());
		pPwdtextField.setText(preDir.getPwd());
		pCPwdtextField.setText(preDir.getPwd());
		pFNametextField.setText(preDir.getfName());
		pLNametextField.setText(preDir.getlName());
		pMailtextField.setText(preDir.getMail());
		pNICtextField.setText(preDir.getNic());
		pAddsstextField.setText(preDir.getAddess());
		dLictextField.setText(preDir.getLicId());
		dInstextField.setText(preDir.getInsId());
		dPaytextField.setText(preDir.getDpay().toString());
		
	}
	
	// populate update GUI with data  for staff
	private void populateSGui(Staff preStf) {
		
		pRegtextField.setText(preStf.getRegNo());
		pUntextField.setText(preStf.getUseName());
		pPwdtextField.setText(preStf.getPwd());
		pCPwdtextField.setText(preStf.getPwd());
		pFNametextField.setText(preStf.getfName());
		pLNametextField.setText(preStf.getlName());
		pMailtextField.setText(preStf.getMail());
		pNICtextField.setText(preStf.getNic());
		pAddsstextField.setText(preStf.getAddess());
	}
	
	// populate update GUI with data  for customer, staff, driver and owner
	public void populateTelGui(ContactNo tel1,ContactNo tel2 ){
		
		String tempT1 = tel1.getTel();
		String tempT2 = tel2.getTel();
		
		if(tempT1 != null && tempT1.trim().length() > 3) {
			pTeltextField1.setText(tempT1);
		}
		
		if(tempT2 != null && tempT2.trim().length() > 3) {
			pTeltextField2.setText(tempT2);
		}
	}

	//customer(typ = 1), vehicle owners(typ = 2), staff(typ = 3), drivers(typ = 4)
	public PersionAddUpdate(int Typ) {
		
		if(Typ == 1) {
			setBounds(100, 100, 626, 500);
			msg1 = "Customer added succesfully.";
			msg2 = "Customer Added";
		}
		else if(Typ == 2) {
			setBounds(100, 100, 626, 500);
			msg1 = "Vehicle Owner added succesfully.";
			msg2 = "Vehicle Owner Added";
		}
		else if(Typ == 3) {
			setBounds(100, 100, 626, 500);
			msg1 = "Staff member added succesfully.";
			msg2 = "Staff Member Added";
		}
		else if(Typ == 4) {
			setBounds(100, 100, 626, 550);
			msg1 = "Driver added succesfully.";
			msg2 = "Driver Added";
		}
		
		getContentPane().setLayout(new BorderLayout());
		addUpdatePcontentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(addUpdatePcontentPanel, BorderLayout.CENTER);
		addUpdatePcontentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel cuslblNewLabel = new JLabel("Registration No :");
			cuslblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(cuslblNewLabel, "4, 4");
		}
		{
			pRegtextField = new JTextField();
			addUpdatePcontentPanel.add(pRegtextField, "8, 4, fill, default");
			pRegtextField.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("First Name  :");
			lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblFirstName, "4, 8");
		}
		{
			pFNametextField = new JTextField();
			addUpdatePcontentPanel.add(pFNametextField, "8, 8, fill, default");
			pFNametextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name  :");
			lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblLastName, "4, 12");
		}
		{
			pLNametextField = new JTextField();
			addUpdatePcontentPanel.add(pLNametextField, "8, 12, fill, default");
			pLNametextField.setColumns(10);
		}
		{
			JLabel cuslblNewLabel = new JLabel("User Name :");
			cuslblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(cuslblNewLabel, "4, 16");
		}
		{
			pUntextField = new JTextField();
			addUpdatePcontentPanel.add(pUntextField, "8, 16, fill, default");
			pUntextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password  :");
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblPassword, "4, 20");
		}
		{
			pPwdtextField = new JTextField();
			addUpdatePcontentPanel.add(pPwdtextField, "8, 20, fill, default");
			pPwdtextField.setColumns(10);
		}
		{
			JLabel lblComformPassword = new JLabel("Comfirm Password  :");
			lblComformPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblComformPassword, "4, 22");
		}
		{
			pCPwdtextField = new JTextField();
			addUpdatePcontentPanel.add(pCPwdtextField, "8, 22, fill, default");
			pCPwdtextField.setColumns(10);
		}
		{
			JLabel lblTel = new JLabel("Tel 1 :");
			lblTel.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblTel, "4, 26");
		}
		{
			pTeltextField1 = new JTextField();
			addUpdatePcontentPanel.add(pTeltextField1, "8, 26, fill, default");
			pTeltextField1.setColumns(10);
		}
		{
			JLabel lblTel_1 = new JLabel("Tel 2:");
			lblTel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblTel_1, "4, 28");
		}
		{
			pTeltextField2 = new JTextField();
			addUpdatePcontentPanel.add(pTeltextField2, "8, 28, fill, default");
			pTeltextField2.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email  :");
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblEmail, "4, 32");
		}
		{
			pMailtextField = new JTextField();
			addUpdatePcontentPanel.add(pMailtextField, "8, 32, fill, default");
			pMailtextField.setColumns(10);
		}
		{
			JLabel lblNic = new JLabel("NIC  :");
			lblNic.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblNic, "4, 36");
		}
		{
			pNICtextField = new JTextField();
			addUpdatePcontentPanel.add(pNICtextField, "8, 36, fill, default");
			pNICtextField.setColumns(10);
		}
		{
			JLabel lblNic = new JLabel("Address  :");
			lblNic.setFont(new Font("Tahoma", Font.BOLD, 12));
			addUpdatePcontentPanel.add(lblNic, "4, 40");
		}
		{
			pAddsstextField = new JTextField();
			addUpdatePcontentPanel.add(pAddsstextField, "8, 40, fill, default");
			pAddsstextField.setColumns(10);
		}
		
		if(Typ == 4){
			{
				JLabel lblPayment = new JLabel("Payment  :");
				lblPayment.setFont(new Font("Tahoma", Font.BOLD, 12));
				addUpdatePcontentPanel.add(lblPayment, "4, 52");
			}
			{
				dPaytextField = new JTextField();
				addUpdatePcontentPanel.add(dPaytextField, "8, 52, fill, default");
				dPaytextField.setColumns(10);
			}
		
		
			{
				JLabel dLiclblNic = new JLabel("License ID  :");
				dLiclblNic.setFont(new Font("Tahoma", Font.BOLD, 12));
				addUpdatePcontentPanel.add(dLiclblNic, "4, 44");
			}
			{
				dLictextField = new JTextField();
				addUpdatePcontentPanel.add(dLictextField, "8, 44, fill, default");
				dLictextField.setColumns(10);
			}
			{
				JLabel dInslblNic = new JLabel("Insuarance ID  :");
				dInslblNic.setFont(new Font("Tahoma", Font.BOLD, 12));
				addUpdatePcontentPanel.add(dInslblNic, "4, 48");
			}
			{
				dInstextField = new JTextField();
				addUpdatePcontentPanel.add(dInstextField, "8, 48, fill, default");
				dInstextField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//save customers
						savePerson(Typ);
					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void savePerson(int Typ) {
		// TODO Auto-generated method stub
		
		//read Text field data
		String regNo = pRegtextField.getText();
		String uNam  = pUntextField.getText();
		String pwd   = pPwdtextField.getText();
		String cpwd  = pCPwdtextField.getText();
		String fnam  = pFNametextField.getText();
		String lnam  = pLNametextField.getText();
		String tel1  = pTeltextField1.getText();
		String tel2  = pTeltextField2.getText();
		String mail  = pMailtextField.getText();
		String nic   = pNICtextField.getText();
		String add   = pAddsstextField.getText();
		String Lic   = null;
		String Ins   = null;
		String price = null;
		BigDecimal payment = null;
		
		if(Typ == 4) {
			Lic = dLictextField.getText();
			Ins = dInstextField.getText();
			price = dPaytextField.getText();
			payment = convertStringToBigDecimal(price);
		}
		
		String string1 = new String(pwd); 
		String string2 = new String(cpwd); 
		
		if(string1.equals(string2)) {
		
		Persion tempPersion = null;
		Drivers tempDriver  = null;
		Staff theStaff      = null;
		ContactNo tempCon1   = null;
		ContactNo tempCon2   = null;
		int telno1 = 0;
		int telno2 = 0;
		
		if(updateMode) {
			if(Typ == 1 || Typ == 2) {
				tempPersion = previousPersion;
				
				tempPersion.setUseName(uNam);
				tempPersion.setPwd(pwd);
				tempPersion.setfName(fnam);
				tempPersion.setlName(lnam);
				tempPersion.setMail(mail);
				tempPersion.setNic(nic);
				tempPersion.setAddress(add);
				tempPersion.setStatu(true);
			}
			if(Typ == 3) {
				theStaff = previousStaff;
				
				theStaff.setUseName(uNam);
				theStaff.setPwd(pwd);
				theStaff.setfName(fnam);
				theStaff.setlName(lnam);
				theStaff.setMail(mail);
				theStaff.setNic(nic);
				theStaff.setAddress(add);
				theStaff.setStatu(true);
				
			}
			if(Typ == 4) {
				tempDriver = previousDrivers;
				
				tempDriver.setUseName(uNam);
				tempDriver.setPwd(pwd);
				tempDriver.setfName(fnam);
				tempDriver.setlName(lnam);
				tempDriver.setNic(nic);
				tempDriver.setLicId(Lic);
				tempDriver.setInsId(Ins);
				tempDriver.setMail(mail);
				tempDriver.setAddress(add);
				tempDriver.setDpay(payment);
				tempDriver.setStatu(true);
				
			}
			
			tempCon1 = conTac1;
			tempCon2 = conTac2;
			
			if(tel1 != null && tel1.trim().length() > 3) {
				tempCon1.setTel(tel1);
				 telno1 = 1;
			}
			
			if(tel2 != null && tel2.trim().length() > 3) {
				tempCon2.setTel(tel2);
				telno2 = 1;
			}
		}
		else {
			if(Typ == 1 || Typ == 2) {
				tempPersion = new Persion(regNo, uNam, pwd, fnam, lnam, mail, nic, add, true);
			}
			if(Typ == 4) {
				tempDriver = new Drivers(regNo, uNam, pwd, fnam, lnam, nic, Lic, Ins, mail, add, payment, true);
			}
			
			
			
			if(tel1 != null && tel1.trim().length() > 3) {
				 tempCon1 = new ContactNo(regNo,tel1);
				 telno1 = 1;
			}
			
			if(tel2 != null && tel2.trim().length() > 3) {
				tempCon2 = new ContactNo(regNo,tel2);
				telno2 = 1;
			}
		}
		
		try {
			if(updateMode) {
				if(Typ == 1 || Typ == 2) {
					persiondao.updatePerson(tempPersion, Typ);
				}
				else if(Typ == 4) {
					persiondao.updateDriver(tempDriver);
				}
				
				if(telno1 == 1) {
					persiondao.updateContact(conTac1,tempCon1,Typ);
				}
				if(telno2 == 1) {
					persiondao.updateContact(conTac2,tempCon2,Typ);
				}
				
			}
			else {
				// call add methods
				if(Typ == 1 || Typ == 2) {
					persiondao.addPersion(tempPersion, Typ);
				}
				else if(Typ == 4) {
					persiondao.insertDriver(tempDriver);
				}
				if(Typ != 3) {
					if(telno1 == 1) {
						persiondao.insertTel(tempCon1,Typ);
					}
					if(telno2 == 1) {
						persiondao.insertTel(tempCon2,Typ);
					}
				}
			}
			
			if(updateMode) {
				if(Typ == 3) {
					persiondao.updateStaff(theStaff);
					
					JobDPT obj12 = new JobDPT(PersionAddUpdate.this ,persiondao, theStaff, pSerTab, true);
					
					obj12.setVisible(true);
				}
			}
			else {
				if(Typ == 3) {
					theStaff = new Staff(regNo ,0, uNam, pwd, fnam, lnam, mail, nic, add, true, "x", 0, "x", 0);
					
					if(telno1 == 0) {
						tempCon1 = null;
					}
					if(telno2 == 0) {
						tempCon2 = null;
					}
					
					JobDPT obj11 = new JobDPT(PersionAddUpdate.this ,persiondao, theStaff, tempCon1, tempCon2, pSerTab, false);
					
					obj11.setVisible(true);
				}
			}
			
			//close dialog
			setVisible(false);
			dispose();
			
			//refresh GUI
			pSerTab.refreshPersionView(Typ, 1);
			
			JOptionPane.showMessageDialog(pSerTab,
					msg1,msg2,
					JOptionPane.INFORMATION_MESSAGE);
		
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(
					pSerTab,
					"Error saving : "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		}
		else {
			JOptionPane.showMessageDialog(pSerTab,
					"Wrong Password","Password",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected BigDecimal convertStringToBigDecimal(String salaryStr) {

		BigDecimal result = null;

		try {
			double salaryDouble = Double.parseDouble(salaryStr);

			result = BigDecimal.valueOf(salaryDouble);
		} catch (Exception exc) {
			System.out.println("Invalid value. Defaulting to 0.0");
			result = BigDecimal.valueOf(0.0);
		}

		return result;
	}
}
