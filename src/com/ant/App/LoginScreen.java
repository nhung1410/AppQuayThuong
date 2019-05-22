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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import com.toedter.components.JSpinField;
import java.awt.Color;
import java.awt.Font;

public class LoginScreen extends JFrame {

	private JPanel LoginPanel;
	private JTextField txtUserName;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		LoginPanel = new JPanel();
		LoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPanel);
		LoginPanel.setLayout(null);

		JLabel lblLogin = new JLabel("Name:");
		lblLogin.setBounds(62, 63, 41, 16);
		LoginPanel.add(lblLogin);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(68, 110, 61, 16);
		LoginPanel.add(lblPassword);

		txtUserName = new JTextField();
		txtUserName.setBounds(135, 60, 211, 22);
		LoginPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblUserWarning = new JLabel("");
		lblUserWarning.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblUserWarning.setForeground(Color.RED);
		lblUserWarning.setBounds(135, 81, 211, 16);
		LoginPanel.add(lblUserWarning);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(135, 107, 211, 22);
		LoginPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblPasswarning = new JLabel("");
		lblPasswarning.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPasswarning.setForeground(Color.RED);
		lblPasswarning.setBounds(135, 130, 211, 16);
		LoginPanel.add(lblPasswarning);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = txtUserName.getText();
				String password = txtPassword.getText();
				if (txtUserName.getText().equals("username") && txtPassword.getText().equals("password")) {
					try {
						DashBroadScreen broadScreen = new DashBroadScreen();
						broadScreen.setVisible(true);
						setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					LoginPanel.updateUI();
				} else if ((txtPassword.getText().length() > 0) && (txtUserName.getText().length() >0) ) {
					if (!txtUserName.getText().equals("username")) {
						lblUserWarning.setText("Wrong username. Try again.");
					} else {
						lblPasswarning.setText("Wrong password. Try again.");
					}
					

				} else {
					if(txtUserName.getText().equals("")) 
						lblUserWarning.setText("Enter user name");
					
					else
						
						lblPasswarning.setText("Enter Password");
				}
				
			}
		});

		btnLogin.setBounds(77, 158, 75, 25);
		LoginPanel.add(btnLogin);

		JButton btnRegiser = new JButton("Register");
		btnRegiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterScreen registerScreen = new RegisterScreen();
				registerScreen.setVisible(true);
				setVisible(false);

			}
		});
		btnRegiser.setBounds(209, 158, 89, 25);
		LoginPanel.add(btnRegiser);
		
		
		
		
	}
}
