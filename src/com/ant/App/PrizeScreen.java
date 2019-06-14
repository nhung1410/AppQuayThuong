package com.ant.App;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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


import org.apache.commons.io.FileUtils;


import com.ant.Util.ButtonRender;
import com.ant.Util.SqliteConnection;
import com.ant.entities.Reward;
import com.ant.entities.User;


import java.sql.*;

public class PrizeScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTurns;
	private JComboBox cbbClass;
	private JEditorPane edtPrize;
	private User user;
	private Action action;

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
					PrizeScreen frame = new PrizeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void Add() {

		try {
			Connection conn = SqliteConnection.dbConnector();
			String queryInsert = "insert into reward(name,turn,prize,t) values(?,?,?,?) ";
			PreparedStatement pstInsert = conn.prepareStatement(queryInsert);
			pstInsert.setString(1, cbbClass.getSelectedItem().toString());
			pstInsert.setInt(2, Integer.parseInt(txtTurns.getText()));
			pstInsert.setString(3, edtPrize.getText());
			pstInsert.setInt(4, 0);
			int executeUpdate = pstInsert.executeUpdate();
			pstInsert.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
	
	private void updateDataReward(int id, String name, int turn, String prize) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			Connection connector = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("update reward set name = ? , turn = ?, prize =? where id = ?");
			statement.setString(1, name);
			statement.setInt(2, turn);
			statement.setString(3, prize);
			statement.setInt(4, id);
			statement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
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
				
				String id = res.getString(1);
				String name = res.getString(2);
				String turn = res.getString(3);
				String prize = res.getString(4);
				String t = res.getString(5);
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
			JOptionPane.showMessageDialog(null, e.getMessage());
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
	public PrizeScreen() {

//		ArrayList<Reward> readFileReward = readFileReward();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
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
		scrollPane.setBounds(48, 72, 694, 267);
		contentPane.add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Class");
		model.addColumn("Turns");
		model.addColumn("Prize");
		model.addColumn("Turn number");
		model.addColumn("Action");
		table.setModel(model);

		JPanel editPanel = new JPanel();
		editPanel.setBounds(48, 367, 694, 250);
		contentPane.add(editPanel);
		editPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Class");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(12, 12, 55, 30);
		editPanel.add(lblNewLabel);

		JLabel lblTurns = new JLabel("Turns");
		lblTurns.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTurns.setBounds(12, 53, 55, 30);
		editPanel.add(lblTurns);

		JLabel lblPrize = new JLabel("Prize");
		lblPrize.setFont(new Font("Dialog", Font.BOLD, 13));
		lblPrize.setBounds(12, 107, 55, 30);
		editPanel.add(lblPrize);

		String[] item = { "Giải ba", "Giải nhì","Giải nhất"  };

		cbbClass = new JComboBox(item);
		cbbClass.setBounds(85, 15, 190, 30);
		editPanel.add(cbbClass);

		txtTurns = new JTextField();
		txtTurns.setBounds(85, 58, 190, 30);
		editPanel.add(txtTurns);
		txtTurns.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(85, 119, 293, 119);
		editPanel.add(panel);
		panel.setLayout(null);

		edtPrize = new JEditorPane();
		edtPrize.setBounds(0, 0, 293, 119);
		panel.add(edtPrize);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				int selectedColumn = table.getSelectedColumn();
				int columnCount = model.getColumnCount();
				for (int column = 0; column < columnCount; column++) {
					if ("Class".equals(table.getColumnName(column))) {
						cbbClass.setSelectedItem(table.getValueAt(selectedRow, column).toString());

					} else if ("Turns".equals(table.getColumnName(column))) {
						txtTurns.setText(table.getValueAt(selectedRow, column).toString());
					} else if ("Prize".equals(table.getColumnName(column))) {
						edtPrize.setText(table.getValueAt(selectedRow, column).toString());

					} else {

					}

				}
//				table.getValueAt(selectedRow, 1);

			}
		});

//		showFileInTable(readFileReward, model);
		showDataInTable(model);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add();
				Connection conn = null;
				PreparedStatement statement = null;
				ResultSet res = null;
				try {
					
					conn = SqliteConnection.dbConnector();
					statement = conn.prepareStatement("SELECT * from reward where id = (select max(id) from reward)");
					res = statement.executeQuery();
				
					String id = res.getString(1);
					String name = res.getString(2);
					String turn = res.getString(3);
					String prize = res.getString(4);
					String t = res.getString(5);
						Vector<Object> row = new Vector<Object>();
						row.add(id);
						row.add(name);
						row.add(turn);
						row.add(prize);
						row.add(t);

						ButtonRender buttonRender = new ButtonRender();
						table.getColumn("Action").setCellRenderer(buttonRender);

						model.addRow(row);
//
					
//
//					Vector row = new Vector();
//					row.add(reward.getId());
//					row.add(reward.getClazz());
//					row.add(reward.getTurns());
//					row.add(reward.getPrize());
//					row.add(reward.getT());
//					row.add("Delete");
//					table.getColumn("Action").setCellRenderer(new ButtonRender());
//
//					model.addRow(row);

					
					table.setModel(model);
					table.setVisible(true);
					contentPane.updateUI();
					txtTurns.setText("");
					edtPrize.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
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

		btnAdd.setBounds(513, 19, 98, 30);
		editPanel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String id = "";
				int selectedRow = table.getSelectedRow();
				int columnCount = model.getColumnCount();
				for (int column = 0; column < columnCount; column++) {
					if ("Class".equals(table.getColumnName(column))) {
						table.setValueAt(cbbClass.getSelectedItem(), selectedRow, column);

					} else if ("Turns".equals(table.getColumnName(column))) {
						table.setValueAt(txtTurns.getText(), selectedRow, column);
					} else if ("Prize".equals(table.getColumnName(column))) {
						table.setValueAt(edtPrize.getText(), selectedRow, column);
					} else if ("ID".equals(table.getColumnName(column))) {
						id = table.getValueAt(selectedRow, column).toString();
					} else {

					}
				}
//			 Object selectedItem = cbbClass.getSelectedItem();
				updateDataReward(Integer.parseInt(id),cbbClass.getSelectedItem().toString() , Integer.parseInt(txtTurns.getText()),edtPrize.getText());
				
//				File f = new File("Reward.txt");
//				ArrayList<String> list = new ArrayList<String>();

//				try {
////					Scanner r = new Scanner(f);
//
//					List<String> lines = FileUtils.readLines(f, "UTF-8");
//
//					for (String st : lines) {
//						String[] token = st.split(",");
//
//						if (token[0].equals(id)) {
//							list.add(token[0] + "," + cbbClass.getSelectedItem() + "," + txtTurns.getText() + ","
//									+ edtPrize.getText());
//						} else {
//							list.add(st);
//						}
//					}
//
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				try (PrintWriter pw = new PrintWriter(f)) {
//					for (String s : list) {
//						pw.println(s);
//					}
//
//				} catch (Exception e) {
//					// TODO: handle exception
//				}

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
		btnUpdate.setBounds(513, 89, 98, 30);
		editPanel.add(btnUpdate);
		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen();
				employeeScreen.setVisible(true);

				setVisible(false);
			}
		});

		mnDb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DashbroadScreen dashbroadScreen = new DashbroadScreen();
				dashbroadScreen.setVisible(true);
				setVisible(false);
			}
		});
	}
}
