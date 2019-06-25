package com.ant.App;

import java.awt.Font;
import java.util.*;
import com.ant.entities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ant.Util.FileTypeFilter;
import com.ant.Util.SqliteConnection;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.io.*;
import java.awt.SystemColor;
import java.sql.*;
import java.sql.Connection;
import java.text.DecimalFormat;

public class ListEmployeeScreen extends JFrame {

	private JPanel contentPane;

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	private void addExcelPathData(String path) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("INSERT INTO excelPath (path) VALUES (?)");
			statement.setString(1, path);
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

	private String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return "";
		}
		return name.substring(lastIndexOf);
	}

	private void updatePathData(String path) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("UPDATE excelPath SET path = ?");
			statement.setString(1, path);
			statement.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}

	private void showExcelFile(JTable table, DefaultTableModel model, JTextField txtUrl) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("SELECT * FROM excelPath");

			res = statement.executeQuery();
			while (res.next()) {
				String path = res.getString("path");
				txtUrl.setText(path);
				File file = new File(path);
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				Sheet sheet = workbook.getSheetAt(0);

				for (Row row : sheet) {
					int rowNum = row.getRowNum();
					if (rowNum == 0) {

					} else {
						int i = 0;
						Vector<Object> data = new Vector<>();

						for (Cell cell : row) {

							if (cell.getCellType() == CellType.STRING) {
								data.add(i, cell.getStringCellValue());
							} else if (cell.getCellType() == CellType.NUMERIC) {
								DecimalFormat decimalFormat = new DecimalFormat("#");
								data.add(i,decimalFormat.format(cell.getNumericCellValue()) );
							}

							i++;
						}

						model.addRow(data);

					}
				}

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Chọn file có dữ liệu dạng text");

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

	/**
	 * Create the frame.
	 */
	public ListEmployeeScreen(User _user) {
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

		JMenuItem mnEmployee = new JMenuItem("Employee ");
		mnEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnEmployee.setBackground(SystemColor.activeCaption);
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		mnPrize.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		mnDb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnDb);

		JMenuItem mnProfile = new JMenuItem("Profile");
		mnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnProfile);

		JTextField txtUrl = new JTextField();
		txtUrl.setBounds(268, 38, 496, 36);
		contentPane.add(txtUrl);
		txtUrl.setColumns(10);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NO");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Address");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 117, 838, 507);
		contentPane.add(scrollPane);
		JTable table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scrollPane.setViewportView(table);
		table.setModel(model);
		showExcelFile(table, model, txtUrl);

		JButton btnSelect = new JButton("Import file");
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSelect.setBounds(133, 38, 123, 35);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser fs = new JFileChooser();
					fs.setDialogTitle("Select file");
					fs.setFileFilter(new FileTypeFilter(".xls", "Excel file"));
					fs.setFileFilter(new FileTypeFilter(".xlsx", "Excel file"));
					int result = fs.showOpenDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = fs.getSelectedFile();
						if (file.exists()
								&& (getFileExtension(file).equals(".xls") || getFileExtension(file).equals(".xlsx"))) {
							String excelPath = fs.getSelectedFile().getAbsolutePath();

							if (txtUrl.getText().isEmpty()) {

								addExcelPathData(excelPath);

							} else {
								model.getDataVector().removeAllElements();
								updatePathData(excelPath);
							}
							showExcelFile(table, model, txtUrl);

						} else {
							JOptionPane.showMessageDialog(null, "Chọn file excel");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());

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
				// TODO Auto-generated method stub
				DashbroadScreen dashbroad = new DashbroadScreen(_user);
				dashbroad.setVisible(true);
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
