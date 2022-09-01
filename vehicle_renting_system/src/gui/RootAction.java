package gui;

import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


public class RootAction extends JFrame {
	
	
	public RootAction() {
		setTitle("Root Action ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 304);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Staff");
		lblNewLabel.setBounds(31, 31, 147, 27);
		lblNewLabel.setFont(new Font("Kozuka Mincho Pr6N H", Font.BOLD, 13));
		getContentPane().add(lblNewLabel);
		
		JButton roStfMbtnNewButton = new JButton("Go to Section");
		roStfMbtnNewButton.setBounds(209, 32, 121, 25);
		roStfMbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to root as Staff handle
				PersionSearchTab src1 = new PersionSearchTab(3,1);
				src1.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		roStfMbtnNewButton.setFont(new Font("Russo One", Font.BOLD, 12));
		getContentPane().add(roStfMbtnNewButton);
		
		JButton roCusMbtnNewButton = new JButton("Go to Section");
		roCusMbtnNewButton.setBounds(209, 90, 121, 25);
		roCusMbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//log to the rootAction to customer handle
				PersionSearchTab obj = new PersionSearchTab(1,1);
				obj.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblManageCustomers = new JLabel("Manage Customers");
		lblManageCustomers.setBounds(31, 89, 147, 27);
		lblManageCustomers.setFont(new Font("Kozuka Mincho Pr6N H", Font.BOLD, 13));
		getContentPane().add(lblManageCustomers);
		roCusMbtnNewButton.setFont(new Font("Russo One", Font.BOLD, 12));
		getContentPane().add(roCusMbtnNewButton);
		
		JButton roOwnMbtnNewButton = new JButton("Go to Section");
		roOwnMbtnNewButton.setBounds(209, 148, 121, 25);
		roOwnMbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to root as handle owners handle
				PersionSearchTab ownerSerch = new PersionSearchTab(2,1);
				ownerSerch.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblManageVehicleOwners = new JLabel("Manage Vehicle Owners");
		lblManageVehicleOwners.setBounds(31, 147, 147, 27);
		lblManageVehicleOwners.setFont(new Font("Kozuka Mincho Pr6N H", Font.BOLD, 13));
		getContentPane().add(lblManageVehicleOwners);
		roOwnMbtnNewButton.setFont(new Font("Russo One", Font.BOLD, 12));
		getContentPane().add(roOwnMbtnNewButton);
		
		JButton roDrMbtnNewButton = new JButton("Go to Section");
		roDrMbtnNewButton.setBounds(209, 206, 121, 25);
		roDrMbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to root as drivers handle
				PersionSearchTab ownerSerch = new PersionSearchTab(4,1);
				ownerSerch.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblManageDrivers = new JLabel("Manage Drivers");
		lblManageDrivers.setBounds(31, 205, 147, 27);
		lblManageDrivers.setFont(new Font("Kozuka Mincho Pr6N H", Font.BOLD, 13));
		getContentPane().add(lblManageDrivers);
		roDrMbtnNewButton.setFont(new Font("Russo One", Font.BOLD, 12));
		getContentPane().add(roDrMbtnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(364, 11, 409, 242);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(RootAction.class.getResource("/Image/111.jpg")));
		lblNewLabel_1.setBounds(0, 0, 409, 242);
		panel_1.add(lblNewLabel_1);
	}
}
