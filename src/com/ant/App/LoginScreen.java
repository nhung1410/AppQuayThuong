package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.ant.Util.SqliteConnection;
import com.ant.entities.*;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.StringUtil;
import org.omg.CORBA.PUBLIC_MEMBER;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.ant.entities.User;
import java.sql.*;
import java.sql.Connection;

import java.awt.Color;
import java.awt.Font;

public class LoginScreen extends JFrame {

	private JPanel LoginPanel;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JLabel lblUserWarning;
	private JLabel lblPasswarning;

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

		try {

			fr = new FileReader("Login.txt");
			br = new BufferedReader(fr);

			while ((str = br.readLine()) != null) {

				String[] token = str.split(",");

				if (token[1].equals(txtUserName.getText()) && token[2].equals(txtPassword.getText())) {
					try {
						User info = new User();
						info.setId(Integer.parseInt(token[0]));
						info.setUserName(token[1]);
						info.setPassword(token[2]);
						info.setName(token[3]);
						info.setAge(Integer.parseInt(token[4]));

						info.setAddress(token[5]);

						ProfileScreen broadScreen = new ProfileScreen(info);
						broadScreen.setVisible(true);

						setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if ((token[1].equals(txtUserName.getText())) && (!token[2].equals(txtPassword.getText()))) {
					lblPasswarning.setText("Wrong password. Try again.");

				}

				else if ((!token[1].equals(txtUserName.getText())) && (token[2].equals(txtPassword.getText()))) {

					lblUserWarning.setText("Wrong username. Try again.");

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	Connection conn;

	public LoginScreen() {
		conn = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		LoginPanel = new JPanel();
		LoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPanel);
		LoginPanel.setLayout(null);

		JLabel lblLogin = new JLabel("Name:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(48, 63, 55, 16);
		LoginPanel.add(lblLogin);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(48, 110, 75, 16);
		LoginPanel.add(lblPassword);

		txtUserName = new JTextField();
		txtUserName.setBounds(135, 60, 211, 22);
		LoginPanel.add(txtUserName);
		txtUserName.setColumns(10);

		lblUserWarning = new JLabel("");
		lblUserWarning.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblUserWarning.setForeground(Color.RED);
		lblUserWarning.setBounds(135, 81, 211, 16);
		LoginPanel.add(lblUserWarning);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(135, 107, 211, 22);
		LoginPanel.add(txtPassword);
		txtPassword.setColumns(10);

		lblPasswarning = new JLabel("");
		lblPasswarning.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPasswarning.setForeground(Color.RED);
		lblPasswarning.setBounds(135, 130, 211, 16);
		LoginPanel.add(lblPasswarning);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if ((txtPassword.getText().length() > 0) && (txtUserName.getText().length() > 0)) {
						if (txtUserName.getText().equals("superadmin") && txtPassword.getText().equals("password")) {
							
							ListEmployeeScreen adminScreen = new ListEmployeeScreen();
							adminScreen.setVisible(true);
							setVisible(false);
						} else {
							login();
						}

					} else {
						if (txtUserName.getText().equals(""))
							lblUserWarning.setText("Enter Username");

						else

							lblPasswarning.setText("Enter Password");
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		btnLogin.setBounds(77, 158, 75, 25);
		LoginPanel.add(btnLogin);

		JButton btnRegiser = new JButton("Register");
		btnRegiser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterScreen registerScreen = new RegisterScreen();
				registerScreen.setVisible(true);
				setVisible(false);
			

			}
		});
		btnRegiser.setBounds(209, 158, 117, 25);
		LoginPanel.add(btnRegiser);

	}
}
