package com.ant.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.ant.App.ListEmployeeScreen;
import com.ant.App.LoginScreen;
import com.ant.Util.SqliteConnection;
import com.ant.Util.getExcelFileValue;
import com.ant.entities.Employee;
import com.ant.entities.Reward;
import com.ant.entities.User;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class DashbroadScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtM;
	private JTextField txtN;
	private JTextField txtV;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txtClazz;
	private boolean flag ;
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

	private Vector<Employee> selectEmployeeList() {
		Connection conn = null;
		PreparedStatement pstSelect = null;
		ResultSet res = null;
		getExcelFileValue reader = new getExcelFileValue();
		Vector<Employee> listEmp = reader.getData();

		try {

			conn = SqliteConnection.dbConnector();
			pstSelect = conn.prepareStatement("SELECT * FROM rewardDetail");
			res = pstSelect.executeQuery();
			while (res.next()) {
				for (int i = 0; i < listEmp.size(); i++) {

					if (listEmp.get(i).getMaNV().equals(res.getString("maNV"))) {
						listEmp.remove(i);

					}
				}

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				pstSelect.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listEmp;
	}

	private void ActionStartBtn() {

		Connection conn = null;
		PreparedStatement statement = null;
		Vector<Employee> listEmp = selectEmployeeList();
		ArrayList<Reward> reList = showRewardData();

		try {

			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("insert into rewardDetail (reward_id,maNV,nameNV) values (?,?,?) ");
			Random ran = new Random();
			if ((listEmp.size() > 0) && (reList.size() > 0)) {
				flag = true;
				int num = ran.nextInt(listEmp.size());

				Employee employee = new Employee();
				employee.setMaNV(listEmp.get(num).getMaNV());
				employee.setName(listEmp.get(num).getName());

				String t1 = String.valueOf(Integer.parseInt(employee.getMaNV()) / 1000);
				String t2 = String.valueOf((Integer.parseInt(employee.getMaNV()) / 100) % 10);
				String t3 = String.valueOf((Integer.parseInt(employee.getMaNV()) % 100) / 10);
				String t4 = String.valueOf(Integer.parseInt(employee.getMaNV()) % 10);
				txt1.setText(t1);
				txt2.setText(t2);
				txt3.setText(t3);
				txt4.setText(t4);

				if (reList.size() == sizeBandau) {

					statement.setInt(1, reList.get(0).getId());
					statement.setString(2, employee.getMaNV());
					statement.setString(3, employee.getName());
					statement.executeUpdate();

				} else {

				}
			} else if (listEmp.isEmpty() || reList.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lượt quay kết thúc!");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		} finally {
			try {

				statement.close();
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private void insertTable(JTable table, DefaultTableModel model) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		ArrayList<Reward> reList = showRewardData();
		Vector<Employee> listEmp = selectEmployeeList();
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement(
					"SELECT * FROM rewardDetail INNER JOIN reward ON rewardDetail.reward_id = reward.id ORDER BY id DESC LIMIT 1");
			res = statement.executeQuery();
			Vector<Object> row = new Vector<Object>();

			if (reList.size() > 0 && listEmp.size() >= 0) {

				while (res.next()) {
					if (flag) {
						if (reList.size() == sizeBandau) {
							String name = res.getString("name");
							String maNV = res.getString("maNV");
							String nameNV = res.getString("nameNV");

							row.add(table.getRowCount() + 1);
							row.add(name);
							row.add(maNV);
							row.add(nameNV);
							model.addRow(row);
							table.setModel(model);

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void showTable(JTable table, DefaultTableModel model) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement(
					"SELECT * FROM rewardDetail INNER JOIN reward ON rewardDetail.reward_id = reward.id");
			res = statement.executeQuery();
			int stt = table.getRowCount();

			while (res.next()) {
				stt++;
				String maNV = res.getString(3);
				String nameNV = res.getString(4);

				String name = res.getString(6);

				Vector<Object> row = new Vector<Object>();
				row.add(stt);
				row.add(name);
				row.add(maNV);
				row.add(nameNV);
				model.addRow(row);
				table.setModel(model);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ArrayList<Reward> showRewardData() {
		ArrayList<Reward> reList = new ArrayList<Reward>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = SqliteConnection.dbConnector();
			pst = conn.prepareStatement("SELECT * from reward where  turn > t");
			rs = pst.executeQuery();

			while (rs.next()) {
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
		return reList;
	}

	private void updateRewardData() {

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("update reward set t =?  where id = ?");

			ArrayList<Reward> reList = showRewardData();
			Vector<Employee> listEmp = selectEmployeeList();

			if (flag) {
				if (reList.size() > 0 && listEmp.size() >= 0) {
					if (reList.size() == 0 || listEmp.size() == 0) {

						flag = false;
					}

					if (reList.size() == sizeBandau) {
						statement.setInt(1, reList.get(0).getT() + 1);
						statement.setInt(2, reList.get(0).getId());
						statement.execute();
						txtClazz.setText(reList.get(0).getClazz().toString());
						txtTurn.setText(String.valueOf(reList.get(0).getTurns() - reList.get(0).getT() - 1));

					} else if (reList.size() < sizeBandau) {
						sizeBandau = reList.size();
						txtClazz.setText(reList.get(0).getClazz().toString());
						txtTurn.setText(String.valueOf(reList.get(0).getTurns() - reList.get(0).getT()));

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				statement.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private JTextField txtTurn;
	public int sizeBandau = 0;

	/**
	 * Create the frame.
	 */
	public DashbroadScreen(User _user) {
		setUser(_user);
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

		ArrayList<Reward> reList = showRewardData();
		sizeBandau = reList.size();

		if (reList.size() > 0) {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(547, 40, 399, 515);
		panel.add(scrollPane);
		JTable table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Giải");
		model.addColumn("Mã nhân viên");
		model.addColumn("Tên nhân viên");

		table.setModel(model);
		showTable(table, model);
		scrollPane.setViewportView(table);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					ActionStartBtn();
					insertTable(table, model);
					updateRewardData();

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
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogout.setBounds(884, 0, 98, 35);
		contentPane.add(btnLogout);
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
		mnProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileScreen profileScreen = new ProfileScreen(_user);
				profileScreen.setVisible(true);
				setVisible(false);

			}
		});
	}
}
