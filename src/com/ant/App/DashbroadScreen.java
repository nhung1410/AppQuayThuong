package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.IntStream;

import com.ant.App.ListEmployeeScreen;
import com.ant.App.LoginScreen;
import com.ant.App.PrizeScreen;
import com.ant.Util.SqliteConnection;
import com.ant.entities.DetailReward;
import com.ant.entities.Reward;
import com.ant.entities.User;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.xmlbeans.XmlCursor.TokenType;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class DashbroadScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtM;
	private JTextField txtN;
	private JTextField txtV;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	String _turn;
	private JTextField txtClazz;

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
					DashbroadScreen frame = new DashbroadScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void ActionStartBtn() {

		Vector<Object> listEmp = new Vector<>();
		listEmp = ListEmployeeScreen.getData();

		try {

			Random ran = new Random();
			if (listEmp != null) {

				int num = ran.nextInt(listEmp.size());

				String[] arr = listEmp.get(num).toString().split(",");

				String t1 = String.valueOf(Integer.parseInt(arr[1].trim()) / 1000);
				String t2 = String.valueOf((Integer.parseInt(arr[1].trim()) / 100) % 10);
				String t3 = String.valueOf((Integer.parseInt(arr[1].trim()) % 100) / 10);
				String t4 = String.valueOf(Integer.parseInt(arr[1].trim()) % 10);
				txt1.setText(t1);
				txt2.setText(t2);
				txt3.setText(t3);
				txt4.setText(t4);
				updateRewardData();

			} else {
				JOptionPane.showMessageDialog(null, "Chọn thêm nhân viên để quay thưởng !");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void showRewardData(ArrayList<Reward> reList) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = SqliteConnection.dbConnector();
			pst = conn.prepareStatement("SELECT * from reward where  turn > t");
			rs = pst.executeQuery();

			while(rs.next()) {
				int rewardId = rs.getInt(1);
				String name = rs.getString(2);
				int turn = rs.getInt(3);
				String prize = rs.getString(4);
				int t = rs.getInt(5);

				Reward reward = new Reward();
				reward.setId(rewardId);
				reward.setClazz(name);
				reward.setTurns(turn);
				reward.setPrize(prize);
				reward.setT(t);
				reList.add(reward);
			} 

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}

	private void updateRewardData() {
		Connection conn = null;
		PreparedStatement statement = null;
		ArrayList<Reward> reList = new ArrayList<Reward>();
		Reward re = new Reward();

		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("update reward set t =?  where id = ?");
			showRewardData(reList);
			while(!reList.isEmpty()) {

				statement.setInt(1, reList.get(0).getT() + 1);
				statement.setInt(2, reList.get(0).getId());
				statement.execute();
				txtClazz.setText(reList.get(0).getClazz().toString());
				txtTurn.setText(String.valueOf(reList.get(0).getTurns() - reList.get(0).getT() - 1));
			} 

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {

//				pst.close();
				statement.close();
				conn.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}

	private JTable table;
	private JTextField txtTurn;

	/**
	 * Create the frame.
	 */
	public DashbroadScreen() {
//		setUser(_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 413, 30);
		contentPane.add(menuBar);

		JMenuItem mnEmployee = new JMenuItem("Employee");
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		mnDb.setBackground(SystemColor.activeCaption);
		menuBar.add(mnDb);

		JMenuItem mnProfile = new JMenuItem("Profile");
		menuBar.add(mnProfile);

		JPanel panel = new JPanel();
		panel.setBounds(12, 57, 958, 567);
		contentPane.add(panel);
		panel.setLayout(null);
		txtClazz = new JTextField();
		txtClazz.setForeground(Color.RED);
		txtClazz.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtClazz.setEditable(false);
		txtClazz.setBounds(244, 275, 136, 30);
		panel.add(txtClazz);
		txtClazz.setColumns(10);

		txtTurn = new JTextField();
		txtTurn.setForeground(Color.BLUE);
		txtTurn.setEditable(false);
		txtTurn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTurn.setColumns(10);
		txtTurn.setBounds(244, 318, 136, 30);
		panel.add(txtTurn);

		ArrayList<Reward> reList = new ArrayList<Reward>();
		showRewardData(reList);
		while(!reList.isEmpty()) {
		txtClazz.setText(reList.get(0).getClazz().toString());
		txtTurn.setText(String.valueOf(reList.get(0).getTurns() - reList.get(0).getT()));
		}
		
		txtM = new JTextField();
		txtM.setHorizontalAlignment(SwingConstants.CENTER);
		txtM.setText("M");
		txtM.setFont(new Font("Dialog", Font.BOLD, 20));
		txtM.setEditable(false);
		txtM.setBounds(65, 115, 50, 60);
		panel.add(txtM);
		txtM.setColumns(10);

		txtN = new JTextField();
		txtN.setFont(new Font("Dialog", Font.BOLD, 20));
		txtN.setText("N");
		txtN.setHorizontalAlignment(SwingConstants.CENTER);
		txtN.setEditable(false);
		txtN.setColumns(10);
		txtN.setBounds(127, 115, 50, 60);
		panel.add(txtN);

		txtV = new JTextField();
		txtV.setFont(new Font("Dialog", Font.BOLD, 20));
		txtV.setHorizontalAlignment(SwingConstants.CENTER);
		txtV.setText("V");
		txtV.setEditable(false);
		txtV.setColumns(10);
		txtV.setBounds(189, 115, 50, 60);
		panel.add(txtV);

		txt1 = new JTextField();
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setFont(new Font("Dialog", Font.BOLD, 20));
		txt1.setEditable(false);
		txt1.setColumns(10);
		txt1.setBounds(251, 115, 50, 60);
		panel.add(txt1);

		txt2 = new JTextField();
		txt2.setFont(new Font("Dialog", Font.BOLD, 20));
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setEditable(false);
		txt2.setColumns(10);
		txt2.setBounds(313, 115, 50, 60);
		panel.add(txt2);

		txt3 = new JTextField();
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setFont(new Font("Dialog", Font.BOLD, 20));
		txt3.setEditable(false);
		txt3.setColumns(10);
		txt3.setBounds(374, 115, 50, 60);
		panel.add(txt3);

		txt4 = new JTextField();
		txt4.setHorizontalAlignment(SwingConstants.CENTER);
		txt4.setFont(new Font("Dialog", Font.BOLD, 20));
		txt4.setEditable(false);
		txt4.setColumns(10);
		txt4.setBounds(436, 115, 50, 60);
		panel.add(txt4);
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					ActionStartBtn();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnStart.setFont(new Font("Dialog", Font.BOLD, 20));
		btnStart.setBounds(324, 462, 100, 50);
		panel.add(btnStart);

		JLabel lblNewLabel = new JLabel("Giải");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(92, 275, 112, 29);
		panel.add(lblNewLabel);

		JLabel lblSLnQuay = new JLabel("Số lần quay còn lại");
		lblSLnQuay.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSLnQuay.setBounds(92, 316, 130, 29);
		panel.add(lblSLnQuay);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(584, 40, 362, 515);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

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
		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen();
				employeeScreen.setVisible(true);
				setVisible(false);
			}
		});
//		mnPrize.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				PrizeScreen prizeScreen = new PrizeScreen(_user);
//				prizeScreen.setVisible(true);
//				setVisible(false);
//
//			}
//		});
//		mnProfile.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ProfileScreen profileScreen = new ProfileScreen(_user);
//				profileScreen.setVisible(true);
//				setVisible(false);
//
//			}
//		});
	}
}
