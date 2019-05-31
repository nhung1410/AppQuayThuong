package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.PortableInterceptor.INACTIVE;

import com.ant.entities.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.JToggleButton;

public class DashBroadScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtLose;
	private JTextField txtWin;
	private JTextField txtGirf;
	private User user;

	private User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBroadScreen frame = new DashBroadScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DashBroadScreen() {

	}

	/**
	 * Create the frame.
	 */

	public DashBroadScreen(User _user) {
		setUser(_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JMenu profiel = new JMenu();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 172, 26);
		contentPane.add(menuBar);

		JMenuItem mnProfile = new JMenuItem("Profile");
		mnProfile.setBackground(SystemColor.menu);
		menuBar.add(mnProfile);

		JMenuItem mnSpin = new JMenuItem("Spin reward");
		mnSpin.setBackground(SystemColor.menu);
		menuBar.add(mnSpin);

		JPanel spinPanel = new JPanel();
		spinPanel.setBounds(10, 50, 954, 590);
		contentPane.add(spinPanel);
		spinPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(66, 33, 830, 176);
		spinPanel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(606, 26, 114, 70);
		panel_1.add(btnStart);
		btnStart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		textField = new JTextField();
		textField.setText("1");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.BOLD, 40));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(109, 39, 392, 59);
		panel_1.add(textField);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(23, 38, 62, 59);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Giải:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(34, 229, 46, 35);
		panel_1.add(lblNewLabel_1);

		JPanel conPanel = new JPanel();
		conPanel.setBounds(66, 276, 830, 275);
		spinPanel.add(conPanel);
		conPanel.setLayout(null);
		conPanel.setVisible(false);

		txtWin = new JTextField();
		txtWin.setBounds(72, 48, 692, 66);
		conPanel.add(txtWin);
		txtWin.setText("Chúc mừng");
		txtWin.setHorizontalAlignment(SwingConstants.CENTER);
		txtWin.setForeground(Color.RED);
		txtWin.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtWin.setEditable(false);
		txtWin.setColumns(10);

		txtGirf = new JTextField();
		txtGirf.setBounds(72, 166, 692, 66);
		conPanel.add(txtGirf);
		txtGirf.setHorizontalAlignment(SwingConstants.CENTER);
		txtGirf.setForeground(Color.RED);
		txtGirf.setFont(new Font("Dialog", Font.PLAIN, 28));
		txtGirf.setEditable(false);
		txtGirf.setColumns(10);

		JPanel losePanel = new JPanel();
		losePanel.setBounds(66, 276, 830, 275);
		spinPanel.add(losePanel);
		losePanel.setLayout(null);
		losePanel.setVisible(false);

		txtLose = new JTextField();
		txtLose.setForeground(Color.BLUE);
		txtLose.setHorizontalAlignment(SwingConstants.CENTER);
		txtLose.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtLose.setEditable(false);
		txtLose.setText("CHÚC BẠN MAY MẮN LẦN SAU");
		txtLose.setBounds(36, 36, 782, 78);
		losePanel.add(txtLose);
		txtLose.setColumns(10);

		JPanel profilePanel = new JPanel();
		profilePanel.setBounds(159, 50, 647, 590);
		contentPane.add(profilePanel);
		profilePanel.setVisible(false);

		JLabel lblID = new JLabel("ID: ");
		profiel.add(lblID);
		profilePanel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(23, 65, 98, 30);
		profilePanel.add(lblName);

		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtName.setBounds(238, 65, 322, 30);
		profilePanel.add(txtName);

		JLabel lblNamewarn = new JLabel("");
		lblNamewarn.setForeground(Color.RED);
		lblNamewarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNamewarn.setBounds(171, 91, 359, 30);
		profilePanel.add(lblNamewarn);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddress.setBounds(23, 140, 98, 30);
		profilePanel.add(lblAddress);

		JTextField txtAddress = new JTextField();
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(238, 140, 322, 30);
		profilePanel.add(txtAddress);

		JLabel lblAddressWarn = new JLabel("");
		lblAddressWarn.setForeground(Color.RED);
		lblAddressWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblAddressWarn.setBounds(161, 182, 369, 30);
		profilePanel.add(lblAddressWarn);

		JLabel lblBirthday = new JLabel("Date of birth");
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBirthday.setBounds(23, 224, 106, 30);
		profilePanel.add(lblBirthday);
		JDateChooser txtbirthDate = new JDateChooser();

		txtbirthDate.setBounds(238, 224, 237, 30);
		profilePanel.add(txtbirthDate);
		txtbirthDate.setDateFormatString("dd/MM/yyyy");

		JLabel lblBirthWarn = new JLabel("");
		lblBirthWarn.setForeground(Color.RED);
		lblBirthWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBirthWarn.setBounds(161, 284, 369, 30);
		profilePanel.add(lblBirthWarn);

		JLabel lblPassword = new JLabel("Your password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setBounds(23, 300, 162, 30);
		profilePanel.add(lblPassword);

		JTextField txtPassword = new JPasswordField();
		txtPassword.setBounds(238, 300, 322, 30);
		profilePanel.add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setEditable(false);
		
		JButton btnEp = new JButton("Edit");
		btnEp.setBounds(575, 300, 55, 30);
		profilePanel.add(btnEp);

		JLabel lblPassWarn = new JLabel("");
		lblPassWarn.setForeground(Color.RED);
		lblPassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPassWarn.setBounds(238, 347, 322, 30);
		profilePanel.add(lblPassWarn);

		JLabel lblRePassword = new JLabel("Confirm password");
		lblRePassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRePassword.setBounds(23, 397, 174, 30);
		profilePanel.add(lblRePassword);

		JTextField txtRePassword = new JPasswordField();
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(238, 397, 322, 30);
		profilePanel.add(txtRePassword);
		txtRePassword.setEditable(false);

		JLabel lblRePassWarn = new JLabel("");
		lblRePassWarn.setForeground(Color.RED);
		lblRePassWarn.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblRePassWarn.setBounds(238, 439, 322, 30);
		profilePanel.add(lblRePassWarn);

		StringBuilder helloLable = new StringBuilder();
		JLabel lblHello = new JLabel("Welcome, ");
		lblHello.setBounds(449, 0, 373, 26);
		contentPane.add(lblHello);

		if (getUser() != null) {
			helloLable.append(getUser().getName());
			lblHello.setText(helloLable.toString());
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
		btnSubmit.setBounds(219, 495, 157, 44);
		profilePanel.add(btnSubmit);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String address = txtAddress.getText();
				String pass = txtPassword.getText();
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dateOfBirth = df.format(txtbirthDate.getDate());
				File f = new File("Login.txt");
				ArrayList<String> list = new ArrayList<String>();
				String str = "";
				try {

					Scanner r = new Scanner(f);
					while ((str = r.nextLine()) != null) {
						System.out.println("start");
						String[] token = str.split(",");
						if (getUser().getId() == Integer.parseInt(token[0])) {

							System.out.println(str);

							list.add(token[0] + "," + token[1] + "," + pass + "," + name + "," + address + ","
									+ dateOfBirth);

							System.out.println(list);
						} else {
							list.add(str);
						}

					}

					r.close();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				try (PrintWriter pw = new PrintWriter(f)) {
					for (String s : list) {
						pw.println(s);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});

		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirm",
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
		helloLable.append(lblHello.getText());

		mnProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profilePanel.setVisible(true);
				spinPanel.setVisible(false);

			}
		});

		mnSpin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profilePanel.setVisible(false);
				spinPanel.setVisible(true);

			}
		});
	}

}
