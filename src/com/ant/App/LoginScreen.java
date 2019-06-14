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
import javax.swing.JOptionPane;
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
	Connection conn = SqliteConnection.dbConnector();

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

	private void login(String usn, String pass) {
		try {
			String query = "select * from users where username=? and password=?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, usn);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			boolean flag = false;
			User us = new User();
			while (rs.next()) {
				us.setId(rs.getInt("id"));
				us.setUserName(rs.getString("username"));
				us.setName(rs.getString("name"));
				us.setAddress(rs.getString("address"));
				us.setPassword(rs.getString("password"));
				flag = true;
			}
			if (flag) {
				DashbroadScreen ds = new DashbroadScreen();
				ds.setVisible(true);
				setVisible(false);
			} else {
				lblPasswarning.setText("Wrong username or password. Try again.");
			}

			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

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
					if (!txtUserName.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

						login(txtUserName.getText(), txtPassword.getText());
						

					} else {
						if (txtUserName.getText().equals("")) {
							lblUserWarning.setText("Enter Username");
						} else {

							lblPasswarning.setText("Enter Password");
							lblUserWarning.setText("Wrong username. Try again.");
						}
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
