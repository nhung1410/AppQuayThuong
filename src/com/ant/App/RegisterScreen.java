package com.ant.App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.ant.Util.UsernameValidator;
import com.ant.Util.CheckNull;
import com.ant.Util.PasswordValidator;
import com.ant.Util.SqliteConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.SystemColor;

public class RegisterScreen extends JFrame {

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

	private Boolean checkUserName(String username, JLabel lbl) {
		Boolean check = true;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("SELECT * FROM users");
			res = statement.executeQuery();
			while (res.next()) {
				if (username.isEmpty()) {
					lbl.setText("Enter blank");
					check = false;

				} else {
					if (username.equals(res.getString("username"))) {
						lbl.setText("username already exist!");
						check = false;
						System.out.println();
					} else {
						check = true;
					}
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	/**
	 * Create the frame.
	 */
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 105, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBackground(SystemColor.activeCaption);
		lblRegister.setForeground(new Color(255, 255, 255));
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegister.setBounds(347, 13, 74, 29);
		contentPane.add(lblRegister);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 83, 782, 530);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setForeground(new Color(210, 105, 30));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserName.setBounds(134, 28, 73, 30);
		panel.add(lblUserName);

		JTextField txtUserName = new JTextField();
		txtUserName.setForeground(new Color(0, 0, 128));
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setBounds(277, 29, 322, 30);
		panel.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblUserNamewarn = new JLabel("");
		lblUserNamewarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblUserNamewarn.setForeground(Color.RED);
		lblUserNamewarn.setBounds(277, 59, 453, 25);
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
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					lblUserNamewarn.setText("You can use letters(a-z, A_Z), 4-12 characters & periods");
					txtUserName.setText("");
				}
			}
		});

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(210, 105, 30));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(134, 96, 56, 30);
		panel.add(lblName);

		JTextField txtName = new JTextField();
		txtName.setForeground(new Color(0, 0, 128));
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setBounds(277, 97, 322, 30);
		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNamewarn = new JLabel("");
		lblNamewarn.setForeground(Color.RED);
		lblNamewarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblNamewarn.setBounds(277, 129, 453, 25);
		panel.add(lblNamewarn);

		JLabel lblPassword = new JLabel("Create password");
		lblPassword.setForeground(new Color(210, 105, 30));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(134, 159, 117, 30);
		panel.add(lblPassword);

		JTextField txtPassword = new JPasswordField();
		txtPassword.setForeground(new Color(0, 0, 128));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(277, 160, 322, 30);
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblPassWarn = new JLabel("");
		lblPassWarn.setForeground(Color.RED);
		lblPassWarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblPassWarn.setBounds(277, 188, 453, 25);
		panel.add(lblPassWarn);

		txtPassword.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					lblPassWarn.setText("You can use letters(a-z, A-Z, 0-9), 8-16 characters & periods");
					txtPassword.setText("");
				}
			}
		});

		JLabel lblRePassword = new JLabel("Confirm password");
		lblRePassword.setForeground(new Color(210, 105, 30));
		lblRePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRePassword.setBounds(130, 218, 135, 30);
		panel.add(lblRePassword);

		JTextField txtRePassword = new JPasswordField();
		txtRePassword.setForeground(new Color(0, 0, 128));
		txtRePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(277, 219, 322, 30);
		panel.add(txtRePassword);

		JLabel lblRePassWarn = new JLabel("");
		lblRePassWarn.setForeground(Color.RED);
		lblRePassWarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblRePassWarn.setBounds(277, 251, 453, 25);
		panel.add(lblRePassWarn);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(210, 105, 30));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(130, 281, 61, 30);
		panel.add(lblAddress);

		JTextField txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(277, 282, 322, 30);
		panel.add(txtAddress);

		JLabel lblAddressWarn = new JLabel("");
		lblAddressWarn.setForeground(Color.RED);
		lblAddressWarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblAddressWarn.setBounds(277, 314, 322, 25);
		panel.add(lblAddressWarn);

		JLabel lblBirthday = new JLabel("Date of birth");
		lblBirthday.setForeground(new Color(210, 105, 30));
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBirthday.setBounds(130, 346, 88, 30);
		panel.add(lblBirthday);
		JDateChooser txtbirthDate = new JDateChooser();

		JTextFieldDateEditor editor = (JTextFieldDateEditor) txtbirthDate.getDateEditor();
		editor.setEditable(false);
		txtbirthDate.setToolTipText("");
		txtbirthDate.setForeground(new Color(0, 0, 128));
		txtbirthDate.setBounds(277, 346, 198, 30);
		panel.add(txtbirthDate);
		txtbirthDate.setDateFormatString("dd/MM/yyyy");

		JLabel lblBirthWarn = new JLabel("");
		lblBirthWarn.setForeground(Color.RED);
		lblBirthWarn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblBirthWarn.setBounds(277, 376, 322, 25);
		panel.add(lblBirthWarn);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(210, 105, 30));
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement pstInsert = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				try {
					java.util.Date d = txtbirthDate.getDate();

					String sd;
					if (d == null) {
						sd = "";
					} else {
						sd = sdf.format(d);

					}

					CheckNull checkNull = new CheckNull();
					conn = SqliteConnection.dbConnector();
					pstInsert = conn.prepareStatement(
							"insert into users(username,name,password,address,age) values(?,?,?,?,?)");

					if (checkUserName(txtUserName.getText(), lblUserNamewarn)
							&& checkNull.checkText(txtPassword.getText(), lblPassWarn)
							&& checkNull.checkText(txtName.getText(), lblNamewarn)
							&& checkNull.checkText(txtRePassword.getText(), lblRePassWarn)
							&& checkNull.checkText(txtAddress.getText(), lblAddressWarn)
							&& checkNull.checkText(sd, lblBirthWarn)) {

						UsernameValidator validator = new UsernameValidator();
						PasswordValidator passValidator = new PasswordValidator();
						if (validator.Validate(txtUserName.getText()) && passValidator.validate(txtPassword.getText())
								&& txtPassword.getText().equals(txtRePassword.getText())) {

							pstInsert.setString(1, txtUserName.getText());
							pstInsert.setString(2, txtName.getText());
							pstInsert.setString(3, txtPassword.getText());
							pstInsert.setString(4, txtAddress.getText());
							Calendar c = Calendar.getInstance();
							int year = c.get(Calendar.YEAR);
							int yearOfBirth = txtbirthDate.getDate().getYear() + 1900;
							int age = year - yearOfBirth;
							pstInsert.setInt(5, age);
							pstInsert.executeUpdate();
							JOptionPane.showMessageDialog(null, "Create a new account successfully");
							LoginScreen loginScreen = new LoginScreen();
							loginScreen.setVisible(true);
							setVisible(false);

						} else if (!validator.Validate(txtUserName.getText())) {
							lblUserNamewarn.setText("You can use letters(a-z, A_Z), 4-12 characters & periods");
						} else if (!passValidator.validate(txtPassword.getText())) {
							lblPassWarn.setText("You can use letters(a-z, A-Z, 0-9), 8-16 characters & periods");
						} else if (!txtPassword.getText().equals(txtRePassword.getText())) {
							lblRePassWarn.setText("Like password!");
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pstInsert.close();
						conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}

		});

		btnSubmit.setBounds(232, 447, 88, 35);
		panel.add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setBackground(new Color(210, 105, 30));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText("");
				txtName.setText("");
				txtPassword.setText("");
				txtRePassword.setText("");
				txtAddress.setText("");
				txtbirthDate.setDate(null);

			}
		});
		btnClear.setBounds(564, 447, 97, 35);
		panel.add(btnClear);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.setVisible(true);
			}
		});
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(210, 105, 30));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(12, 17, 74, 25);
		contentPane.add(btnBack);

	}
}
