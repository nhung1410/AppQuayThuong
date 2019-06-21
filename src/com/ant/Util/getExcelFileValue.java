package com.ant.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ant.entities.Employee;

public class getExcelFileValue {
	public Vector<Employee> getData(){
		Vector<Employee> list = new Vector<Employee>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			conn = SqliteConnection.dbConnector();
			statement = conn.prepareStatement("SELECT * FROM excelPath");
			res = statement.executeQuery();
			while(res.next()) {
				String path = res.getString("path");
				File file =  new File(path);
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				Sheet sheet = workbook.getSheetAt(0);
				
				for (Row row : sheet) {
					int rowNum = row.getRowNum();
					if (rowNum == 0) {

					} else {
						int i = 0;

						Vector<Object> data = new Vector<>();
						Employee employee = new Employee();
						for (Cell cell : row) {
							data.add(i, cell.getStringCellValue());
							i++;

						}
						employee.setMaNV(data.elementAt(1).toString());
						employee.setName(data.elementAt(2).toString());
						employee.setAge(Integer.parseInt(data.elementAt(3).toString()));
						employee.setAddress(data.elementAt(4).toString());

						list.add(employee);

					}

				}

		
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
		return list;
	}
}
