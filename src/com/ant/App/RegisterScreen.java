package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints.Key;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtRePassword;
	private JTextField txtAddress;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
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
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 518);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setForeground(new Color(255, 255, 255));
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegister.setBounds(52, 13, 86, 57);
		contentPane.add(lblRegister);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 83, 489, 388);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(42, 32, 73, 16);
		panel.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(161, 29, 257, 22);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblUserNamewarn = new JLabel("");
		lblUserNamewarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblUserNamewarn.setForeground(Color.RED);
		lblUserNamewarn.setBounds(161, 49, 257, 16);
		panel.add(lblUserNamewarn);
		
		txtUserName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					lblUserNamewarn.setText("You can use letters(a-z, A_Z) & periods");
				}
			}
		});
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(42, 80, 56, 16);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(161, 77, 257, 22);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNamewarn = new JLabel("");
		lblNamewarn.setForeground(Color.RED);
		lblNamewarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNamewarn.setBounds(161, 97, 257, 16);
		panel.add(lblNamewarn);
		
		JLabel lblPassword = new JLabel("Create password");
		lblPassword.setBounds(42, 123, 114, 16);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(161, 120, 257, 22);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblPassWarn = new JLabel("");
		lblPassWarn.setForeground(Color.RED);
		lblPassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPassWarn.setBounds(161, 140, 257, 16);
		panel.add(lblPassWarn);
		
		
		JLabel lblRePassword = new JLabel("Confirm password");
		lblRePassword.setBounds(42, 169, 114, 16);
		panel.add(lblRePassword);
		
		txtRePassword = new JPasswordField();
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(161, 166, 257, 22);
		panel.add(txtRePassword);
		
		JLabel lblRePassWarn = new JLabel("");
		lblRePassWarn.setForeground(Color.RED);
		lblRePassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblRePassWarn.setBounds(161, 186, 257, 16);
		panel.add(lblRePassWarn);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(42, 211, 56, 16);
		panel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(161, 208, 257, 22);
		panel.add(txtAddress);
		
		JLabel lblAddressWarn = new JLabel("");
		lblAddressWarn.setForeground(Color.RED);
		lblAddressWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblAddressWarn.setBounds(161, 242, 257, 16);
		panel.add(lblAddressWarn);
		
		JLabel lblBirthday = new JLabel("Date of birth");
		lblBirthday.setBounds(42, 254, 88, 16);
		panel.add(lblBirthday);
		JDateChooser txtbirthDate = new JDateChooser();
		
		txtbirthDate.setBounds(161, 254, 159, 22);
		panel.add(txtbirthDate);
		txtbirthDate.setDateFormatString("dd/MM/yyyy");

		JLabel lblBirthWarn = new JLabel("");
		lblBirthWarn.setForeground(Color.RED);
		lblBirthWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBirthWarn.setBounds(160, 279, 160, 16);
		panel.add(lblBirthWarn);
		
		
		 
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				String loginName = txtUserName.getText();
				String name = txtName.getText();
				String pass = String.valueOf(txtPassword.getText());
				String rePass = String.valueOf(txtRePassword.getText());
				String address = txtAddress.getText();
				String dateBirth = txtbirthDate.getDateFormatString();
	
				user.setLoginName(loginName);
				user.setName(name);
				user.setPassword(pass);
				user.setAddress(address);
				user.setDateOfBirth(dateBirth);
				
				if(loginName.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+") || loginName.matches("[1-9]")) {
				
					lblUserNamewarn.setText("You can use letters(a-z, A_Z) & periods");
				}
				else if(loginName.equals("")) {
					lblUserNamewarn.setText("Enter Username");
				}
				
			}	
		});
		
		
		btnSubmit.setBounds(88, 327, 88, 25);
		panel.add(btnSubmit);
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText("");
				txtName.setText("");
				txtPassword.setText("");
				txtRePassword.setText("");
				txtAddress.setText("");
				txtbirthDate.setDateFormatString("");
		
				
			}
		});
		btnClear.setBounds(302, 327, 97, 25);
		panel.add(btnClear);
		
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
}
