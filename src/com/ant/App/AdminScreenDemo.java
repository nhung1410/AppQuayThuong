package com.ant.App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.util.*;
import com.ant.entities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ant.Util.ExcelHelper;
import com.ant.Util.FileTypeFilter;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.*;

public class AdminScreenDemo extends JFrame {

	Iterator<Row> rowIterator;
	Row rowLabelHeader;
	private JPanel contentPane;

	public JTextField textField;
	public DefaultTableModel model;
	private int heigh = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreenDemo frame = new AdminScreenDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public  AdminScreenDemo() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 785, 26);
		contentPane.add(menuBar);

		JMenu mnEmployeeList = new JMenu("Employee ");
		menuBar.add(mnEmployeeList);

		JMenuItem showList = new JMenuItem("Show list");
		mnEmployeeList.add(showList);

		JMenu mnEditPrize = new JMenu("Prize");
		menuBar.add(mnEditPrize);

		JMenuItem openPrizePanel = new JMenuItem("Edit");
		mnEditPrize.add(openPrizePanel);

		JPanel Listpanel = new JPanel();
		Listpanel.setBounds(0, 39, 785, 611);
		contentPane.add(Listpanel);
		Listpanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 761, 42);
		Listpanel.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(158, 14, 387, 22);
		panel.add(textField);
		textField.setColumns(10);

		List<User> listUsers = new ArrayList<>();
		model = new DefaultTableModel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 102, 658, 458);
		Listpanel.add(scrollPane);

		JButton btnSelect = new JButton("Import");
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
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						Sheet sheet = workbook.getSheetAt(0);
						rowIterator = sheet.iterator();
						int i = 1;
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();
							// Get header for mapDataFilter
							Iterator<Cell> cellIterator = row.cellIterator();
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								String cellValue = cell.getStringCellValue();
								model.addColumn(cellValue);
								String abc;
							}
							i++;
							continue;
						}
//					for (Row row : sheet) {
//						if (row == null) {
//							break;
//						}
//						int rowNum = row.getRowNum();
//						model.insertRow(row, rowData);
//						for (Cell cell : row) {
////							for (int col = 0; col <= heigh; col++) {
//								if (cell.getColumnIndex() >= heigh) {
//									break;
//								}
//								model.addColumn(cell.getStringCellValue());
////							}
//						}
//					}
						model.insertRow(0, new Object[] { 1, 2, 3 });
//					model.addRow(new Object[] {4,2,3});
						System.out.println();
						JTable table = new JTable(model);
						table.setVisible(true);
//					model.addColumn("NO");
//					model.addColumn("ID");
//					model.addColumn("Name");
//					model.addColumn("Age");
//					model.addColumn("Address");
						scrollPane.setViewportView(table);
						scrollPane.updateUI();
//					contentPane.add(scrollPane);
//					contentPane.updateUI();

					}

//					
//					InputStream inp = null;
//					try {
//						inp = new FileInputStream(excelPath);
//
//						Workbook wb = WorkbookFactory.create(inp);
//						Sheet sheet = wb.getSheetAt(0);
//						Header header = sheet.getHeader();
//
//						int rowsCount = sheet.getLastRowNum();
//
//						for (int i = 0; i < rowsCount; i++) {
//							Row row = sheet.getRow(i);
//							int colCounts = row.getLastCellNum();
//						
//							for (int j = 0; j < colCounts; j++) {
//								Cell cell = row.getCell(j);
//								System.out.println(cell.getStringCellValue());
//
////								switch (cell.getCellType()) {
////								case STRING:
////									model.addColumn(cell.getStringCellValue());
////									break;
////								case BOOLEAN:
////									model.addColumn(cell.getBooleanCellValue());
////									break;
////								case NUMERIC:
////									model.addColumn(cell.getNumericCellValue());
////									break;
////								}
//
//							}
//						}
//					}

					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});

		btnSelect.setBounds(30, 13, 71, 25);
		panel.add(btnSelect);

		JPanel PrizePanel = new JPanel();
		PrizePanel.setBounds(0, 39, 785, 611);
		contentPane.add(PrizePanel);
		PrizePanel.setVisible(false);
		PrizePanel.setLayout(null);

		JTable tblPrize = new JTable();
		tblPrize.setBounds(69, 113, 656, 447);
		PrizePanel.add(tblPrize);

		JTextField txtEdit = new JTextField();
		txtEdit.setBounds(111, 514, 454, 33);
		PrizePanel.add(txtEdit);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(612, 514, 82, 33);
		PrizePanel.add(btnSave);

		openPrizePanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrizePanel.setVisible(true);
				Listpanel.setVisible(false);

			}
		});

		showList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrizePanel.setVisible(false);
				Listpanel.setVisible(true);

			}
		});

	}
}
