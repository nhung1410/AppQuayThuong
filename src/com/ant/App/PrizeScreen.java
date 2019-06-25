package com.ant.App;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ant.Util.ButtonRender;
import com.ant.Util.CheckNull;
import com.ant.Util.SqliteConnection;
import com.ant.Util.TurnsValidator;
import com.ant.entities.User;

import java.sql.*;
import java.awt.Color;

public class PrizeScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PrizeScreen frame = new PrizeScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private void Add(JTextField txtTurns, JLabel lblTurnsWarn, JEditorPane edtPrize, JLabel lblPrizeWarn,
			JComboBox cbbClass) {
		Connection conn = null;

		PreparedStatement pstInsert = null;
		try {
			CheckNull checkNull = new CheckNull();
			conn = SqliteConnection.dbConnector();
			String queryInsert = "insert into reward(name,turn,prize,t) values(?,?,?,?) ";
			pstInsert = conn.prepareStatement(queryInsert);
			if (checkNull.checkText(txtTurns.getText(), lblTurnsWarn, "turns")
					&& checkNull.checkText(edtPrize.getText(), lblPrizeWarn, "prize")) {
				TurnsValidator validator = new TurnsValidator();
				if (validator.Validate(txtTurns.getText())) {

				pstInsert.setString(1, cbbClass.getSelectedItem().toString());
				pstInsert.setInt(2, Integer.parseInt(txtTurns.getText()));
				pstInsert.setString(3, edtPrize.getText());
				pstInsert.setInt(4, 0);
				pstInsert.executeUpdate();
				}
				 else {
						lblTurnsWarn.setText("Enter number");
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstInsert.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void updateDataReward(int id, String name, int turn, String prize) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("update reward set name = ? , turn = ?, prize =? where id = ?");
			statement.setString(1, name);
			statement.setInt(2, turn);
			statement.setString(3, prize);
			statement.setInt(4, id);

			statement.executeUpdate();
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

	private void showDataInTable(DefaultTableModel model) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("SELECT * from reward");
			res = statement.executeQuery();
			while (res.next()) {

				String id = res.getString("id");
				String name = res.getString("name");
				String turn = res.getString("turn");
				String prize = res.getString("prize");
				String t = res.getString("t");
				Vector<Object> row = new Vector<Object>();
				row.add(id);
				row.add(name);
				row.add(turn);
				row.add(prize);
				row.add(t);

				ButtonRender buttonRender = new ButtonRender();
				table.getColumn("Action").setCellRenderer(buttonRender);

				model.addRow(row);
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

	/**
	 * Create the frame.
	 * 
	 */
	public PrizeScreen(User _user) {
		setUser(_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 370, 30);
		contentPane.add(menuBar);

		JMenuItem mnEmployee = new JMenuItem("Employee");
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		mnPrize.setBackground(SystemColor.activeCaption);
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		menuBar.add(mnDb);

		JMenuItem mnProfile = new JMenuItem("Profile");
		menuBar.add(mnProfile);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(48, 72, 884, 267);
		contentPane.add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã giải thưởng");
		model.addColumn("Giải");
		model.addColumn("Lượt quay");
		model.addColumn("Phần thưởng");
		model.addColumn("Số lượt đã quay");
		model.addColumn("Action");
		table.setModel(model);

		JPanel editPanel = new JPanel();
		editPanel.setBounds(48, 367, 884, 250);
		contentPane.add(editPanel);
		editPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Giải");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(144, 14, 55, 30);
		editPanel.add(lblNewLabel);

		JLabel lblTurns = new JLabel("Số lượt quay");
		lblTurns.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTurns.setBounds(144, 57, 88, 30);
		editPanel.add(lblTurns);

		JLabel lblPrize = new JLabel("Phần thưởng");
		lblPrize.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrize.setBounds(144, 121, 97, 30);
		editPanel.add(lblPrize);

		String[] item = { "Giải ba", "Giải nhì", "Giải nhất" };

		JComboBox cbbClass = new JComboBox(item);
		cbbClass.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbbClass.setBounds(253, 15, 190, 30);
		editPanel.add(cbbClass);

		JTextField txtTurns = new JTextField();
		txtTurns.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTurns.setBounds(253, 58, 190, 30);
		editPanel.add(txtTurns);
		txtTurns.setColumns(10);

		JLabel lblTurnsWarn = new JLabel("");
		lblTurnsWarn.setForeground(Color.RED);
		lblTurnsWarn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTurnsWarn.setBounds(253, 90, 355, 25);
		editPanel.add(lblTurnsWarn);
		
		JEditorPane edtPrize = new JEditorPane();
		edtPrize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edtPrize.setBounds(253, 118, 293, 119);
		editPanel.add(edtPrize);

		JLabel lblPrizeWarn = new JLabel("");
		lblPrizeWarn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPrizeWarn.setForeground(Color.RED);
		lblPrizeWarn.setBounds(558, 196, 304, 25);
		editPanel.add(lblPrizeWarn);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				int selectedColumn = table.getSelectedColumn();
				int columnCount = model.getColumnCount();
				for (int column = 0; column < columnCount; column++) {
					if ("Giải".equals(table.getColumnName(column))) {
						cbbClass.setSelectedItem(table.getValueAt(selectedRow, column).toString());

					} else if ("Lượt quay".equals(table.getColumnName(column))) {
						txtTurns.setText(table.getValueAt(selectedRow, column).toString());
					} else if ("Phần thưởng".equals(table.getColumnName(column))) {
						edtPrize.setText(table.getValueAt(selectedRow, column).toString());

					} else {

					}

				}

			}
		});

		showDataInTable(model);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Connection conn = null;
				PreparedStatement statement = null;
				ResultSet res = null;
				try {
					Add(txtTurns, lblTurnsWarn, edtPrize, lblPrizeWarn, cbbClass);
					conn = SqliteConnection.dbConnector();
					statement = conn.prepareStatement("SELECT * from reward where id = (select max(id) from reward)");
					res = statement.executeQuery();
					CheckNull checkNull = new CheckNull();
					if (checkNull.checkText(txtTurns.getText(), lblTurnsWarn, "turn")
							&& checkNull.checkText(edtPrize.getText(), lblPrizeWarn, "prize")) {

						while (res.next()) {
							TurnsValidator validator = new TurnsValidator();
							if (validator.Validate(txtTurns.getText())) {
								String id = res.getString("id");
								String name = res.getString("name");
								String turn = res.getString("turn");
								String prize = res.getString("prize");
								String t = res.getString("t");
								Vector<Object> row = new Vector<Object>();
								row.add(id);
								row.add(name);
								row.add(turn);
								row.add(prize);
								row.add(t);

								ButtonRender buttonRender = new ButtonRender();
								table.getColumn("Action").setCellRenderer(buttonRender);

								model.addRow(row);
								table.setModel(model);
								table.setVisible(true);
								contentPane.updateUI();
								txtTurns.setText("");
								edtPrize.setText("");
							} else {
								lblTurnsWarn.setText("Enter number");
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
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		btnAdd.setBounds(650, 57, 98, 30);
		editPanel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					CheckNull checkNull = new CheckNull();
					if (checkNull.checkText(txtTurns.getText(), lblTurnsWarn, "turn")
							&& checkNull.checkText(edtPrize.getText(), lblPrizeWarn, "prize")) {
						TurnsValidator validator = new TurnsValidator();
						if (validator.Validate(txtTurns.getText())) {
							String id = "";
							int selectedRow = table.getSelectedRow();
							int columnCount = model.getColumnCount();
							for (int column = 0; column < columnCount; column++) {
								if ("Giải".equals(table.getColumnName(column))) {
									table.setValueAt(cbbClass.getSelectedItem(), selectedRow, column);

								} else if ("Lượt quay".equals(table.getColumnName(column))) {
									table.setValueAt(txtTurns.getText(), selectedRow, column);
								} else if ("Phần thưởng".equals(table.getColumnName(column))) {
									table.setValueAt(edtPrize.getText(), selectedRow, column);
								} else if ("Mã giải thưởng".equals(table.getColumnName(column))) {
									id = table.getValueAt(selectedRow, column).toString();
								} else {

								}
							}
							int _id = Integer.parseInt(id);
							int _turn = Integer.parseInt(txtTurns.getText());
							updateDataReward(_id, cbbClass.getSelectedItem().toString(), _turn, edtPrize.getText());
						} else {
							lblTurnsWarn.setText("Enter number");
						}
					}
				}

				catch (Exception e) {
					e.printStackTrace();
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
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogout.setBounds(884, 0, 98, 35);
		contentPane.add(btnLogout);
		btnUpdate.setBounds(650, 141, 98, 30);
		editPanel.add(btnUpdate);

		
		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen(_user);
				employeeScreen.setVisible(true);

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
