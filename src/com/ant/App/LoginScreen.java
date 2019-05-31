package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import com.ant.entities.*;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.StringUtil;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.sound.sampled.Line;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
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
	private void login() {
		FileReader fr = null;
		BufferedReader br = null;
		String str = null;
	
//		1,nhung,112233,nhung,hn,10/05/2019
		try {

			fr = new FileReader("Login.txt");
			br = new BufferedReader(fr);

			while ((str = br.readLine()) != null) {
			
				String[] token = str.split(",");

				if(token[1].equals(txtUserName.getText())&& token[2].equals(txtPassword.getText()) ) {
					try {
						User info = new User();
						info.setId(Integer.parseInt(token[0]));
						info.setUserName(token[1]);
						info.setPassword(token[2]);
						info.setName(token[3]);
						info.setAddress(token[4]);
						info.setDateOfBirth(token[5]);
						
						DashBroadScreen broadScreen = new DashBroadScreen(info);
						broadScreen.setVisible(true);
						
						setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				}
			if(txtUserName.getText().equals("superadmin") && txtPassword.getText().equals("password")) {
				setVisible(false);
				AdminScreen adminScreen = new AdminScreen();
				adminScreen.setVisible(true);
			}
			else{
			
			}
			

			

			// all code
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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
				
				
				login();
					
					LoginPanel.updateUI();
//			 if ((txtPassword.getText().length() > 0) && (txtUserName.getText().length() > 0)) {
//					if (!txtUserName.getText().equals("username")) {
//						lblUserWarning.setText("Wrong username. Try again.");
//					} else {
//						lblPasswarning.setText("Wrong password. Try again.");
//					}
//
//				} else {
//					if (txtUserName.getText().equals(""))
//						lblUserWarning.setText("Enter user name");
//
//					else
//
//						lblPasswarning.setText("Enter Password");
//				}

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
