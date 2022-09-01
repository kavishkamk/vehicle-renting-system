package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import persionSearch.dao.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class login extends JFrame {

	private JPanel contentPane;
	
	private PersionDAO persiondao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 20, 1219, 509);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 976, 477);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("ABC");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/Image/Untitled.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(971, 0, 232, 470);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ABC");
		lblNewLabel_1.setFont(new Font("Wide Latin", Font.BOLD, 17));
		lblNewLabel_1.setBounds(34, 22, 101, 65);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vehicle");
		lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(81, 62, 121, 72);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Renting");
		lblNewLabel_1_2.setFont(new Font("Wide Latin", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(44, 119, 128, 72);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("System");
		lblNewLabel_1_3.setFont(new Font("Wide Latin", Font.BOLD, 17));
		lblNewLabel_1_3.setBounds(81, 177, 121, 72);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("Log as a Root");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(34, 320, 125, 22);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Log");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAction dialog1 = new RootAction();
				dialog1.setVisible(true);
				
				setVisible(false);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(81, 371, 89, 23);
		panel_1.add(btnNewButton);
	}
}
