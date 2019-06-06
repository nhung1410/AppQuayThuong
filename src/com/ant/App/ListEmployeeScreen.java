package com.ant.App;

import java.awt.EventQueue;

import java.util.*;
import com.ant.entities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ant.Util.FileTypeFilter;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.io.*;
import java.awt.SystemColor;

public class ListEmployeeScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListEmployeeScreen frame = new ListEmployeeScreen();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ArrayList<User> list = new ArrayList<User>();

	private void readDataEmployee() {
		File file = new File("Login.txt");
		
			try {
				
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();
				while (data != null) {
					String token[] = data.split(",");
					User user = new User();
					user.setId(Integer.parseInt(token[0]));
					user.setName(token[3]);
					user.setAge(Integer.parseInt(token[4]));
					user.setAddress(token[5]);

					list.add(user);
					data = br.readLine();
				}
				br.close();
				fr.close();
				
			}
			catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
	}

	private void showEmployee(JTable table, JScrollPane scrollPane, DefaultTableModel model) {

		try {
			int i = 0;
			for (User u : list) {
				i++;

				Vector<Object> row = new Vector<Object>();
				row.add(i);
				row.add(u.getId());
				row.add(u.getName());
				row.add(u.getAge());
				row.add(u.getAddress());

				model.addRow(row);
			}

			table.setModel(model);

			table.setVisible(true);
			scrollPane.setViewportView(table);
			scrollPane.updateUI();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public ListEmployeeScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 213, 26);
		contentPane.add(menuBar);

		JMenuItem mnEmployee = new JMenuItem("Employee ");
		mnEmployee.setBackground(SystemColor.activeCaption);
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		menuBar.add(mnDb);

		JTextField txtUrl = new JTextField();
		txtUrl.setBounds(212, 38, 496, 36);
		contentPane.add(txtUrl);
		txtUrl.setColumns(10);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NO");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Address");
		
		readDataEmployee();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 108, 693, 507);
		contentPane.add(scrollPane);
		JTable table = new JTable();
		showEmployee(table, scrollPane, model);

		JButton btnSelect = new JButton("Import");
		btnSelect.setBounds(77, 38, 112, 35);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser();
				fs.setDialogTitle("Select file");

				fs.setFileFilter(new FileTypeFilter(".xls", "Excel file"));
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Excel file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {

					String excelPath = fs.getSelectedFile().getAbsolutePath();

					try {
						FileInputStream fis = new FileInputStream(new File(excelPath));
						txtUrl.setText(excelPath);
						@SuppressWarnings("resource")
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						Sheet sheet = workbook.getSheetAt(0);
//						for (Cell cell : sheet.getRow(0)) {
//							model.addColumn(cell.getStringCellValue());
//
//						}

						for (Row row : sheet) {
							int rowNum = row.getRowNum();
							if (rowNum == 0) {

							} else {
								int i = 0;
								Vector<Object> data = new Vector<>();
								for (Cell cell : row) {
									data.add(i, cell.getStringCellValue());
									i++;
								}
								model.addRow(data);

							}

						}

						table.setVisible(true);
						scrollPane.setViewportView(table);
						scrollPane.updateUI();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});

		mnPrize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrizeScreen prizeScreen = new PrizeScreen();
				prizeScreen.setVisible(true);
				setVisible(false);
			}
		});
		mnDb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DashbroadScreen dashbroad = new DashbroadScreen();
				dashbroad.setVisible(true);
				setVisible(false);
			}
		});
	}
}
