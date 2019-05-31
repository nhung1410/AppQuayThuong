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

public class AdminScreen extends JFrame {

	private JPanel contentPane;

	public DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
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

	public AdminScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 132, 26);
		contentPane.add(menuBar);

		JMenuItem mnEmployeeList = new JMenuItem("Employee ");
		menuBar.add(mnEmployeeList);

		JMenuItem mnEditPrize = new JMenuItem("Prize");
		menuBar.add(mnEditPrize);

		JPanel Listpanel = new JPanel();
		Listpanel.setBounds(0, 39, 785, 611);
		contentPane.add(Listpanel);
		Listpanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 761, 42);
		Listpanel.add(panel);
		panel.setLayout(null);

		JTextField txtUrl = new JTextField();
		txtUrl.setBounds(204, 13, 452, 30);
		panel.add(txtUrl);
		txtUrl.setColumns(10);

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
						txtUrl.setText(excelPath);
						@SuppressWarnings("resource")
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						Sheet sheet = workbook.getSheetAt(0);
						for (Cell cell : sheet.getRow(0)) {
							model.addColumn(cell.getStringCellValue());

						}

						for (Row row : sheet) {
							int rowNum = row.getRowNum();
							if (rowNum == 0) {

							} else {
								int i = 0;
								Vector data = new Vector<>();
								for (Cell cell : row) {
									data.add(i, cell.getStringCellValue());
									i++;
								}
								model.addRow(data);

							}

						}

						JTable table = new JTable(model);
						table.setVisible(true);
						scrollPane.setViewportView(table);
						scrollPane.updateUI();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});

		btnSelect.setBounds(103, 12, 89, 30);
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
		
		mnEmployeeList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listpanel.setVisible(true);
				PrizePanel.setVisible(false);
				
			}
		});
		
		mnEditPrize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listpanel.setVisible(false);
				PrizePanel.setVisible(true);
				
			}
		});

	}
}
