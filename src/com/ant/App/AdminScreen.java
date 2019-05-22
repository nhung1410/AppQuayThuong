package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.*;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AdminScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblEmployee;

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
		menuBar.setBounds(0, 0, 785, 26);
		contentPane.add(menuBar);
		
		JMenu mnEmployeeList = new JMenu("Employee List");
		menuBar.add(mnEmployeeList);
		
		JMenu mnEditPrize = new JMenu("Edit prize");
		menuBar.add(mnEditPrize);
		
		JPanel Listpanel = new JPanel();
		Listpanel.setBounds(0, 122, 785, 528);
		contentPane.add(Listpanel);
		Listpanel.setLayout(null);
		
		tblEmployee = new JTable();
		tblEmployee.setBounds(56, 31, 673, 441);
		Listpanel.add(tblEmployee);
	
		JButton btnSelect = new JButton("Import");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser( );
				fs.setDialogTitle("Select file");
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Excel file"));
				fs.setFileFilter(new FileTypeFilter(".xls", "Excel file"));
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					
					String excelPath = fs.getSelectedFile().getAbsolutePath();	
					ExcelHelper eh =new ExcelHelper(excelPath);
					try {
						List<User> listEmployee = eh.readData(User.class.getName());
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					
					
					
					
					
				}
			}
		});
		btnSelect.setBounds(46, 52, 121, 25);
		contentPane.add(btnSelect);
		
		
		
	}
}
