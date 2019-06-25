package com.ant.App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ant.Util.CheckNull;
import com.ant.Util.PasswordValidator;
import com.ant.Util.SqliteConnection;
import com.ant.entities.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ProfileScreen extends JFrame {

	private JPanel contentPane;
	private User user;

	private User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	/**
	 * Create the frame.
	 */

	Connection conn;

	public ProfileScreen(User _user) {
		conn = SqliteConnection.dbConnector();
		setUser(_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JMenu profiel = new JMenu();

		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(159, 97, 647, 517);
		contentPane.add(profilePanel);

		JLabel lblID = new JLabel("ID: ");
		profiel.add(lblID);
		profilePanel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(23, 65, 98, 30);
		profilePanel.add(lblName);

		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtName.setBounds(173, 66, 322, 30);
		profilePanel.add(txtName);

		JLabel lblNamewarn = new JLabel("");
		lblNamewarn.setForeground(Color.RED);
		lblNamewarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNamewarn.setBounds(173, 96, 462, 30);
		profilePanel.add(lblNamewarn);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddress.setBounds(23, 138, 98, 30);
		profilePanel.add(lblAddress);

		JTextField txtAddress = new JTextField();
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(173, 139, 341, 30);
		profilePanel.add(txtAddress);

		JLabel lblAddressWarn = new JLabel("");
		lblAddressWarn.setForeground(Color.RED);
		lblAddressWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblAddressWarn.setBounds(173, 171, 434, 30);
		profilePanel.add(lblAddressWarn);

		JLabel lblBirthday = new JLabel("Date of birth");
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBirthday.setBounds(23, 214, 106, 30);
		profilePanel.add(lblBirthday);
		JDateChooser txtbirthDate = new JDateChooser();

		txtbirthDate.setBounds(173, 214, 256, 30);
		profilePanel.add(txtbirthDate);
		txtbirthDate.setDateFormatString("dd/MM/yyyy");

		JLabel lblBirthWarn = new JLabel("");
		lblBirthWarn.setForeground(Color.RED);
		lblBirthWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBirthWarn.setBounds(173, 244, 434, 30);
		profilePanel.add(lblBirthWarn);

		JLabel lblPassword = new JLabel("Your password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setBounds(23, 288, 140, 30);
		profilePanel.add(lblPassword);

		JTextField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(173, 289, 341, 30);
		profilePanel.add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setEditable(false);

		JButton btnEp = new JButton("Edit");
		btnEp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEp.setBounds(545, 288, 72, 30);
		profilePanel.add(btnEp);

		JLabel lblPassWarn = new JLabel("");
		lblPassWarn.setForeground(Color.RED);
		lblPassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPassWarn.setBounds(173, 319, 471, 30);
		profilePanel.add(lblPassWarn);
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
					lblPassWarn.setText("Password includes 8-16 characters & periods");
					txtPassword.setText("");
				}
				
			}
		});

		JLabel lblRePassword = new JLabel("Confirm password");
		lblRePassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRePassword.setBounds(23, 361, 140, 30);
		profilePanel.add(lblRePassword);

		JTextField txtRePassword = new JPasswordField();
		txtRePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(173, 362, 341, 30);
		profilePanel.add(txtRePassword);
		txtRePassword.setEditable(false);

		JLabel lblRePassWarn = new JLabel("");
		lblRePassWarn.setForeground(Color.RED);
		lblRePassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblRePassWarn.setBounds(173, 394, 462, 30);
		profilePanel.add(lblRePassWarn);

		StringBuilder helloLable = new StringBuilder();

		if (getUser() != null) {
			helloLable.append(getUser().getName());
			txtName.setText(getUser().getName());
			txtAddress.setText(getUser().getAddress());
			txtPassword.setText(getUser().getPassword());
			txtRePassword.setText(getUser().getPassword());

		}

		btnEp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtPassword.setEditable(true);
				txtRePassword.setEditable(true);

			}
		});

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSubmit.setBounds(272, 445, 157, 44);
		profilePanel.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement pstUpdate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					java.util.Date d = txtbirthDate.getDate();

					String sd;
					if (d == null) {
						sd = "";
					} else {
						sd = sdf.format(d);
					}
					CheckNull text = new CheckNull();
					conn = SqliteConnection.dbConnector();
					pstUpdate = conn.prepareStatement(
							"UPDATE users SET name = ?, address = ?, age = ?, password = ? WHERE id = ?");
					if (text.checkText(txtName.getText(), lblNamewarn, lblName.getText())
							&& text.checkText(txtAddress.getText(), lblAddressWarn, lblAddress.getText())
							&& text.checkText(txtPassword.getText(), lblPassWarn, lblPassword.getText())
							&& text.checkText(txtRePassword.getText(), lblRePassWarn,lblRePassword.getText())
							&& text.checkText(sd, lblBirthWarn,lblBirthday.getText())) {
						PasswordValidator passValidator = new PasswordValidator();
						if (passValidator.validate(txtPassword.getText())
								&& txtPassword.getText().equals(txtRePassword.getText())) {

							Calendar c = Calendar.getInstance();
							int year = c.get(Calendar.YEAR);
							@SuppressWarnings("deprecation")
							int yearOfBirth = txtbirthDate.getDate().getYear() + 1900;
							int age = year - yearOfBirth;
							pstUpdate.setString(1, txtName.getText());
							pstUpdate.setString(2, txtAddress.getText());
							pstUpdate.setInt(3, age);
							pstUpdate.setString(4, txtPassword.getText());
							pstUpdate.setInt(5, getUser().getId());
							pstUpdate.execute();
							JOptionPane.showMessageDialog(null, "Edit profile successfully!");

							
							lblNamewarn.setText("");
							lblAddressWarn.setText("");
							lblBirthWarn.setText("");
							lblPassWarn.setText("");
							lblRePassWarn.setText("");
							
							txtPassword.setEditable(false);
							txtRePassword.setEditable(false);
							

						} else if (!passValidator.validate(txtPassword.getText())) {
							lblPassWarn.setText("Password includes 8-16 characters & periods");

						} else if (!txtPassword.getText().equals(txtRePassword.getText())) {
							lblRePassWarn.setText("Like password");
						}
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} finally {
					try {
						pstUpdate.close();
						conn.close();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}

			}

		});

		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						setVisible(false);
						LoginScreen loginScreen = new LoginScreen();
						loginScreen.setVisible(true);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLogout.setBounds(884, 0, 98, 26);
		contentPane.add(btnLogout);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 493, 26);
		contentPane.add(menuBar);

		JMenuItem mnEmployee = new JMenuItem("Employee");
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		menuBar.add(mnDb);

		JMenuItem mnProfile = new JMenuItem("Profile");
		menuBar.add(mnProfile);

		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen(_user);
				employeeScreen.setVisible(true);
				setVisible(false);
			}
		});
		mnPrize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrizeScreen prizeScreen = new PrizeScreen(_user);
				prizeScreen.setVisible(true);
				setVisible(false);

			}
		});
		mnDb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DashbroadScreen dashbroadScreen = new DashbroadScreen(_user);
				dashbroadScreen.setVisible(true);
				setVisible(false);
			}
		});
	}
}
