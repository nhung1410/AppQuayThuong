package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.ant.Util.SqliteConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

	private void login(String usn, String pass) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			
			conn = SqliteConnection.dbConnector();
			String query = "select * from users where username=? and password=?";
			pst = conn.prepareStatement(query);
			pst.setString(1, usn);
			pst.setString(2, pass);
			rs = pst.executeQuery();
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
				DashbroadScreen ds = new DashbroadScreen(us);
				ds.setVisible(true);
				setVisible(false);
			} else {
				lblPasswarning.setText("Wrong username or password. Try again.");
			}

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * Create the frame.
	 */
	public LoginScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 396);
		LoginPanel = new JPanel();
		LoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPanel);
		LoginPanel.setLayout(null);

		JLabel lblLogin = new JLabel("Username");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(50, 66, 75, 30);
		LoginPanel.add(lblLogin);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(48, 123, 75, 30);
		LoginPanel.add(lblPassword);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setBounds(137, 60, 253, 30);
		LoginPanel.add(txtUserName);
		txtUserName.setColumns(10);

		lblUserWarning = new JLabel("");
		lblUserWarning.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblUserWarning.setForeground(Color.RED);
		lblUserWarning.setBounds(137, 91, 253, 25);
		LoginPanel.add(lblUserWarning);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(137, 124, 253, 30);
		LoginPanel.add(txtPassword);
		txtPassword.setColumns(10);

		lblPasswarning = new JLabel("");
		lblPasswarning.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPasswarning.setForeground(Color.RED);
		lblPasswarning.setBounds(137, 155, 253, 25);
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
					e.printStackTrace();
				}

			}
		});

		btnLogin.setBounds(137, 204, 75, 35);
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
		btnRegiser.setBounds(291, 204, 117, 35);
		LoginPanel.add(btnRegiser);

	}
}
